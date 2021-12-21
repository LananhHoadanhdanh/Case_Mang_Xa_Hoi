package service.imageUse;

import model.entity.ImageUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.IGeneralService;

public interface IImageUseService extends IGeneralService<ImageUser> {
    public ImageUser findByUserIdAndStatus(Long idU);
}
