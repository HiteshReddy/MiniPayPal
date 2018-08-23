package com.minipaypal.manager.user;

import com.minipaypal.api.beans.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TransactionManager {

    private static Map<Integer, Transaction> credits = new HashMap<>();

    /**
     * returns the Credit Transactions details for the given transaction id
     *
     * @param id
     * @return Transaction
     */
    public static Transaction getTransaction(int id) {
        if(credits.containsKey(id)) {
            return credits.get(id);
        }
        return null;
    }

    /**
     * put the new transaction details in the map
     *
     * @param transaction
     */
    public static int addTransactionDetails(Transaction transaction) {
        int tranId = 0;
        synchronized(TransactionManager.class) {
            Random random = new Random();
            tranId = ManagerUtils.generateId();
            while(credits.containsKey(tranId)) {
                tranId = ManagerUtils.generateId();
            }
            credits.put(tranId, transaction);
        }
        return tranId;
    }

}
