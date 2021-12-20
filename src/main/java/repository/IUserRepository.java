package repository;

import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username like ?1")
    Optional<User> findByUsername(String username);
    @Query("select u from User u where u.fullName like ?1")
    Optional<User> findByFullName(String fullName);
    Iterable<User> findAllByFullNameContaining(String fullName);
}
