package com.exam1.Service;

import com.exam1.Model.MusicalEvent;
import com.exam1.Model.User;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.Dao.EntityManager;
import com.framework.Services.Dao.EntityManagerFactory;
import com.framework.Services.IEntity;
import com.google.inject.Inject;

import java.util.List;

public class MusicalEventService {
    private EntityManager entityManager;

    @Inject
    public MusicalEventService(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.get(MusicalEvent.class);
    }

    public MusicalEvent getOrCreateNotPersisted(String name) throws EntityManagerException {
        List<IEntity> eventList = this.entityManager.getByField("intitule", name);
        if (eventList.isEmpty()) {
            return new MusicalEvent(name);
        }

        return (MusicalEvent) eventList.get(0);
    }

    public User buyEventForUser(User user, MusicalEvent event) throws EntityManagerException {
        user.buyEventTicket(event);
        entityManager.persist(event);
        entityManager.persist(user); // Any entity manager can persist other entities

        return user;
    }
}
