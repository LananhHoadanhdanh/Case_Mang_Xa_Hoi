package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.post.IPostService;

@Controller
public class PostController {
    @Autowired
    private IPostService postService;
}
