package com.training.senla.enums;

/**
 * Created by prokop on 13.10.16.
 */
public enum ServicesSection {
    FOOD,
    MANDATORY,
    PLACE;

    public static com.training.senla.enums.ServicesSection isExist(String string) {
        for(com.training.senla.enums.ServicesSection section : values()) {
            if(section.toString().equals(string)) {
                return section;
            }
        }
        return null;
    }
}
