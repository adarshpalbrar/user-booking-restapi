package com.shipserv;

import com.shipserv.beans.Booking;
import com.shipserv.beans.User;
import com.shipserv.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Locale;

@SpringBootApplication
public class UserBookingsRestApiApplication implements CommandLineRunner {

    private static final LocalTime CHECKIN_LOCAL_TIME = LocalTime.of(13, 0, 0);
    private static final LocalTime CHECKOUT_LOCAL_TIME = LocalTime.of(11, 0, 0);

    private final UserRepository userRepository;

    @Autowired
    public UserBookingsRestApiApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserBookingsRestApiApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale/messages");
        return messageSource;
    }

    @Override
    public void run(String... args) {

        final User user = new User();
        user.setFullName("Adarshpal Brar");
        user.setUserName("abrar");


        final Booking londonBooking = new Booking();
        londonBooking.setLocation("London");
        londonBooking.setFromDate(LocalDateTime.of(LocalDate.of(2018, Month.JULY, 1), CHECKIN_LOCAL_TIME));
        londonBooking.setToDate(LocalDateTime.of(LocalDate.of(2018, Month.JULY, 3), CHECKOUT_LOCAL_TIME));

        final Booking sicilyBooking = new Booking();
        sicilyBooking.setLocation("Palermo");
        sicilyBooking.setFromDate(LocalDateTime.of(LocalDate.of(2018, Month.AUGUST, 11), CHECKIN_LOCAL_TIME));
        sicilyBooking.setToDate(LocalDateTime.of(LocalDate.of(2018, Month.AUGUST, 15), CHECKOUT_LOCAL_TIME));

        user.addBooking(londonBooking);
        user.addBooking(sicilyBooking);
        userRepository.save(user);
    }
}
