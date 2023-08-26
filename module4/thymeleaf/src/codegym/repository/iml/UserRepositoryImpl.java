package codegym.repository.iml;

import codegym.entity.User;
import codegym.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static HashMap<String, User> users = new HashMap<>();

    static {
        users.put("US1", new User("US1", "Hảo", 22, "Đà Nẵng"));
        users.put("US2", new User("US2", "Tam", 21, "Da Nang"));
        users.put("US3", new User("US3", "Hien", 23, "Da Nang"));
        users.put("US4", new User("US4", "Thang", 20, "Da Nang"));
        users.put("US5", new User("US5", "Nam", 19, "Da Nang"));
    }

    @Override
    public User getUser(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getListUser() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void editUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
    }

    @Override
    public void deleteUser(User user) {
        if (users.containsKey(user.getId())) {
            users.remove(user.getId());
        }
    }

    @Override
    public void saveOrEdit(User user) {
        users.put(user.getId(), user);
    }
}

