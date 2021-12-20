package repository;

import model.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<LikePost, Long> {
    @Query("select l from LikePost l where l.postId.id=:idP and l.user.id=:idUs")
    public Optional<LikePost> findAllByPostIdAndUserId(Long idP, Long idUs);

    @Query("select l from LikePost  l where l.postId.id=:id")
    public Iterable<LikePost> findAllByPostId(Long id);
}
