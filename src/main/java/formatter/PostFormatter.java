package formatter;

import model.entity.Post;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import service.post.IPostService;
import service.user.IUserService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class PostFormatter implements Formatter<Post> {
    private IPostService postService;

    @Autowired
    public PostFormatter(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public Post parse(String text, Locale locale) throws ParseException {
        Optional<Post> postServiceOptional = postService.findById(Long.parseLong(text));
        return postServiceOptional.orElse(null);
    }

    @Override
    public String print(Post object, Locale locale) {
        return "[" + object.getId() + ", " + object.getContent() + object.getStatus() + ", " + object.getUser().getId() + ", " + object.getDateTime() + ", " + object.getImage() + "]";
    }
}
