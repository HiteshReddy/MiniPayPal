package com.minipaypal;

import com.minipaypal.api.UserRestApi;
import com.minipaypal.api.beans.CurrencyType;
import com.minipaypal.api.beans.User;
import com.minipaypal.api.beans.UserType;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class UserRestApiTest {

    @Test
    public void testCreateUser() {
        User user = new User("testUser", "Bangalore", "testUser@gmail.com", UserType.BUYER, 100, CurrencyType.RUPEE);
        UserRestApi rest = new UserRestApi();
        Response response = rest.createUser(user);
        assertEquals(201, response.getStatus());
    }

    @Test
    public void testGetUser() {
        User user = new User("testUser1", "Bangalore", "testUser1@gmail.com", UserType.BUYER, 100, CurrencyType.RUPEE);
        UserRestApi rest = new UserRestApi();
        rest.createUser(user);

        Response response = rest.getUser("123458");
        User newCreatedUser = (User)response.getEntity();
        assertEquals(user.getEmail(),newCreatedUser.getEmail());
        assertEquals(user.getAmount(), newCreatedUser.getAmount());
    }

    @Test
    public void testDuplicateUser() {
        User user1 = new User("testUser1", "Bangalore", "testUser1@gmail.com", UserType.BUYER, 100, CurrencyType.RUPEE);
        UserRestApi rest = new UserRestApi();
        rest.createUser(user1);

        User user2 = new User("testUser1", "Bangalore", "testUser1@gmail.com", UserType.BUYER, 100, CurrencyType.RUPEE);
        Response response = rest.createUser(user2);
        assertEquals(409, response.getStatus());
    }
}
