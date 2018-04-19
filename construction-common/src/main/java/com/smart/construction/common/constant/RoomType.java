package com.smart.construction.common.constant;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RoomType {

    LIVING_ROOM("L", "Living Room"),
    BEDROOM("B", "Bedroom"),
    KITCHEN("K", "Kitchen"),
    DINNING_ROOM("D", "Dinning Room");

    RoomType(String value, String display) {
        this.value = value;
        this.display = display;
    }

    private String value;
    private String display;

    public String getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    public static RoomType getByValue(String value) {
        return Arrays.stream(RoomType.values()).filter(roomType -> roomType.getValue().equals(value)).collect(Collectors.toList()).get(0);
    }

    public static RoomType getByDisplay(String display) {
        return Arrays.stream(RoomType.values()).filter(roomType -> roomType.getDisplay().equals(display)).collect(Collectors.toList()).get(0);
    }
}
