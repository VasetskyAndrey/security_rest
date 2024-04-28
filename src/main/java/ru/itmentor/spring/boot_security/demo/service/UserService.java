package ru.itmentor.spring.boot_security.demo.service;


import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void create(User user, String[] selectedRoles);
    void create(User user);
    User read(Long id);
    User getByName(String name);
    void update(User user, String[] selectedRoles);
    void update(User user);
    void delete(User user);
    List<User> getList();
    List<Role> getRoles();
}
