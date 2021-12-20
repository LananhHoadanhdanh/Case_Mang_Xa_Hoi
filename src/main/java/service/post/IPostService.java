package service.post;

import model.entity.Post;
import org.springframework.data.jpa.repository.Query;
import service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    @Query("select p from Post p where p.user.id=:id")
    public Iterable<Post> findAllByUserId(Long id);

    @Query("select p from Post p where p.user.id=:id and p.status =1")
    public Iterable<Post> findAllByUserIdAndByStatus(Long id);
}
