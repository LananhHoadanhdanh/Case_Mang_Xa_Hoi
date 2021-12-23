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
            User userAcc = (User) httpSession.getAttribute("user");
            if (userAcc != null) {
                model.addAttribute("userAcc", userAcc);
                ArrayList<Post> allPost = (ArrayList<Post>) postService.findAllAndOrderByDateTime();
                List<Friend> friends = friendService.findAllFriendByIdFr(userAcc.getId());
                List<User> userFriend = new ArrayList<>();
                for (Friend friend : friends) {
                    userFriend.add(friend.getUser());
                }
                ArrayList<Post> newsfeedPost = new ArrayList<>();
                for (Post post : allPost) {
                    if (Objects.equals(post.getUser().getId(), userAcc.getId())) {
                        newsfeedPost.add(post);
                    } else {
                        for (User user3 : userFriend) {
                            if (Objects.equals(user3.getId(), post.getUser().getId())) {
                                newsfeedPost.add(post);
                            }
                        }
                    }
                }
                model.addAttribute("posts", newsfeedPost);

                Iterable<ImageUser> imageUsers = imageUseService.findAll();
                model.addAttribute("imageUsers", imageUsers);

                ImageUser imageUserAcc = imageUseService.findByUser(userAcc.getId());
                model.addAttribute("imageUserAcc", imageUserAcc);

                Iterable<Comment> comments = commentService.findAll();
                model.addAttribute("comments", comments);
                return "newsfeed/newsfeed";
            } return "redirect:/login";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/resetNewsfeed")
    public String resetNewsfeed(Model model) {
        User userAcc = (User) httpSession.getAttribute("user");
        if (userAcc != null) {
            model.addAttribute("userAcc", userAcc);
            ArrayList<Post> allPost = (ArrayList<Post>) postService.findAllAndOrderByDateTime();
            List<Friend> friends = friendService.findAllFriendByIdFr(userAcc.getId());
            List<User> userFriend = new ArrayList<>();
            for (Friend friend : friends) {
                userFriend.add(friend.getUser());
            }
            ArrayList<Post> newsfeedPost = new ArrayList<>();
            for (Post post : allPost) {
                if (Objects.equals(post.getUser().getId(), userAcc.getId())) {
                    newsfeedPost.add(post);
                } else {
                    for (User user3 : userFriend) {
                        if (Objects.equals(user3.getId(), post.getUser().getId())) {
                            newsfeedPost.add(post);
                        }
                    }
                }
            }
            model.addAttribute("posts", newsfeedPost);

            Iterable<ImageUser> imageUsers = imageUseService.findAll();
            model.addAttribute("imageUsers", imageUsers);

            ImageUser imageUserAcc = imageUseService.findByUser(userAcc.getId());
            model.addAttribute("imageUserAcc", imageUserAcc);

            Iterable<Comment> comments = commentService.findAll();
            model.addAttribute("comments", comments);
            return "newsfeed/newsfeed";
        } return "redirect:/login";
    }

}