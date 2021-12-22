package service.imageUse;

import model.entity.ImageUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IImageUserRepository;

import java.util.Optional;

@Service
public class ImageUseService implements IImageUseService {
    @Autowired
    private IImageUserRepository iImageUserRepository;

    @Override
    public Iterable<ImageUser> findAll() {
        return iImageUserRepository.findAll();
    }

    @Override
    public Optional<ImageUser> findById(Long id) {
        return iImageUserRepository.findById(id);
    }

    @Override
    public ImageUser save(ImageUser imageUser) {
        return iImageUserRepository.save(imageUser);
    }

    @Override
    public void remove(Long id) {
        iImageUserRepository.deleteById(id);
    }

    @Override
    public ImageUser findByUserIdAndStatus(Long idU) {
        return iImageUserRepository.findByUserIdAndStatus(idU);
    }

    @Override
    public ImageUser findByUser(Long id) {
        return iImageUserRepository.findByUser(id);
    }

}
