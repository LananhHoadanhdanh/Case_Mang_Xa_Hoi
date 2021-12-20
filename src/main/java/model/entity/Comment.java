package model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Date dateComment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    public Comment(Long id, String comment, Date dateComment, User user) {
        this.id = id;
        this.comment = comment;
        this.dateComment = dateComment;
        this.user = user;
    }

    public Comment(String comment, Date dateComment, User user, Post post) {
        this.comment = comment;
        this.dateComment = dateComment;
        this.user = user;
        this.post = post;
    }

    public Comment(String comment, Date dateComment, User user) {
        this.comment = comment;
        this.dateComment = dateComment;
        this.user = user;
    }

    public Comment(){

    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
