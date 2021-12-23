package controller;

import model.entity.Friend;
import model.entity.ImageUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.friend.IFriendService;
import service.imageUse.IImageUseService;
import service.user.IUserService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("edit")
public class EditController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IImageUseService imageUseService;

    @Autowired
    private IFriendService friendService;

    @Autowired
    private HttpSession httpSession;
    @GetMapping("profile")
    public ModelAndView showEditForm(){
        ModelAndView modelAndView = new ModelAndView("edit_profile/edit-profile-basic");
        modelAndView.addObject("newUser",new User());
        User userInEdit = (User) httpSession.getAttribute("user");
        modelAndView.addObject("userInEdit",userInEdit);
        return modelAndView;
    }
    @PostMapping("/profile")
    public ModelAndView saveProduct( User user,@RequestParam MultipartFile avatar) {
        String fileName = "/images/" + avatar.getOriginalFilename();
        try {
            FileCopyUtils.copy(avatar.getBytes(),
                    new File("\\Users\\susu\\Desktop\\Module3\\JSP và JSTL\\Case_Mang_Xa_Hoi\\src\\main\\webapp\\images\\" + fileName)); // coppy ảnh từ ảnh nhận được vào thư mục quy định,
            // đường dẫn ảo là /image/
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        User user1 = (User) httpSession.getAttribute("user");
        user.setId(user1.getId());
        userService.save(user);
        httpSession.setAttribute("user",user);
        ImageUser imageUser =  imageUseService.findByUser(user1.getId());
        imageUser.setUrlImg(fileName);
        imageUseService.save(imageUser);
        ModelAndView modelAndView = new ModelAndView("newsfeed/newsfeed-friends");
        ImageUser imageUser1 = imageUseService.findByUserIdAndStatus(user1.getId());
        List<Friend> friendList = friendService.findAllFriendByIdFr(user1.getId());
        List<ImageUser> imageUserList = new ArrayList<>();
        for(Friend friend:friendList){
            imageUserList.add(imageUseService.findByUserIdAndStatus(friend.getUser().getId()));
        }
        modelAndView.addObject("imgFriend",imageUserList);
        modelAndView.addObject("imgUser",imageUser1);
        modelAndView.addObject("user1",user1);
        modelAndView.addObject("friendS",friendList);
        return modelAndView;
    }
}
