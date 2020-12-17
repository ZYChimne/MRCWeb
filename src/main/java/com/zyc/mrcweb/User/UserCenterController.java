package com.zyc.mrcweb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.Locale;

@Controller
public class UserCenterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/UserCenter")
    public ModelAndView showUserCenter(Locale locale, Model model) throws IOException {
        File f=new File("cookie");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String username = br.readLine();
        User user=userRepository.findByUsername(username);
        return new ModelAndView("UserCenter", "User", user);
    }

    @PostMapping("/SummitAmount")
    public ModelAndView summitAmount(@RequestParam("amount") String requestAmount,
                                     @RequestParam("confirmAmount") String confirmRequestAmount) throws IOException {
        int amount=(int)Double.parseDouble(requestAmount);
        int confirmAmount=(int)Double.parseDouble(confirmRequestAmount);
        File f=new File("cookie");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String username = br.readLine();
        User user=userRepository.findByUsername(username);
        if(amount==confirmAmount){
            if(amount>999){
                user.setMember(true);
                userRepository.save(user);
            }
            else if(amount>0){
                user.setAllowUsageTimes(user.getAllowUsageTimes()+amount);
                userRepository.save(user);
                System.out.println(amount);
            }
        }
        return new ModelAndView("UserCenter", "User", user);
    }
}
