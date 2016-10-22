package com.training.senla.util.validator;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;

/**
 * Created by prokop on 18.10.16.
 */
public class Validator {

    public RoomStatus RoomStatusValidator(String string) {
        switch (string) {
            case "MAINTAINED":
                return RoomStatus.MAINTAINED;
            case "FREE":
                return RoomStatus.FREE;
            case "BUSY":
                return RoomStatus.BUSY;
        }

        return null;
    }

    public RoomsSection RoomSectionValidator(String string) {
        switch (string) {
            case "STANDART":
                return RoomsSection.STANDART;
            case "SINGLE":
                return RoomsSection.SINGLE;
            case "LUKS":
                return RoomsSection.LUKS;
            case "IMPROVED":
                return RoomsSection.IMPROVED;
        }

        return null;
    }

    public ServicesSection ServiceSectionValidator(String string) {
        switch (string) {
            case "FOOD":
                return ServicesSection.FOOD;
            case "MANDATORY":
                return ServicesSection.MANDATORY;
            case "PLACE":
                return ServicesSection.PLACE;
        }
        return null;
    }
}
