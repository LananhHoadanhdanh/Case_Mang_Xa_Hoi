package model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime dateTime;
    private int status;
    private String image;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Post() {
    }

    public Post(String content, int status, String image) {
        this.content = content;
        this.status = status;
        this.image = image;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Post {" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", user=" + user +
                '}';
    }
}
