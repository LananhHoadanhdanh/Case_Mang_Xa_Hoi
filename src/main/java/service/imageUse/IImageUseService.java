package service.imageUse;

import model.entity.ImageUser;
import org.springframework.data.jpa.repository.Query;
import service.IGeneralService;

public interface IImageUseService extends IGeneralService<ImageUser> {
    @Query("select img from ImageUser img where img.status=1 and img.userId.id=:idU")
    public ImageUser findByUserIdAndStatus(Long idU);
}
