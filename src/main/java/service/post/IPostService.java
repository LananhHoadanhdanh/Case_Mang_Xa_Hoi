package service.post;

import model.entity.Post;
import org.springframework.data.jpa.repository.Query;
import service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByUser(Long id);
    Iterable<Post> findAllByUserIdAndByStatus(Long id);
}
