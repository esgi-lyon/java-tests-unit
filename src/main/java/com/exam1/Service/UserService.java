package com.exam1.Service;

import com.exam1.Model.ClassicUser;
import com.exam1.Model.Student;
import com.exam1.Model.User;
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

    public User getOrCreateStudentNotPersisted(String name) throws EntityManagerException {
        List<IEntity> students = this.entityManager.getByField("name", name);
        if (students.isEmpty()) {
            return new Student(name);
        }

        return (Student) students.get(0);
    }

    public User getOrCreateClassicUserNotPersisted(String name) throws EntityManagerException {
        List<IEntity> classicUsers = this.entityManager.getByField("name", name);
        if (classicUsers.isEmpty()) {
            return new ClassicUser(name);
        }

        return (ClassicUser) classicUsers.get(0);
    }
}
