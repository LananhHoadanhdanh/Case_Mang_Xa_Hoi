package service.Comment;

import model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ICommentRepository;

import java.util.Optional;
@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findAllByPostId(Long id) {
        return commentRepository.findAllByPostId(id);
    }

}
