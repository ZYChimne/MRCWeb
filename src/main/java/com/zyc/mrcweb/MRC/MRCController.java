package com.zyc.mrcweb.MRC;

import com.zyc.mrcweb.User.User;
import com.zyc.mrcweb.User.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

@Controller
public class MRCController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/getAnswer")
    public ModelAndView getAnswer(@RequestParam("question") String question,
                                  @RequestParam("context") String context,
                                  @RequestParam("username") String username,
                                  Locale locale,
                                  Model model){
        User user=userRepository.findByUsername(username);
        if(user.getMember()){
            ResultSet resultSet=getAnswer(question, context);
            model.addAttribute("Answer",resultSet.getAnswer());
        }
        else{
            if(user.getAllowUsageTimes()>0){
                user.setAllowUsageTimes(user.getAllowUsageTimes()-1);
                user.setUsageTimes(user.getUsageTimes()+1);
                ResultSet resultSet=getAnswer(question, context);
                model.addAttribute("Answer",resultSet.getAnswer());
            }
            else{
                return new ModelAndView("Error","msg","YOU HAVE RUN OUT OF YOUR USAGE TIMES\n" +
                        "Please CHARGE IN THE USER CENTER\n");
            }
        }
//        ResultSet rs=new ResultSet();
//        rs.setAnswer("Beijing");
//        model.addAttribute("Answer",rs.getAnswer());
		return new ModelAndView("QNC", "User", user);
    }
    
    @GetMapping("/RedirectToMagicBox")
    public ModelAndView RedirectToMagicBox() throws IOException {
        File f=new File("cookie");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String username = br.readLine();
        User user=userRepository.findByUsername(username);
        return new ModelAndView("QNC","User", user);
    }

    private ResultSet getAnswer(String question, String context){
        QuestionSet qs=new QuestionSet(question, context);
		MRC mrc=new MRC(qs);
		mrc.runMRCScript();
		ResultSet rs= mrc.getAnswer();
		return rs;
    }
}
