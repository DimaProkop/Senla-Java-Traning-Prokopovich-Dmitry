package com.training.senla.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RegistrationModel extends BaseModel{
    private int guestId;
    private int roomId;
    private LocalDate startDate;
    private LocalDate finalDate;

    public RegistrationModel() {
    }

    public RegistrationModel(int guestId, int roomId, LocalDate startDate, LocalDate finalDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }
}
