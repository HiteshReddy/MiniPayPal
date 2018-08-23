package com.minipaypal.api.beans;

import javax.xml.bind.annotation.XmlElement;

public class TransactionResponse {

    @XmlElement
    private String type;
    @XmlElement
    private int id;

    public TransactionResponse(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }
}
