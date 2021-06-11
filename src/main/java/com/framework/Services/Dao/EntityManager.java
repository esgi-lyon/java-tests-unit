package com.framework.Services.Dao;

import com.framework.Exception.EntityManagerException;
import com.framework.Service;
import com.framework.Services.IEntity;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

/**
 * class EntityManagerProxy : Db operations for a specific entity class
 * TODO : create a connectors classes caller to keep this in a state of singleton
 */
public class EntityManager extends Session implements Service {

    boolean loaded = false;

    Class entityClass;
    org.hibernate.Session session;
    Transaction tx;

    /**
     * Constructor
     *
     * @param entityClass EntityClass reflection information
     */
    public EntityManager(Class entityClass) {
        this.entityClass = entityClass;
        session = getSession();
        load();
    }

    /**
     * [Save Method] ACID custom persist
     *
     * @param en IEntity instance
     * @throws EntityManagerException Internal entity manager proxy exception
     */
    @Transactional
    public final void persist(IEntity en) throws EntityManagerException {
        try {
            startOperation();
            session.saveOrUpdate(en);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            rollback(tx);
            throw new EntityManagerException(e);
        } finally {
            close(session);
        }
    }

    /**
     * [Delete Method] Delete an entity
     *
     * @param en
     * @throws EntityManagerException Internal entity manager proxy exception
     */
    public final void delete(IEntity en) throws EntityManagerException {
        try {
            startOperation();
            session.delete(en);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            rollback(tx);
            throw new EntityManagerException(e);
        } finally {
            close(session);
        }
    }

    /**
     * [Delete Method] Empty a table
     *
     * @throws EntityManagerException Internal exception
     */
    public final void hqlTruncate() throws EntityManagerException {
        startOperation();
        String hql = String.format("delete from %s", entityClass.getSimpleName());

        try {
            session.createQuery(hql).executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception exception) {
            rollback(tx);
            throw new EntityManagerException(exception);
        } finally {
            close(session);
        }
    }

    /**
     * [Read method] Get all from entity
     *
     * @return List of entity concerned by this en manager proxy instance
     * @throws EntityManagerException Internal exception
     */
    public final List<IEntity> getAll() throws EntityManagerException {
        List<IEntity> entities;
        session = getSession();

        try {
            entities = session.createQuery("from " + entityClass.getSimpleName()).list();
        } catch (Exception e) {
            throw new EntityManagerException(e);
        } finally {
            close(session);
        }

        return entities;
    }

    /**
     * [Read method] Find an entity by id
     *
     * @param id Identifier
     * @return IEntity The entity requested
     * @throws EntityManagerException Internal exception
     */
    public final IEntity getById(Long id) throws EntityManagerException {
        Object obj;
        try {
            session = getSession();
            obj = session.load(entityClass, id);
        } catch (Exception e) {
            throw new EntityManagerException(e);
        } finally {
            close(session);
        }

        return (IEntity) obj;
    }

    public final List<IEntity> getByField(String field, String v) throws EntityManagerException {
        List<IEntity> obj;

        try {
            session = getSession();
            obj = session.createQuery(String.format("from %1$s where %2$s=:%2$s", entityClass.getCanonicalName(), field))
                    .setParameter(field, v)
                    .list();
        } catch (Exception e) {
            throw new EntityManagerException(e);
        } finally {
            close(session);
        }

        return obj;
    }

    /**
     * Internal helper to start a transaction
     */
    protected final void startOperation() {
        session = getSession();
        tx = session.beginTransaction();
    }

    /**
     * Get entity class for use with SQL table name
     *
     * @return Class reflection
     */
    public Class getEntityClass() {
        return entityClass;
    }

    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
