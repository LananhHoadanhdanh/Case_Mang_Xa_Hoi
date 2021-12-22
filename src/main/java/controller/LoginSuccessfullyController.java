package controller;

import org.springframework.stereotype.Controller;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginSuccessfullyController {
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/successfully")
    public ModelAndView checkLogin(@ModelAttribute("user") User user,@RequestParam("Email") String email,@RequestParam("password") String password){
        Optional<User> user1 = userService.findByEmailAndPassword(email, password);
        ModelAndView modelAndView;
        if (user1.isPresent()) { // kiểm tra xem dữ liệu có null không
            modelAndView = new ModelAndView("newsfeed/newsfeed");
            httpSession.setAttribute("user",user1.get());
            modelAndView.addObject("user1",user1.get());
        } else {
            modelAndView = new ModelAndView("login");
        }
        return modelAndView;
    }
    @GetMapping("/resetNewsfeed")
    public ModelAndView resetNewsfeed(){
        User user1 = (User) httpSession.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("newsfeed/newsfeed");
        modelAndView.addObject("user1",user1);
        return modelAndView;
    }
}
