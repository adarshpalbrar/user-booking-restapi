package com.shipserv.controller;

import com.shipserv.beans.Booking;
import com.shipserv.beans.User;
import com.shipserv.exceptions.UserNotFoundException;
import com.shipserv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/{id}/bookings")
    public List<Booking> findBookingByUserId(@PathVariable Integer id) {
        final User user = userService.findById(id);
        if(user.getId() != null) {
            return user.getBookings();
        }
        throw new UserNotFoundException("User with id: " + id + " not found");
    }

    @GetMapping("/users/{id}")
    public User findUserByd(@PathVariable Integer id) {
        final User user = userService.findById(id);
        if(user.getId() != null) {
            return user;
        }
        throw new UserNotFoundException("User with id: " + id + " not found");
    }
}
