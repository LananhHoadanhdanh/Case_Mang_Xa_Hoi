package service.Comment;

import model.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {
    @Query("select c from Comment c where c.post.id=:id")
    public Iterable<Comment> findAllByPostId(Long id);
}
