package model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private Long idFriendOfUser;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Friend() {
    }

    public Friend(LocalDateTime createAt, int status, User user, Long idFriendOfUser) {
        this.createAt = createAt;
        this.status = status;
        this.user = user;
        this.idFriendOfUser = idFriendOfUser;
    }

    public Friend(LocalDateTime createAt, int status, Long idFriendOfUser, User user) {
        this.createAt = createAt;
        this.status = status;
        this.idFriendOfUser = idFriendOfUser;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdFriendOfUser() {
        return idFriendOfUser;
    }

    public void setIdFriendOfUser(Long idFriendOfUser) {
        this.idFriendOfUser = idFriendOfUser;
    }
}
