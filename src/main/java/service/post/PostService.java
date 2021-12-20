package service.post;

import model.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IPostRepository;

import java.util.Optional;
@Service
public class PostService implements IPostService{
    @Autowired
    private IPostRepository postRepository;
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllByUserId(Long id) {
        return postRepository.findAllByUserId(id);
    }

    @Override
    public Iterable<Post> findAllByUserIdAndByStatus(Long id) {
        return postRepository.findAllByUserIdAndByStatus(id);
    }
}
