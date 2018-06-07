package com.shipserv.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findBookingByUserId() throws JSONException {
        final String actualResponse = restTemplate.getForObject("/users/1/bookings", String.class);
        String expectedValue = "[{\"bookingId\":2,\"fromDate\":\"2018-07-01T13:00:00\"," +
                "\"toDate\":\"2018-07-03T11:00:00\",\"location\":\"London\"},{\"bookingId\":3,\"fromDate\":\"2018-08-11T13:00:00\"," +
                "\"toDate\":\"2018-08-15T11:00:00\",\"location\":\"Palermo\"}]";

        JSONAssert.assertEquals(expectedValue, actualResponse, false);
    }

    @Test
    public void findUserById() throws JSONException {
        final String actualResponse = restTemplate.getForObject("/users/1", String.class);
        String expectedValue = "{\"id\":1,\"userName\":\"abrar\",\"fullName\":\"Adarshpal Brar\"," +
                "\"bookings\":[{\"bookingId\":2,\"fromDate\":\"2018-07-01T13:00:00\",\"toDate\":\"2018-07-03T11:00:00\"," +
                "\"location\":\"London\"},{\"bookingId\":3,\"fromDate\":\"2018-08-11T13:00:00\",\"toDate\":\"2018-08-15T11:00:00\"," +
                "\"location\":\"Palermo\"}]}";
        JSONAssert.assertEquals(expectedValue, actualResponse, false);
    }
}