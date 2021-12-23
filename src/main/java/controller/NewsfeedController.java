package controller;

import model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Comment.ICommentService;
import service.friend.IFriendService;
import service.image.IImageService;
import service.imageUse.IImageUseService;
import service.post.IPostService;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/newsfeed")
public class NewsfeedController {
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
    @Autowired
    private IFriendService friendService;

    @GetMapping("")
    public String showNewsfeed(Model model){
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
                    for (User user : userFriend) {
                        if (Objects.equals(user.getId(), post.getUser().getId())) {
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

    @PostMapping("/post")
    public String createNewPost(Post post){
        User user1 = (User) httpSession.getAttribute("user");
        post.setUser(user1);
        post.setDateTime(LocalDateTime.now());
        postService.save(post);
        imageService.save(new Image(post.getImage(), post.getId(), user1));
        return "redirect:/newsfeed";
    }

    @PostMapping("/post-comment")
    private String commentPost(Long postId, String comment) {
        User user1 = (User) httpSession.getAttribute("user");
        Optional<Post> post = postService.findById(postId);
        Comment comment1 = new Comment(comment, LocalDateTime.now(), user1, post.get());
        commentService.save(comment1);
        return "redirect:/newsfeed";
    }

    @PostMapping("post-update")
    private String updatePost(Long postId, String content, String image) {
        User user1 = (User) httpSession.getAttribute("user");
        Post post = postService.findById(postId).get();
        post.setContent(content);
        post.setImage(image);
        postService.save(post);
        return "redirect:/newsfeed";
    }
    @GetMapping("post-delete")
    private String deletePost(Long postId) {
        User user1 = (User) httpSession.getAttribute("user");
        Post post = postService.findById(postId).get();
        post.setStatus(0);
        postService.save(post);
        return "redirect:/newsfeed";
    }
}
