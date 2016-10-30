package com.training.senla.enums;

/**
 * Created by prokop on 13.10.16.
 */
public enum RoomsSection{
    SINGLE,
    STANDART,
    IMPROVED,
    LUKS;

    public static RoomsSection isExist(String string) {
        for(RoomsSection section : RoomsSection.values()) {
            if(section.toString().equals(string)) {
                return section;
            }
        }
        return null;
    }
}
