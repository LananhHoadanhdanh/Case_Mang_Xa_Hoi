package controller;

import javafx.geometry.Pos;
import model.entity.Friend;
import model.entity.Post;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Comment.ICommentService;
import service.friend.IFriendService;
import service.image.IImageService;
import service.imageUse.IImageUseService;
import service.post.IPostService;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public String showNewsfeed(){
        User userAcc = (User) httpSession.getAttribute("user");
        Iterable<Post> postsAcc = postService.findAllByUser(userAcc.getId());
        List<Friend> friends = friendService.findAllFriendByIdFr(userAcc.getId());
        List<User> userFriend = new ArrayList<>();
        for (Friend friend : friends) {
            userFriend.add(friend.getUser());
        }
        return "newsfeed/newsfeed";
    }
}
