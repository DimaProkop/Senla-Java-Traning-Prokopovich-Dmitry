package com.training.senla.service;

import java.io.Serializable;

/**
 * Created by dmitry on 27.11.16.
 */
public class DataPacket implements Serializable {
    private String header;
    private Object body;

    public DataPacket(String header, Object body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
