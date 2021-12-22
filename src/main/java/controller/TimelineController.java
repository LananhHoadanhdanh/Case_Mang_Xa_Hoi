package controller;

import model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Comment.ICommentService;
import service.image.IImageService;
import service.imageUse.IImageUseService;
import service.post.IPostService;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/timeline")
public class TimelineController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private IPostService postService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private IImageUseService imageUseService;
    @Autowired
    private ICommentService commentService;

    @GetMapping("")
    public String showTimeLine(Model model, Long userId){
        User userAcc = (User) httpSession.getAttribute("user");
        if (userAcc != null) {
            model.addAttribute("userAcc", userAcc);

            User otherUser = userService.findById(userId).get();
            model.addAttribute("otherUser", otherUser);

            ImageUser imageUser = imageUseService.findByUser(otherUser.getId());
            model.addAttribute("imageUser", imageUser);

            Iterable<Post> posts = postService.findAllByUser(otherUser.getId());
            model.addAttribute("posts", posts);

            Iterable<Comment> comments = commentService.findAll();
            model.addAttribute("comments", comments);

            Iterable<ImageUser> imageUsers = imageUseService.findAll();
            model.addAttribute("imageUsers", imageUsers);

            return "timeline/timeline";
        } return "redirect:/login";
    }

    @PostMapping("/post")
    public String createNewPost(Post post){
        User user1 = (User) httpSession.getAttribute("user");
        post.setUser(user1);
        post.setDateTime(LocalDateTime.now());
        postService.save(post);
        imageService.save(new Image(post.getImage(), post.getId(), user1));
        return "redirect:/timeline?userId=" + user1.getId();
    }

    @PostMapping("/update-post")
    public String updatePost(Post post){
        User user1 = (User) httpSession.getAttribute("user");
        postService.save(post);
        post.setUser(user1);
        return "redirect:/timeline?userId=" + user1.getId();
    }

    @PostMapping("/post-comment")
    private String commentPost(Long postId, String comment) {
        User user1 = (User) httpSession.getAttribute("user");
        Post post = postService.findById(postId).get();
        Comment comment1 = new Comment(comment, LocalDateTime.now(), user1, post);
        commentService.save(comment1);
        return "redirect:/timeline?userId=" + user1.getId();
    }

    @PostMapping("post-update")
    private String updatePost(Model model, Long postId, String content, String image) {
        User user1 = (User) httpSession.getAttribute("user");
        Post post = postService.findById(postId).get();
        model.addAttribute("postUp", post);
        post.setContent(content);
        post.setImage(image);
        postService.save(post);
        return "redirect:/timeline?userId=" + user1.getId();
    }
    @GetMapping("post-delete")
    private String deletePost(Long postId) {
        User user1 = (User) httpSession.getAttribute("user");
        Post post = postService.findById(postId).get();
        post.setStatus(0);
        postService.save(post);
        return "redirect:/timeline?userId=" + user1.getId();
    }


}
