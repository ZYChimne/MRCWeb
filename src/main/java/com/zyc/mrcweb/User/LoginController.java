package com.zyc.mrcweb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.Locale;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/")
    public String index() {
        return "Login";
    }

    @PostMapping("/SummitLogin")
    public ModelAndView summitLogin(@RequestParam(value = "username") String username,
                                    @RequestParam(value="password") String password) throws IOException {
        User user=userRepository.findByUsername(username);
        //System.out.println(user.getUsername());
        //System.out.println(user.getPassword());
        //System.out.println(password);
        //System.out.println(username);
        //EQUALS
        if(password.equals(user.getPassword()))
        {
            File f=new File("cookie");
            f.setWritable(true);
            FileOutputStream fos = new FileOutputStream(f);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(user.getUsername());
            writer.close();
            fos.close();
            f.setReadOnly();
            return new ModelAndView("QNC", "User", user);
        }
        else return  new ModelAndView("Login", "User", user);
    }
}
