package com.exam2.Service;


import com.exam2.Model.User;
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
}
