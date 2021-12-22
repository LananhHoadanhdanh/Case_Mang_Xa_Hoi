package service.user;

import model.entity.User;
import service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>{
    Optional<User> findByUserName(String username);

    Optional<User> findByFullName(String fullName);

    Iterable<User> findAllByFullNameContaining(String fullName);

    Optional<User> findByEmailAndPassword(String email,String password);
    User findUserById(Long idU);
}
