package com.training.senla.model;

import java.io.Serializable;

/**
 * Created by prokop on 19.10.16.
 */
public class BaseModel implements Serializable{


    private static final long serialVersionUID = 4532534381263371721L;
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
