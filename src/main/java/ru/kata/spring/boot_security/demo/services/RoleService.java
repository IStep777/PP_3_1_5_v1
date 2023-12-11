package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@Repository
public class RoleService {


    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }


    public Role getRoleForName(String name) {
        return entityManager.createQuery("select r from Role r where r.role =: role", Role.class)
                .setParameter("role", name).getSingleResult();
    }


    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }


    public void addRole(Role role) {
        entityManager.persist(role);
    }
}

