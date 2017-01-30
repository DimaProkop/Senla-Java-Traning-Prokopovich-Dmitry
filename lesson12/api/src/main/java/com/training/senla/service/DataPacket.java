package com.training.senla.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dmitry on 27.11.16.
 */
public class DataPacket implements Serializable {
    private String header;
    private List<Object> body;

    public DataPacket(String header, List<Object> body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Object> getBody() {
        return body;
    }

    public void setBody(List<Object> body) {
        this.body = body;
    }
}
