package com.training.senla.model;

import java.util.Date;

/**
 * Created by dmitry on 1.12.16.
 */
public class Registration extends Base {
    private int guestId;
    private int roomId;
    private Date startDate;
    private Date finalDate;

    public Registration() {
    }

    public Registration(int guestId, int roomId, Date startDate, Date finalDate) {
        this.guestId = guestId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
}
