package service.post;

import model.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByUser(Long id);
    Iterable<Post> findAllByUserIdPublic(Long id);
    Iterable<Post> findAllByUserIdFriend(Long id);
    Iterable<Post> findAllAndOrderByDateTime();
}
