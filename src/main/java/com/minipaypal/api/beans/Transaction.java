package com.minipaypal.api.beans;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Transaction implements Serializable {

    @XmlElement
    private String accountNumber;
    @XmlElement
    private long amount;
    @XmlElement
    private CurrencyType currency;
    @XmlElement
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(String accountNumber, long amount, CurrencyType currency, TransactionType type) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currency = currency;
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}