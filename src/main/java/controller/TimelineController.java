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

@Controller
@RequestMapping("/timeline")
public class TimelineController {
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
    public String showTimeLine(Model model){
        User user1 = (User) httpSession.getAttribute("user");
        if (user1 != null) {
            model.addAttribute("user", user1);
            Iterable<Post> posts = postService.findAllByUser(user1.getId());
            model.addAttribute("posts", posts);
            ImageUser imageUser = imageUseService.findByUser(user1.getId());
            model.addAttribute("imageUser", imageUser);
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
        return "redirect:/timeline";
    }

    @PostMapping("/update-post")
    public String updatePost(Post post){
        User user1 = (User) httpSession.getAttribute("user");
        postService.save(post);
        post.setUser(user1);
        return "redirect:/timeline";
    }

    @PostMapping("/post-comment")
    private String commentPost(Long postId, String comment) {
        User user1 = (User) httpSession.getAttribute("user");
        Post post = postService.findById(postId).get();
        Comment comment1 = new Comment(comment, LocalDateTime.now(), user1, post);
        commentService.save(comment1);
        return "redirect:/timeline";
    }

    @PostMapping("post-update")
    private String updatePost(Long postId, String content) {
        Post post = postService.findById(postId).get();
        post.setContent(content);
        postService.save(post);
        return "redirect:/timeline";
    }



}
