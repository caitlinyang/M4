package com.example.caitlinyang.m4;

import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.User;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ModelTests {

    private static final int TIMEOUT = 200;
    private Locations location;

    @Before
    public void setup() {
        location = new Locations(1, "BOYS & GILRS CLUB W.W. WOOLFOLK", 33.73182, -84.43971,
                "1642 RICHLAND RD,Atlanta,GA,30332", "Store","(404) 555 - 1234");
    }

    @Test (timeout = TIMEOUT)
    public void testUserId() {
        User user1 = new User("Caitlin", "caitlin@email.com", "1234", "User");
        User user2 = new User("Peter", "phan@email.com", "1122", "Employee");
        User user3 = new User("Frankie", "fkim@email.com", "sue", "Admin");

        assertEquals(user1.getId(), 1);
        assertEquals(user2.getId(), 2);
        assertEquals(user3.getId(), 3);
    }


}
