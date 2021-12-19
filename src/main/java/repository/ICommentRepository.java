package repository;

import model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c where c.post.id=:id")
    public Iterable<Comment> findAllByPostId(Long id);
}
