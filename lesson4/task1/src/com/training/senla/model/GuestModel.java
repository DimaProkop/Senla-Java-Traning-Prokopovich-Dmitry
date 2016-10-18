package com.training.senla.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModel extends BaseModel{
    private String name;
    private RoomModel roomModel;
    private List<ServiceModel> serviceModelList;

    public GuestModel() {

    }

    public GuestModel(String name, List<ServiceModel> serviceModelList) {
        this.name = name;
        this.roomModel = null;
        this.serviceModelList = serviceModelList;
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
