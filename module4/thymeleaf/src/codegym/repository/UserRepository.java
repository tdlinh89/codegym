package codegym.repository;

import codegym.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    User getUser(String id);
    List<User> getListUser();
    void saveUser(User user);
    void editUser(User user);
    void deleteUser(User user);
    void saveOrEdit(User user);
}
