package repository;

import model.entity.ImageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageUserRepository extends JpaRepository<ImageUser,Long> {
    @Query("select img from ImageUser img where img.status=1 and img.userId.id=:idU")
    public ImageUser findByUserIdAndStatus(@Param("idU") Long idU);
}
