package com.training.senla.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModel extends BaseModel implements Serializable{

    private static final long serialVersionUID = -4477116269261501412L;

    private String name;

    private RoomModel roomModel;

    private List<ServiceModel> serviceModelList;

    public GuestModel() {

    }

    public GuestModel(String name) {
        this.name = name;
        this.roomModel = null;
        this.serviceModelList = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    public List<ServiceModel> getServiceModelList() {
        return serviceModelList;
    }

    public void setServiceModelList(List<ServiceModel> serviceModelList) {
        this.serviceModelList = serviceModelList;
    }

}
