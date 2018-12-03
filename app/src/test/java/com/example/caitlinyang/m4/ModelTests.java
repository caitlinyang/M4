package com.example.caitlinyang.m4;

import com.example.caitlinyang.m4.model.Item;
import com.example.caitlinyang.m4.model.Locations;
import com.example.caitlinyang.m4.model.User;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * ModelTests junits for models
 */
public class ModelTests {

    private static final int TIMEOUT = 200;
    private Locations location;
    private Item item;

    /**
     * Creates instance variables to test
     */
    @Before
    public void setup() {
        location = new Locations(1, "BOYS & GIRLS CLUB W.W. WOOLFOLK",
                33.73182, -84.43971,
                "1642 RICHLAND RD,Atlanta,GA,30332", "Store",
                "(404) 555 - 1234");
        item = new Item("BOYS & GIRLS CLUB W.W. WOOLFOLK", "shirt",
                "Now", "15", "Clothes", "A shirt", "");
    }

    /**
     * Tests userID
     */
    @Test (timeout = TIMEOUT)
    public void testUserId() {
        User user1 = new User("Caitlin", "caitlin@email.com",
                "1234", "User");
        User user2 = new User("Peter", "phan@email.com",
                "1122", "Employee");
        User user3 = new User("Frankie", "fkim@email.com",
                "sue", "Admin");

        assertEquals(2, user1.getId());
        assertEquals(3, user2.getId());
        assertEquals(4, user3.getId());
    }

    /**
     * Tests UserGetter
     */
    @Test (timeout = TIMEOUT)
    public void testUserGetter() {
        User user1 = new User("Caitlin", "caitlin@email.com",
                "1234", "User");
        assertEquals("Caitlin", user1.getName());
        assertEquals("caitlin@email.com", user1.getEmail());
        assertEquals("1234",user1.getPassword());
        assertEquals("User", user1.getUserType());
    }

    /**
     * Tests LocationGetters
     */
    @Test (timeout = TIMEOUT)
    public void testLocationGetter() {
        assertEquals(1, location.getKey());
        assertEquals("BOYS & GIRLS CLUB W.W. WOOLFOLK", location.getLocationName());

    }

    /**
     * Test ItemGetter
     */
    @Test (timeout = TIMEOUT)
    public void testItemGetter() {
        assertEquals("BOYS & GIRLS CLUB W.W. WOOLFOLK", item.getLoc_name());
        assertEquals("shirt", item.getItem_name());
    }

    /**
     * Test ItemSetter
     */
    @Test (timeout = TIMEOUT)
    public void testItemSetter() {
        item.setItem_name("Pants");
        assertEquals("Pants", item.getItem_name());
    }

}
