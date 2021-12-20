package service.like;

import model.entity.LikePost;
import org.springframework.data.jpa.repository.Query;
import service.IGeneralService;

import java.util.Optional;

public interface ILikeService extends IGeneralService<LikePost> {
    @Query("select l from LikePost l where l.post.id=:idP and l.user.id=:idUs")
    public Optional<LikePost> findAllByPostIdAndUserId(Long idP, Long idUs);

    @Query("select l from LikePost  l where l.post.id=:id")
    public Iterable<LikePost> findAllByPostId(Long id);
}
