package repository;

import model.entity.Comment;
import model.entity.ImageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c where c.post.id=:id")
    Iterable<Comment> findAllByPostId(@Param("id") Long id);
}
