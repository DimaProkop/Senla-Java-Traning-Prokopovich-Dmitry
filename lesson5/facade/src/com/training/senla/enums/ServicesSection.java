package com.training.senla.enums;

/**
 * Created by prokop on 13.10.16.
 */
public enum ServicesSection {
    FOOD,
    MANDATORY,
    PLACE;

    public static ServicesSection isExist(String string) {
        for(ServicesSection section : ServicesSection.values()) {
            if(section.toString().equals(string)) {
                return section;
            }
        }
        return null;
    }
}
