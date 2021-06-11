package com.app.Cli;

import com.app.Model.User;
import com.app.Service.MusicalEventService;
import com.app.Service.UserService;
import com.framework.Exception.EntityManagerException;
import com.framework.Utils.ArgsUtils;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Scanner;

public class BuyEventProcess {

    String userName;
    String eventName;

    @Inject
    MusicalEventService musicalEventService;

    @Inject
    UserService userService;

    public BuyEventProcess() {}

    public void process(String[] args) throws EntityManagerException {
        parseArgMap(args);
        System.out.println("You are : " + userName + " and you want to buy tickets for " + eventName);

        User user = this.scanUser();
        user = musicalEventService.buyEventForUser(user, musicalEventService.getOrCreateNotPersisted(eventName));

        System.out.println(
                String.format(
                        "Successfully added event %s to user %s for the price of %s",
                        user.getMusicalEvent().getIntitule(),
                        user.getName(),
                        user.getMusicalEvent().getPrix()
                )
        );
    }

    public User scanUser() throws EntityManagerException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you a student ? Y/N");
        String choix = String.format("%s", sc.nextLine());
        if (choix.matches("[Y-y]")) {
            return userService.getOrCreateStudentNotPersisted(userName);
        } else {
            return userService.getOrCreateClassicUserNotPersisted(userName);
        }
    }

    public void parseArgMap(String[] args) {
        HashMap<String, String> argMap = ArgsUtils.toMap(args);
        userName = argMap.get("name");
        eventName = argMap.get("event");
    }

    public static void printHelp() {
        System.out.println("Help: \n usage: ./cli.jar -name Jean -event Soenda");
    }
}
