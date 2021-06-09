package com.exam2.Service;

import com.exam2.Model.Hotel;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.Dao.EntityManager;
import com.framework.Services.Dao.EntityManagerFactory;
import com.google.inject.Inject;

public class HotelService {
    public EntityManager entityManager;

    @Inject
    public HotelService(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.get(Hotel.class);
    }

    public void saveHotel(Hotel hotel) throws EntityManagerException {
        entityManager.persist(hotel);
    }
}
