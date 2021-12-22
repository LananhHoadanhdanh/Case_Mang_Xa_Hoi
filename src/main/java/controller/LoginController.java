package controller;

import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
//@SessionAttributes("user")
@RequestMapping("/login")

public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("newUser",new User());
        return modelAndView;
    }
    @GetMapping("logout")
    public ModelAndView logout(){
        httpSession.removeAttribute("user");
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @PostMapping("register")
    public ModelAndView register( User user){
        ModelAndView modelAndView = new ModelAndView("login");
        userService.save(user);
        modelAndView.addObject("newUser",new User());
        return modelAndView;
    }

    @GetMapping("home")
    public ModelAndView showHome(){
        ModelAndView modelAndView =new ModelAndView("result");
        User user1 = (User) httpSession.getAttribute("user");
        modelAndView.addObject("user",user1);
        return modelAndView;
    }

}
