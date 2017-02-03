package com.training.senla.model;

import java.io.Serializable;

/**
 * Created by prokop on 19.10.16.
 */
public abstract class BaseModel implements Serializable{


    private static final long serialVersionUID = 4532534381263371721L;

    public abstract int getId();

    public abstract void setId(int id);
}
