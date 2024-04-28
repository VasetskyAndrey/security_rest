package ru.itmentor.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;


@Repository
public class UserUserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public User read(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        return manager.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void update(User user, String[] selectedRoles) {
        manager.merge(addRolesMas(createUser(user), selectedRoles));
    }

    @Override
    public void update(User user) {
        manager.merge(addRolesUser(user, createUser(user)));
    }

    @Override
    public void create(User user, String[] selectedRoles) {
        manager.persist(addRolesMas(createUser(user), selectedRoles));
    }

    @Override
    public void create(User user) {
        manager.persist(addRolesUser(user, createUser(user)));
    }

    @Override
    public void delete(User user) {
        manager.remove(manager.find(User.class, user.getId()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getList() {
        return manager.createQuery("from User").getResultList();
    }

    @Override
    public List<Role> getRoles() {
        return manager.createQuery("from Role", Role.class).getResultList();
    }

    private User addRolesMas(User user, String[] selectedRoles) {
        List<String> roles = Arrays.asList(selectedRoles);
        getRoles().stream()
                .filter(role -> roles.contains(role.getNameRoles()))
                .forEach(user::addRole);
        return user;
    }

    private User addRolesUser(User oldUser, User newUser) {
        getRoles().stream()
                .filter(roleBD -> oldUser.getRoles().stream().anyMatch(roleUser -> roleBD.getNameRoles().equals(roleUser.getNameRoles())))
                .forEach(newUser::addRole);
        return newUser;
    }

    private User createUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setLastName(user.getLastName());
        newUser.setDepartment(user.getDepartment());
        newUser.setSalary(user.getSalary());
        return newUser;
    }
}
