package repository;

import model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.user.id=:id order by p.dateTime desc")
    Iterable<Post> findAllByUser(@Param("id") Long id);

    @Query("select p from Post p order by p.dateTime desc")
    Iterable<Post> findAllAndOrderByDateTime();

    @Query("select p from Post p where p.user.id=:id and p.status = 1 order by p.dateTime desc")
    Iterable<Post> findAllByUserIdPublic(@Param("id") Long id);

    @Query("select p from Post p where p.user.id=:id and p.status <> 3 order by p.dateTime desc")
    Iterable<Post> findAllByUserIdFriend(@Param("id") Long id);
}
