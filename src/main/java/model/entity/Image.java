package model.entity;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;

    @Column(nullable = false)
    private String linkImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Image() {
    }

    public Image(String linkImage, Long postId) {
        this.linkImage = linkImage;
        this.postId = postId;
    }

    public Image(String linkImage, Long postId, User user) {
        this.linkImage = linkImage;
        this.postId = postId;
        this.user = user;
    }

    public Image(Long id, Long postId, String linkImage, User user) {
        this.id = id;
        this.postId = postId;
        this.linkImage = linkImage;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
