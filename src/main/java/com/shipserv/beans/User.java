package com.shipserv.beans;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    @Size(min = 5, message = "Name should be at least 5 characters")
    private String fullName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }

    public User() {
        super();
    }

    public User(String userName, @Size(min = 5, message = "Name should be at least 5 characters") String fullName) {
        this.userName = userName;
        this.fullName = fullName;
    }

    public void addBooking(Booking booking) {
        System.out.println("Called Time");
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        this.bookings.add(booking);
        booking.setUser(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUserName(), user.getUserName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUserName());
    }
}


