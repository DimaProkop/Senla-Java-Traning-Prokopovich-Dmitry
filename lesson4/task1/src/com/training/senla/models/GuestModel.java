package com.training.senla.models;

import java.util.Date;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModel {
    private int guestId;
    private String name;
    private Date startDate;
    private Date finalDate;
    private RoomModel roomModel;

    public GuestModel() {

    }

    public GuestModel(int guestId, String name, Date startDate, Date finalDate) {
        this.guestId = guestId;
        this.name = name;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }
}
