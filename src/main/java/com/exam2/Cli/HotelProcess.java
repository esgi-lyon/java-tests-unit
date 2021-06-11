package com.exam2.Cli;

// import com.exam2.Model.Hotel;
import com.exam2.Model.User;
import com.exam2.Service.HotelService;
import com.exam2.Service.UserService;
import com.framework.Exception.EntityManagerException;
import com.framework.Utils.ArgsUtils;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Scanner;

public class HotelProcess {

    String userName;
    String eventName;

    @Inject
    HotelService hotelService;

    @Inject
    UserService userService;

    public HotelProcess() {}

    public void process(String[] args) throws EntityManagerException {
        parseArgMap(args);
        System.out.println("You are : " + userName + " and you want to buy tickets for " + eventName);

        User user = this.scanUser();
        // user = hotelService.buyEventForUser(user, hotelService.getOrCreateNotPersisted(eventName));
/*
        System.out.println(
                String.format(
                        "Employee with the best salary of %s is %s",
                        employee
                )
        ); */
    }

    public User scanUser() throws EntityManagerException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Question ? Y/N");
        String choice = String.format("%s", sc.nextLine());

        return null;
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
