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
        User user1 = (User) httpSession.getAttribute("user");
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(idU);
        List<Friend> friendList = friendService.findAllFriendByIdFr(idU);
        List<ImageUser> imageUserList = new ArrayList<>();
        for(Friend friend:friendList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(friend.getUser().getId()));
        }
        model.addAttribute("imgFriend",imageUserList);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("user1",user1);
        model.addAttribute("friendS",friendList);
        return "newsfeed/newsfeed-friends";
    }
    @GetMapping("/friendSearch")
    public String searchUser(Model model,String searchName){
        httpSession.setAttribute("searchName",searchName);
        User user1 = (User) httpSession.getAttribute("user");
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        List<Friend> listFriendUser1 = friendService.findAllFriend(user1.getId());
        List<User> userList = new ArrayList<>();
        List<User> isFriend = new ArrayList<>();
        List<User> notIsFriend = new ArrayList<>();
        List<User> waiting = new ArrayList<>();
        List<User> answer = new ArrayList<>();
        Iterable<User> friendList = userService.findAllByFullNameContaining(searchName);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
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
                } else if(Objects.equals(user.getId(), friend.getUser().getId()) && friend.getStatus() == 3){
                    check2 = true;
                }
            }
            if(check && !check1){
                isFriend.add(user);
            } else if(check1) {
                waiting.add(user);
            } else if(check2) {
                answer.add(user);
            } else {
                notIsFriend.add(user);
            }
            check = false;
            check1 = false;
            check2 = false;
        }
        for(User user2: userList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(user2.getId()));
        }
        model.addAttribute("isFriend",isFriend);
        model.addAttribute("waiting",waiting);
        model.addAttribute("answer",answer);
        model.addAttribute("notIsFriend",notIsFriend);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("user1",user1);
        model.addAttribute("imgFriend",imageUserList);
        return "newsfeed/newsfeed-people-nearby";
    }
    @GetMapping("/AddFriend")
    public String addFriend(Model model, Long idFriend){
        String searchName = (String) httpSession.getAttribute("searchName");
        User user1 = (User) httpSession.getAttribute("user");
        User user = userService.findUserById(idFriend);
        LocalDateTime time = LocalDateTime.now();
        Friend friend = new Friend(time,2,user1.getId(),user);
        Friend friend2 = new Friend(time,3,user.getId(),user1);
        friendService.save(friend);
        friendService.save(friend2);
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        List<Friend> listFriendUser1 = friendService.findAllFriend(user1.getId());
        List<User> userList = new ArrayList<>();
        List<User> isFriend = new ArrayList<>();
        List<User> notIsFriend = new ArrayList<>();
        List<User> waiting = new ArrayList<>();
        List<User> answer = new ArrayList<>();
        Iterable<User> friendList = userService.findAllByFullNameContaining(searchName);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        for(User user3:friendList){
            if(user3.getId() != user1.getId()){
                userList.add(user3);
            }
        }
        for(User user4:userList){
            for(Friend friend1:listFriendUser1){
                if (Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 1 ) {
                    check = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 2){
                    check1 = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 3){
                    check2 = true;
                }
            }
            if(check && !check1){
                isFriend.add(user4);
            } else if(check1) {
                waiting.add(user4);
            } else if(check2) {
                answer.add(user4);
            } else {
                notIsFriend.add(user4);
            }
            check = false;
            check1 = false;
            check2 = false;
        }
        for(User user2: userList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(user2.getId()));
        }
        model.addAttribute("isFriend",isFriend);
        model.addAttribute("waiting",waiting);
        model.addAttribute("answer",answer);
        model.addAttribute("notIsFriend",notIsFriend);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("user1",user1);
        model.addAttribute("imgFriend",imageUserList);
        return "newsfeed/newsfeed-people-nearby";
    }
    @GetMapping("unWaiting")
    public String unWaiting(Model model, Long idFriend){
        String searchName = (String) httpSession.getAttribute("searchName");
        User user1 = (User) httpSession.getAttribute("user");
        Friend friend = friendService.findFriendByIdUserAndIdFriendOfUser1(idFriend,user1.getId());
        Friend friend2 = friendService.findFriendByIdUserAndIdFriendOfUser1(user1.getId(),idFriend);
        friendService.remove(friend.getId());
        friendService.remove(friend2.getId());
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        List<Friend> listFriendUser1 = friendService.findAllFriend(user1.getId());
        List<User> userList = new ArrayList<>();
        List<User> isFriend = new ArrayList<>();
        List<User> notIsFriend = new ArrayList<>();
        List<User> waiting = new ArrayList<>();
        List<User> answer = new ArrayList<>();
        Iterable<User> friendList = userService.findAllByFullNameContaining(searchName);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        for(User user3:friendList){
            if(user3.getId() != user1.getId()){
                userList.add(user3);
            }
        }
        for(User user4:userList){
            for(Friend friend1:listFriendUser1){
                if (Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 1 ) {
                    check = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 2){
                    check1 = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 3){
                    check2 = true;
                }
            }
            if(check && !check1){
                isFriend.add(user4);
            } else if(check1) {
                waiting.add(user4);
            } else if(check2) {
                answer.add(user4);
            } else {
                notIsFriend.add(user4);
            }
            check = false;
            check1 = false;
            check2 = false;
        }
        for(User user2: userList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(user2.getId()));
        }
        model.addAttribute("isFriend",isFriend);
        model.addAttribute("waiting",waiting);
        model.addAttribute("notIsFriend",notIsFriend);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("answer",answer);
        model.addAttribute("user1",user1);
        model.addAttribute("imgFriend",imageUserList);
        return "newsfeed/newsfeed-people-nearby";
    }
    @GetMapping("unFriend")
    public String unFriend(Model model, Long idFriend){
        String searchName = (String) httpSession.getAttribute("searchName");
        User user1 = (User) httpSession.getAttribute("user");
        Friend friend = friendService.findFriendByIdUserAndIdFriendOfUser1(idFriend,user1.getId());
        Friend friend2 = friendService.findFriendByIdUserAndIdFriendOfUser1(user1.getId(),idFriend);
        friendService.remove(friend.getId());
        friendService.remove(friend2.getId());
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        List<Friend> listFriendUser1 = friendService.findAllFriend(user1.getId());
        List<User> userList = new ArrayList<>();
        List<User> isFriend = new ArrayList<>();
        List<User> notIsFriend = new ArrayList<>();
        List<User> waiting = new ArrayList<>();
        List<User> answer = new ArrayList<>();
        Iterable<User> friendList = userService.findAllByFullNameContaining(searchName);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        for(User user3:friendList){
            if(user3.getId() != user1.getId()){
                userList.add(user3);
            }
        }
        for(User user4:userList){
            for(Friend friend1:listFriendUser1){
                if (Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 1 ) {
                    check = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 2){
                    check1 = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 3){
                    check2 = true;
                }
            }
            if(check && !check1){
                isFriend.add(user4);
            } else if(check1) {
                waiting.add(user4);
            } else if(check2) {
                answer.add(user4);
            } else {
                notIsFriend.add(user4);
            }
            check = false;
            check1 = false;
            check2 = false;
        }
        for(User user2: userList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(user2.getId()));
        }
        model.addAttribute("isFriend",isFriend);
        model.addAttribute("waiting",waiting);
        model.addAttribute("notIsFriend",notIsFriend);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("answer",answer);
        model.addAttribute("user1",user1);
        model.addAttribute("imgFriend",imageUserList);
        return "newsfeed/newsfeed-people-nearby";
    }

    @GetMapping("request")
    public  String requestFriend(Model model,Long idU){
        User user1 = (User) httpSession.getAttribute("user");
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(idU);
        List<Friend> friendList = friendService.findAllFriendAddById(idU);
        List<ImageUser> imageUserList = new ArrayList<>();
        for(Friend friend:friendList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(friend.getUser().getId()));
        }
        model.addAttribute("imgFriend",imageUserList);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("user1",user1);
        model.addAttribute("friendS",friendList);
        return "newsfeed/newsfeed-friendRequest";
    }
    @GetMapping("accept")
    public String accept(Model model, Long idFriend){
        User user1 = (User) httpSession.getAttribute("user");
        Friend friend = friendService.findFriendByIdUserAndIdFriendOfUser1(idFriend,user1.getId());
        Friend friend2 = friendService.findFriendByIdUserAndIdFriendOfUser1(user1.getId(),idFriend);
        friend.setStatus(1);
        friend2.setStatus(1);
        friendService.save(friend);
        friendService.save(friend2);
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<Friend> friendList = friendService.findAllFriendAddById(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        for(Friend friend3:friendList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(friend3.getUser().getId()));
        }
        model.addAttribute("imgFriend",imageUserList);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("user1",user1);
        model.addAttribute("friendS",friendList);
        return "newsfeed/newsfeed-friendRequest";
    }
    @GetMapping("delete")
    public String delete(Model model, Long idFriend){
        User user1 = (User) httpSession.getAttribute("user");
        Friend friend = friendService.findFriendByIdUserAndIdFriendOfUser1(idFriend,user1.getId());
        Friend friend2 = friendService.findFriendByIdUserAndIdFriendOfUser1(user1.getId(),idFriend);
        friendService.remove(friend.getId());
        friendService.remove(friend2.getId());
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<Friend> friendList = friendService.findAllFriendAddById(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        for(Friend friend3:friendList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(friend3.getUser().getId()));
        }
        model.addAttribute("imgFriend",imageUserList);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("friendS",friendList);
        return "newsfeed/newsfeed-friendRequest";
    }

    @PostMapping("/acceptSearch")
    public String acceptSearch(Long idFriend, Model model){
        String searchName = (String) httpSession.getAttribute("searchName");
        User user1 = (User) httpSession.getAttribute("user");
        Friend friend = friendService.findFriendByIdUserAndIdFriendOfUser1(idFriend,user1.getId());
        Friend friend2 = friendService.findFriendByIdUserAndIdFriendOfUser1(user1.getId(),idFriend);
        friend.setStatus(1);
        friend2.setStatus(1);
        friendService.save(friend);
        friendService.save(friend2);
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        List<Friend> listFriendUser1 = friendService.findAllFriend(user1.getId());
        List<User> userList = new ArrayList<>();
        List<User> isFriend = new ArrayList<>();
        List<User> notIsFriend = new ArrayList<>();
        List<User> waiting = new ArrayList<>();
        List<User> answer = new ArrayList<>();
        Iterable<User> friendList = userService.findAllByFullNameContaining(searchName);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        for(User user3:friendList){
            if(user3.getId() != user1.getId()){
                userList.add(user3);
            }
        }
        for(User user4:userList){
            for(Friend friend1:listFriendUser1){
                if (Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 1 ) {
                    check = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 2){
                    check1 = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 3){
                    check2 = true;
                }
            }
            if(check && !check1){
                isFriend.add(user4);
            } else if(check1) {
                waiting.add(user4);
            } else if(check2) {
                answer.add(user4);
            } else {
                notIsFriend.add(user4);
            }
            check = false;
            check1 = false;
            check2 = false;
        }
        for(User user2: userList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(user2.getId()));
        }
        model.addAttribute("isFriend",isFriend);
        model.addAttribute("waiting",waiting);
        model.addAttribute("notIsFriend",notIsFriend);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("answer",answer);
        model.addAttribute("user1",user1);
        model.addAttribute("imgFriend",imageUserList);
        return "newsfeed/newsfeed-people-nearby";
    }
    @PostMapping("/deleteSearch")
    public String deleteSearch(Long idFriend, Model model){
        String searchName = (String) httpSession.getAttribute("searchName");
        User user1 = (User) httpSession.getAttribute("user");
        Friend friend = friendService.findFriendByIdUserAndIdFriendOfUser1(idFriend,user1.getId());
        Friend friend2 = friendService.findFriendByIdUserAndIdFriendOfUser1(user1.getId(),idFriend);
        friendService.remove(friend.getId());
        friendService.remove(friend2.getId());
        ImageUser imageUser = imageUseService.findByUserIdAndStatus(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        List<Friend> listFriendUser1 = friendService.findAllFriend(user1.getId());
        List<User> userList = new ArrayList<>();
        List<User> isFriend = new ArrayList<>();
        List<User> notIsFriend = new ArrayList<>();
        List<User> waiting = new ArrayList<>();
        List<User> answer = new ArrayList<>();
        Iterable<User> friendList = userService.findAllByFullNameContaining(searchName);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        for(User user3:friendList){
            if(user3.getId() != user1.getId()){
                userList.add(user3);
            }
        }
        for(User user4:userList){
            for(Friend friend1:listFriendUser1){
                if (Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 1 ) {
                    check = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 2){
                    check1 = true;
                } else if(Objects.equals(user4.getId(), friend1.getUser().getId()) && friend1.getStatus() == 3){
                    check2 = true;
                }
            }
            if(check && !check1){
                isFriend.add(user4);
            } else if(check1) {
                waiting.add(user4);
            } else if(check2) {
                answer.add(user4);
            } else {
                notIsFriend.add(user4);
            }
            check = false;
            check1 = false;
            check2 = false;
        }
        for(User user2: userList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(user2.getId()));
        }
        model.addAttribute("isFriend",isFriend);
        model.addAttribute("waiting",waiting);
        model.addAttribute("notIsFriend",notIsFriend);
        model.addAttribute("imgUser",imageUser);
        model.addAttribute("user1",user1);
        model.addAttribute("answer",answer);
        model.addAttribute("imgFriend",imageUserList);
        return "newsfeed/newsfeed-people-nearby";
    }
}
