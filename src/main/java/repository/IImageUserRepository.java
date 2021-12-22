package repository;

import model.entity.ImageUser;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageUserRepository extends JpaRepository<ImageUser,Long> {
    @Query("select img from ImageUser img where img.status=1 and img.user.id=:idU")
    ImageUser findByUserIdAndStatus(@Param("idU") Long idU);

    @Query("select img from ImageUser img where img.user.id=:idU")
    ImageUser findByUser(@Param("idU") Long idU);
}

