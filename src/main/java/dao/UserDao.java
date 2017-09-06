package dao;

import model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userid);
    User getUser(User user);
}
