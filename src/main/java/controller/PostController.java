package controller;

import model.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import service.post.IPostService;

@Controller
public class PostController {
    @Autowired
    private IPostService postService;

    @PostMapping("/post")
    public String createNewPost(Post post){
        postService.save(post);
        return "redirect:";
    }

    @PostMapping("/update-post")
    public String updatePost(Post post){
        postService.save(post);
        return "redirect:";
    }

}
