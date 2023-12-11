package ru.kata.spring.boot_security.demo.services;




import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(long id);

    public void deleteUser(long id);

    public User findByUsername(String username);
}
