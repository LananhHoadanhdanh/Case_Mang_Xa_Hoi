package controller;

import model.entity.Friend;
import model.entity.ImageUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.friend.IFriendService;
import service.imageUse.IImageUseService;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class FriendController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IImageUseService imageUseService;

    @Autowired
    private IFriendService friendService;

    @Autowired
    private HttpSession httpSession;

    @ModelAttribute("user")
    public Iterable<User> imageUsers() {
        return userService.findAll();
    }

    @GetMapping("/friend")
    public String showCreateUser(Model model,Long idU) {
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(idU);
        List<Friend> friendList = friendService.findAllFriendByIdFr(idU);
        List<ImageUser> imageUserList = new ArrayList<>();
        for(Friend friend:friendList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(friend.getUser().getId()));
        }
        model.addAttribute("imgFriend",imageUserList);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("friendS",friendList);
        return "newsfeed/newsfeed-friends";
    }
    @PostMapping("/friend")
    public String searchUser(Model model,String searchName){
        User user1 = (User) httpSession.getAttribute("user");
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        List<Friend> listFriendUser1 = friendService.findAllFriend(user1.getId());
        List<User> userList = new ArrayList<>();
        List<User> isFriend = new ArrayList<>();
        List<User> notIsFriend = new ArrayList<>();
        List<User> waiting = new ArrayList<>();
        Iterable<User> friendList = userService.findAllByFullNameContaining(searchName);
        boolean check = false;
        boolean check1 = false;
        for(User user:friendList){
            if(user.getId() != user1.getId()){
                userList.add(user);
            }
        }
        for(User user:userList){
            for(Friend friend:listFriendUser1){
                if (Objects.equals(user.getId(), friend.getUser().getId()) && friend.getStatus() == 1 ) {
                    check = true;
                } else if(Objects.equals(user.getId(), friend.getUser().getId()) && friend.getStatus() == 2){
                    check1 = true;
                }
            }
            if(check && !check1){
                isFriend.add(user);
            } else if(check1) {
                waiting.add(user);
            } else {
                notIsFriend.add(user);
            }
        }
        for(User user2: userList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(user2.getId()));
        }
        model.addAttribute("isFriend",isFriend);
        model.addAttribute("waiting",waiting);
        model.addAttribute("notIsFriend",notIsFriend);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("imgFriend",imageUserList);
        return "newsfeed/newsfeed-people-nearby";
    }
    @PostMapping("/addFriend")
    public String addFriend(Model model, Long idFriend){
        User user1 = (User) httpSession.getAttribute("user");
        User user = userService.findUserById(idFriend);
        LocalDateTime time = LocalDateTime.now();
        Friend friend = new Friend(time,2,user1.getId(),user);
        Friend friend2 = new Friend(time,2,user.getId(),user1);
        friendService.save(friend);
        friendService.save(friend2);
        return "redirect:friend";
    }

}
