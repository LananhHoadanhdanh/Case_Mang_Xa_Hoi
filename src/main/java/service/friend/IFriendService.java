package service.friend;

import model.entity.Friend;
import service.IGeneralService;

import java.util.List;


public interface IFriendService extends IGeneralService<Friend> {
    public List<Friend> findAllFriendByIdUs(Long id);
    public List<Friend> findAllFriendById(Long id);
    public List<Friend> findAllFriendAddById(Long idF);
    public Friend findFriendByIdUserAndIdFriendOfUser1(Long idUs, Long idF);
    public List<Friend> findAllFriendByIdFr(Long id);
    public Friend findFriendByUserIdAndIdFriendOfUser(Long userId, Long idUserFriend);
}
