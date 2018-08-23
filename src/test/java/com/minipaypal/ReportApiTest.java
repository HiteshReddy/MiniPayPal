package com.minipaypal;

import com.minipaypal.api.BalanceApi;
import com.minipaypal.api.PaySellerApi;
import com.minipaypal.api.ReportApi;
import com.minipaypal.api.UserRestApi;
import com.minipaypal.api.beans.CurrencyType;
import com.minipaypal.api.beans.PaySeller;
import com.minipaypal.api.beans.User;
import com.minipaypal.api.beans.UserType;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportApiTest {

    @Test
    public void generateReportTest() {
        PaySellerApi rest = new PaySellerApi();

        UserRestApi userApi = new UserRestApi();
        User user1 = new User("testUser1", "Bangalore", "testUser1@gmail.com", UserType.BUYER, 100, CurrencyType.RUPEE);
        User user2 = new User("testUser2", "Bangalore", "testUser2@gmail.com", UserType.SELLER, 1000, CurrencyType.RUPEE);
        userApi.createUser(user1);
        userApi.createUser(user2);

        PaySeller pay1 = new PaySeller("123458", "123459", 50, CurrencyType.RUPEE);
        Response response1 = rest.paySeller(pay1);
        assertEquals(201, response1.getStatus());
        PaySeller pay2 = new PaySeller("123458", "123459", 50, CurrencyType.RUPEE);
        Response response2 = rest.paySeller(pay1);
        assertEquals(201, response2.getStatus());

        BalanceApi balanceApi = new BalanceApi();
        Response buyerResponse = balanceApi.getBalance("123458");
        assertEquals(0, (long)buyerResponse.getEntity());
        Response sellerResponse = balanceApi.getBalance("123459");
        assertEquals(1074, (long)sellerResponse.getEntity());

        ReportApi reportApi = new ReportApi();
        Response reportResponse = reportApi.generateReport("123459");
        List<PaySeller> list = (List<PaySeller>)reportResponse.getEntity();
        assertEquals(2, list.size());
    }
}
