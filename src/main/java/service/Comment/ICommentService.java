package service.Comment;

import model.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findAllByPostId(Long id);
}
