package com.app.Cli;

import com.app.Model.ClassicUser;
import com.app.Model.MusicalEvent;
import com.app.Model.Student;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.Dao.EntityManager;
import com.framework.Services.IEntity;

import java.util.List;

public class Fixtures {
    private EntityManager enEvent = new EntityManager(MusicalEvent.class);
    private EntityManager enStudent = new EntityManager(Student.class);
    private EntityManager enClassicUser = new EntityManager(ClassicUser.class);

    public Fixtures() throws EntityManagerException {
        this.enEvent.persist(new MusicalEvent("Soenda Festival"));

        this.enStudent.persist(new Student("Jojo"));
        this.enStudent.persist(new Student("Toto"));
        this.enClassicUser.persist(new ClassicUser("Tata"));
    }

    public List<IEntity> getStudents() throws EntityManagerException {
        return this.enStudent.getAll();
    }

    public List<IEntity> getClassicUsers() throws EntityManagerException {
        return this.enClassicUser.getAll();
    }

    public List<IEntity> getMusicalEvents() throws EntityManagerException {
        return this.enEvent.getAll();
    }
}
