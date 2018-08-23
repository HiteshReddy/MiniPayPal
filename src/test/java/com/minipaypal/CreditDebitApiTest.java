package com.minipaypal;

import com.minipaypal.api.BalanceApi;
import com.minipaypal.api.CreditDebitApi;
import com.minipaypal.api.UserRestApi;
import com.minipaypal.api.beans.*;
import com.minipaypal.manager.user.UserManager;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CreditDebitApiTest {

    static UserRestApi rest = new UserRestApi();

    @BeforeClass
    public static void beforeClass() {
        User user1 = new User("testUser1", "Bangalore", "testUser1@gmail.com", UserType.BUYER, 100, CurrencyType.RUPEE);
        rest.createUser(user1);
    }

    @Test
    public void testCreditApi() {
        User user1 = UserManager.getUser("123458");
        CreditDebitApi rest = new CreditDebitApi();
        Transaction transaction = new Transaction("123458", 100, CurrencyType.RUPEE, TransactionType.CREDIT);
        Response response = rest.creditAmount(transaction);

        BalanceApi balanceApi = new BalanceApi();
        Response balanceResponse = balanceApi.getBalance("123458");
        assertEquals(150, (long)balanceResponse.getEntity());
    }

    @Test
    public void testDebitApi() {
        CreditDebitApi rest = new CreditDebitApi();
        Transaction transaction = new Transaction("123458", 50, CurrencyType.RUPEE, TransactionType.DEBIT);
        Response response = rest.debitAmount(transaction);

        BalanceApi balanceApi = new BalanceApi();
        Response balanceResponse = balanceApi.getBalance("123458");
        assertEquals(50, (long)balanceResponse.getEntity());
    }

}
