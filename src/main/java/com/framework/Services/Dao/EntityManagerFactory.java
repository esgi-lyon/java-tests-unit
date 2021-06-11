package com.framework.Services.Dao;

public class EntityManagerFactory {
    public EntityManager get(Class zclass) {
        return new EntityManager(zclass);
    }
}
