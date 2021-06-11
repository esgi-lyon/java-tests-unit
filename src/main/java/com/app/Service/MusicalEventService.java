package com.app.Service;

import com.app.Model.MusicalEvent;
import com.app.Model.User;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.Dao.EntityManager;
import com.framework.Services.Dao.EntityManagerFactory;
import com.framework.Services.IEntity;
import com.google.inject.Inject;

import java.util.List;

public class MusicalEventService {
    private EntityManager entityManager;

    @Inject
    public MusicalEventService(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.get(MusicalEvent.class);
    }

    public MusicalEvent getOrCreateNotPersisted(String name) {
        List<IEntity> eventList = this.entityManager.getByField("intitule", name);
        if (eventList.isEmpty()) {
            return new MusicalEvent(name);
        }

        return (MusicalEvent) eventList.get(0);
    }

    public User buyEventForUser(User user, MusicalEvent event) throws EntityManagerException {
        user.buyEventTicket(event);
        this.entityManager.persist(event);

        return user;
    }
}
