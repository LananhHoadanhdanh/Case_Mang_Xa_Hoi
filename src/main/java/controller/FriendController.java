package controller;

import model.entity.Friend;
import model.entity.ImageUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import service.friend.IFriendService;
import service.imageUse.IImageUseService;
import service.user.IUserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IImageUseService imageUseService;

    @Autowired
    private IFriendService friendService;

    @ModelAttribute("user")
    public Iterable<User> imageUsers() {
        return userService.findAll();
    }

    @GetMapping("timeline-friends")
    public String showCreateUser(Model model,Long idU) {
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(idU);
        List<Friend> friendList = friendService.findAllFriendByIdFr(idU);
        List<ImageUser> imageUserList = new ArrayList<>();
        for(Friend friend:friendList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(friend.getUser().getId()));
//            for(ImageUser imageUser1 : imageUserList){
//                if(friend.getUser().getId() == imageUser1.getUserId().getId()){
//                    List<ImageUser> imageUserList1 = new ArrayList<>();
//                     imageUserList1.add(imageUseService.findByUserIdAndStatus(friend.getUser().getId()));
//                    model.addAttribute("imgFriend",imageUserList1);
//                }
//            }
        }
        model.addAttribute("imgFriend",imageUserList);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("friendS",friendList);
        return "timeline/timeline-friends";
    }


}
