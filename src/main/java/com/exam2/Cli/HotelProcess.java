package com.exam2.Cli;

import com.exam2.Model.*;
import com.exam2.Service.HotelService;
import com.exam2.Service.UserService;
import com.framework.Exception.EntityManagerException;
import com.google.inject.Inject;

public class HotelProcess {

    @Inject
    HotelService hotelService;

    @Inject
    UserService userService;

    public HotelProcess() {}

    public void process(String[] args) throws EntityManagerException {

        Director director1 = new Director("Jojo", "lala");
        Director director2 = new Director("Didier", "Dé");

        Employee employee1 = new Employee("Alain","Té", 2000, 2,  director1);
        Employee employee2 = new Employee("Olivier","Giroud", 10, 2,  director2);
        Employee employee3 = new Employee("Karim","Benz", 50, 2,  director1);

        this.userService.createUser(director1);
        this.userService.createUser(director2);

        this.userService.createUser(employee1);
        this.userService.createUser(employee2);
        this.userService.createUser(employee3);

        Hotel hotel = new Hotel("Hotel 1", "08888888", "8 RUE");

        Room room = new Room(1, 4);
        room.setHotel(hotel);
        this.hotelService.saveHotel(hotel);
        hotel.addRoom(room);

        this.hotelService.entityManager.persist(room);
        this.hotelService.entityManager.persist(hotel);

        System.out.println(
                "Printing hotel " +
                        this.hotelService.entityManager.getByField(
                "name",
                "Hotel 1"
            ).get(1)
        );

        director1.setHotel(hotel);

        // UPDATE
        this.userService.entityManager.persist(director1);
        this.userService.entityManager.persist(room);
        this.userService.entityManager.persist(hotel);

        System.out.println(this.userService.getUserWithBestSalary());
    }

    public static void printHelp() {
        System.out.println("Help: \n usage: ./cli.jar -name Jean -event Soenda");
    }
}
