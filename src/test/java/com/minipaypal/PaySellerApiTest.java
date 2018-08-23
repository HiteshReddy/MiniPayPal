package com.minipaypal;

import com.minipaypal.api.BalanceApi;
import com.minipaypal.api.PaySellerApi;
import com.minipaypal.api.UserRestApi;
import com.minipaypal.api.beans.CurrencyType;
import com.minipaypal.api.beans.PaySeller;
import com.minipaypal.api.beans.User;
import com.minipaypal.api.beans.UserType;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class PaySellerApiTest {

    @Test
    public void testPaySeller() {
        PaySellerApi rest = new PaySellerApi();

        UserRestApi userApi = new UserRestApi();
        User user1 = new User("testUser1", "Bangalore", "testUser1@gmail.com", UserType.BUYER, 100, CurrencyType.RUPEE);
        User user2 = new User("testUser2", "Bangalore", "testUser2@gmail.com", UserType.SELLER, 1000, CurrencyType.RUPEE);
        userApi.createUser(user1);
        userApi.createUser(user2);

        PaySeller pay = new PaySeller("123458", "123459", 50, CurrencyType.RUPEE);
        Response response = rest.paySeller(pay);
        assertEquals(201, response.getStatus());

        BalanceApi balanceApi = new BalanceApi();
        Response buyerResponse = balanceApi.getBalance("123458");
        assertEquals(50, (long)buyerResponse.getEntity());
        Response sellerResponse = balanceApi.getBalance("123459");
        assertEquals(1037, (long)sellerResponse.getEntity());
    }

}
