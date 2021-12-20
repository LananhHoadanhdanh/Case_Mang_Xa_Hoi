package repository;

import model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.user.id=:id")
    public Iterable<Post> findAllByUserId(Long id);

    @Query("select p from Post p where p.user.id=:id and p.status =1")
    public Iterable<Post> findAllByUserIdAndByStatus(Long id);

    @Query("select p from Post p where p.id=:id")
    public Optional<Post> findById(Long id);
}
