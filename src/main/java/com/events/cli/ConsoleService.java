package com.events.cli;

import com.events.Model.ClassicUser;
import com.events.Model.MusicalEvent;
import com.events.Model.Student;
import com.events.Model.User;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.EntityManager;

import java.util.Scanner;

public class ConsoleService {
    private EntityManager enStudent = new EntityManager(Student.class);
    private EntityManager enClassicUser = new EntityManager(ClassicUser.class);
    private EntityManager enEvent = new EntityManager(MusicalEvent.class);

    String userName;
    String eventName;

    User fetchUser() throws EntityManagerException {
        Scanner sc = new Scanner(System.in);
        System.out.println("êtes vous étudiant ? Y/N");
        String choix = sc.nextLine();

        if (choix == "Y") {
            Student student = (Student) this.enStudent.getByField("name", this.userName);
            if (student == null) {
                student = new Student(this.userName);
            }

            return student;
        } else {
            ClassicUser classicUser = (ClassicUser) this.enClassicUser.getByField("name", this.userName);
            if (classicUser == null) {
                classicUser = new ClassicUser(this.userName);
            }

            return classicUser;
        }
    }

    public MusicalEvent fetchEvent() throws EntityManagerException {
        MusicalEvent event = (MusicalEvent) this.enEvent.getByField("name", this.userName);
        if (event == null) {
            event = new MusicalEvent(this.userName);
        }

        return event;
    }

    public ConsoleService(String userName, String eventName) throws EntityManagerException {
        this.eventName = eventName;
        this.userName = userName;

        final User user = this.fetchUser();
        final MusicalEvent event = this.fetchEvent();

        user.buyEventTicket(event);
        this.enEvent.persist(event);

        if (user instanceof ClassicUser) {
            this.enClassicUser.persist(user);
        }
        else if (user instanceof Student) {
            this.enStudent.persist((user));
        }
    }
}
