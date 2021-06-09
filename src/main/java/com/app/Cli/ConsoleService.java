package com.app.Cli;

import com.app.Model.ClassicUser;
import com.app.Model.MusicalEvent;
import com.app.Model.Student;
import com.app.Model.User;
import com.framework.Exception.EntityManagerException;
import com.framework.Services.EntityManager;
import com.framework.Services.IEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {
    private EntityManager enStudent = new EntityManager(Student.class);
    private EntityManager enClassicUser = new EntityManager(ClassicUser.class);
    private EntityManager enEvent = new EntityManager(MusicalEvent.class);

    HashMap<String, String> argMap;
    String userName;
    String eventName;

    public ConsoleService() {}

    public ConsoleService(String[] args) throws EntityManagerException {
        argMap = this.parseArgs(args);
        userName = argMap.get("name");
        eventName = argMap.get("event");
        System.out.println("You are : " + userName + " and you want to buy tickets for " + eventName);
        this.userBuyEventAction();
    }

    void userBuyEventAction() throws EntityManagerException {
        User user = this.fetchUser();
        MusicalEvent event = this.fetchEvent();

        user.buyEventTicket(event);
        this.enEvent.persist(event);
        System.out.println(user.getClass());
        if (user instanceof ClassicUser) {
            this.enClassicUser.persist(user);
        } else if (user instanceof Student) {
            this.enStudent.persist(user);
        }
        System.out.println(user.toString());
        System.out.println(event.toString());

        System.out.println(
                String.format(
                        "Successfully added event %s to user %s for the price of %s",
                        user.getMusicalEvent().getIntitule(),
                        user.getName(),
                        user.getMusicalEvent().getPrix()
                )
        );
    }

    public User fetchUser() {
        Scanner sc = new Scanner(System.in);
        String choix = String.format("%s", sc.nextLine());
        if (choix.matches("[Y-y]")) {
            System.out.println("You are a student");
            List<IEntity> studentList = this.enStudent.getByField("name", this.userName);
            if (studentList.isEmpty()) {
                return new Student(this.userName);
            }

            return (Student) studentList.get(0);
        } else {
            System.out.println("You are a classic user");
            List<IEntity> classicUserList = this.enClassicUser.getByField("name", this.userName);
            if (classicUserList.isEmpty()) {
                return new ClassicUser(this.userName);
            }

            return (ClassicUser) classicUserList.get(0);
        }
    }

    public MusicalEvent fetchEvent() {
        List<IEntity> eventList = this.enEvent.getByField("intitule", this.userName);
        if (eventList.isEmpty()) {
            return new MusicalEvent(this.eventName);
        }

        return (MusicalEvent) eventList.get(0);
    }

    public static HashMap<String, String> parseArgs(String[] args)  {
        HashMap<String, String> argMap = new HashMap<>();

        if (args.length < 1) {
            ConsoleService.printHelp();
            throw new IllegalArgumentException("No argument passed");
        }

        String storeKey = null;

        for (String arg : args) {
            if (arg.charAt(0) == '-' && storeKey == null) {
                storeKey = arg.substring(1);
                continue;
            }

            if (storeKey != null) {
                argMap.put(storeKey, arg);
                storeKey = null;
            } else {
                ConsoleService.printHelp();
                throw new IllegalArgumentException("Review argument passed");
            }
        }

        return argMap;
    }

    protected static void printHelp() {
        System.out.println("Help: \n usage: ./cli.jar -name Jean -event Soenda");
    }
}