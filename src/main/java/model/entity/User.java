package model.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
//    @Size(min = 2, max = 30, message = "độ dài ít nhất là 2 và không vượt quá 30 kí tự")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(length = 8, nullable = false)
    @NotNull
//    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    private String password;

    @Column(nullable = false)
    @NotNull
    private String fullName;

    private String address;

    @Column(nullable = false)
    private String email;

    private String numberPhone;

    @Column(nullable = false)
    private String gender;

    @OneToMany
    private List<ImageUser> imgUrl;

    private Date dateOfBirth;

    public User() {
    }

    public User(String username, String password, String fullName, String address, String email, String numberPhone, String gender, List<ImageUser> imgUrl, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.numberPhone = numberPhone;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<ImageUser> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<ImageUser> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

