package com.smart.construction.painting.constant;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RoomType {

    KITCHEN("K", "Kitchen"),
    LIVING_ROOM("L", "Living Room"),
    BEDROOM("B", "Bedroom"),
    DINING_ROOM("D", "Dining Room");

    private String value;
    private String display;

    RoomType(String value, String display) {
        this.value = value;
        this.display = display;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public static RoomType getByValue(String code) {
        return Arrays.stream(RoomType.values())
                .filter(roomType -> roomType.getValue().equals(code))
                .collect(Collectors.toList()).get(0);

    }
}
