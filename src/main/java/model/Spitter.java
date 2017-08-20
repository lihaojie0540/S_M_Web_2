package model;

import org.springframework.stereotype.Component;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Component("spitter")
public class Spitter {
    private  int id;
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 character long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumberic with no spaces.")
    private  String username;
    @Size(min = 6, max = 20, message = "The password must be at least 6 characters long.")
    private  String password;
    @Size(min = 3, max = 50, message = "The fullname must be between 3 and 50 characters long.")
    private  String fullname;
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Invalid email address.")
    private  String email;
    private  String userportrait;
    private  boolean updatebyemail;
    private  List<Spittle> spittles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<Spittle> getSpittles() {
        return spittles;
    }

    public void setSpittles(List<Spittle> spittles) {
        this.spittles = spittles;
    }

    public String getUserportrait() {
        return userportrait;
    }

    public void setUserportrait(String userportrait) {
        this.userportrait = userportrait;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUpdatebyemail() {
        return updatebyemail;
    }

    public void setUpdatebyemail(boolean updatebyemail) {
        this.updatebyemail = updatebyemail;
    }
}
