package com.zyc.mrcweb.User;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/SignUp")
    public ModelAndView showSignUp(){
        return new ModelAndView("SignUp", "User", new User());
    }

    @PostMapping("/SummitSignup")
    public ModelAndView summitSignup(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("repassword") String repassword){
        User user=userRepository.findByUsername(username);
        if(user!=null){
            return new ModelAndView("Login", "User", user);
        }
        else if(password.equals(repassword)){
            User newUser=new User(username, password);
            userRepository.save(newUser);
            return new ModelAndView("Login", "User", user);
        }
        else return new ModelAndView("SignUp", "User", user);
    }
}
