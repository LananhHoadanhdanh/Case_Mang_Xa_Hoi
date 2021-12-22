package service.imageUse;

import model.entity.ImageUser;
import model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.IGeneralService;

public interface IImageUseService extends IGeneralService<ImageUser> {
    ImageUser findByUserIdAndStatus(Long idU);
    ImageUser findByUser(Long id);
}
