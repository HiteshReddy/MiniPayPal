package com.minipaypal.api.beans;

import javax.xml.bind.annotation.XmlElement;

public class PaySeller {

    @XmlElement
    private String buyerAccountNumber;
    @XmlElement
    private String sellerAccountNumber;
    @XmlElement
    private long amount;
    @XmlElement
    private CurrencyType currency;
    @XmlElement
    private int transactionId;

    public PaySeller() {

    }

    public PaySeller(String buyerAccountNumber, String sellerAccountNumber, long amount, CurrencyType currency) {
        this.buyerAccountNumber = buyerAccountNumber;
        this.sellerAccountNumber = sellerAccountNumber;
        this.amount = amount;
        this.currency = currency;
    }

    public String getBuyerAccountNumber() {
        return buyerAccountNumber;
    }

    public void setBuyerAccountNumber(String buyerAccountNumber) {
        this.buyerAccountNumber = buyerAccountNumber;
    }

    public String getSellerAccountNumber() {
        return sellerAccountNumber;
    }

    public void setSellerAccountNumber(String sellerAccountNumber) {
        this.sellerAccountNumber = sellerAccountNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}