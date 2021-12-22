package model.entity;

import javax.persistence.*;

@Entity
public class ImageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int status;

    private String urlImg;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ImageUser() {
    }

    public ImageUser(int status, String urlImg) {
        this.status = status;
        this.urlImg = urlImg;
    }

    public ImageUser(int status, String urlImg, User user) {
        this.status = status;
        this.urlImg = urlImg;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
