package com.app.Service;

import com.app.Model.ClassicUser;
import com.app.Model.MusicalEvent;
import com.app.Model.Student;
import com.app.Model.User;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.Dao.EntityManager;
import com.framework.Services.Dao.EntityManagerFactory;
import com.framework.Services.IEntity;
import com.google.inject.Inject;

import java.util.List;

public class UserService {
    private EntityManager entityManager;

    @Inject
    public UserService(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.get(User.class);
    }

    public void persistUser(User user) throws EntityManagerException {
        entityManager.persist(user);
    }

    public User getOrCreateStudentNotPersisted(String name) {
        List<IEntity> students = this.entityManager.getByField("name", name);
        if (students.isEmpty()) {
            return new Student(name);
        }

        return (Student) students.get(0);
    }

    public User getOrCreateClassicUserNotPersisted(String name) {
        List<IEntity> classicUsers = this.entityManager.getByField("name", name);
        if (classicUsers.isEmpty()) {
            return new Student(name);
        }

        return (ClassicUser) classicUsers.get(0);
    }
}
