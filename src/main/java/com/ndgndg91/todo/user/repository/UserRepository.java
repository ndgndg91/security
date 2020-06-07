package com.ndgndg91.todo.user.repository;

import com.ndgndg91.todo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public void createUser(User user) {
        em.persist(user);
    }

    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(em.createQuery("SELECT u FROM User u WHERE u.username =: username", User.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
