package controller;

import model.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.Comment.ICommentService;
import service.friend.IFriendService;
import service.image.IImageService;
import service.imageUse.IImageUseService;
import service.post.IPostService;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class LoginSuccessfullyController {
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private IImageService imageService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IImageUseService imageUseService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IFriendService friendService;

    @PostMapping("/successfully")
    public String checkLogin(@ModelAttribute("user") User user, @RequestParam("Email") String email, @RequestParam("password") String password, Model model) {
        Optional<User> user1 = userService.findByEmailAndPassword(email, password);
        if (user1.isPresent()) { // kiểm tra xem dữ liệu có null không
            httpSession.setAttribute("user", user1.get());
            return "redirect:/newsfeed";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/successfully")
    public String resetNewsfeed(Model model) {
        return "redirect:/newsfeed";
    }
}