package com.minipaypal.manager.user;

import com.minipaypal.api.beans.PaySeller;

import java.util.*;

public class PaySellerTransactionManager {

    private static Map<Integer, PaySeller> transaction = new HashMap<>();

    public static int addPaySellerTransactionDetails(PaySeller paySeller) {
        int tranId = 0;
        synchronized(PaySellerTransactionManager.class) {
            tranId = ManagerUtils.generateId();
            while(transaction.containsKey(tranId)) {
                tranId = ManagerUtils.generateId();
            }
            paySeller.setTransactionId(tranId);
            transaction.put(tranId, paySeller);
        }
        return tranId;
    }

    public static List<PaySeller> getTransactionList(String accountNumber) {
        List<PaySeller> listOfTransactions = new ArrayList<>();
        for(PaySeller transaction : transaction.values()) {
            if(transaction.getSellerAccountNumber().equals(accountNumber)) {
                listOfTransactions.add(transaction);
            }
        }
        return listOfTransactions;
    }

    public static PaySeller getTransaction(int transactionId) {
        if(transaction.containsKey(transactionId)) {
            return transaction.get(transactionId);
        }
        return null;
    }
}
