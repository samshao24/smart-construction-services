package com.smart.construction.painting.constant;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum MaterialQuality {

    EXCELLENT("Excellent", "E"),
    GOOD("Good", "G"),
    AVERAGE("Average", "A");

    private String display;
    private String code;

    MaterialQuality(String display, String code) {
        this.display = display;
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static MaterialQuality getByCode(String code) {
        return Arrays.stream(MaterialQuality.values())
                .filter(materialQuality -> materialQuality.getCode().equals(code))
                .collect(Collectors.toList()).get(0);

    }
}
