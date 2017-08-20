package dao;

import model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    User getUser(User user);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userid);
}
