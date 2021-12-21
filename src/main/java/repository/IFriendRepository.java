package repository;

import model.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IFriendRepository extends JpaRepository<Friend,Long> {
    @Query("SELECT f FROM Friend f inner join  f.user u where u.id=:id")
    public List<Friend> findAllFriendById(@Param("id") Long id);
    @Query("SELECT f FROM Friend f where f.status = 1 and f.user.id=:id")
    public List<Friend> findAllFriendByIdUs(@Param("id") Long id);
    @Query("SELECT f FROM Friend f where f.status = 1 and f.idFriendOfUser=:id")
    public List<Friend> findAllFriendByIdFr(@Param("id") Long id);
    @Query("SELECT f FROM Friend f where f.status = 2 and f.idFriendOfUser=:idF")
    public List<Friend> findAllFriendAddById(@Param("idF") Long idF);
    @Query("SELECT f FROM Friend f where f.user.id=:idUs and f.idFriendOfUser=:idF")
    public Friend findFriendByIdUserAndIdFriendOfUser1(@Param("idUs") Long idUs,@Param("idF") Long idF);
    @Query("SELECT f FROM Friend f where f.idFriendOfUser=:idF")
    public List<Friend> findAllFriend(@Param("idF") Long idF);
    public Friend findFriendByUserIdAndIdFriendOfUser(Long userId,Long idUserFriend);
}
