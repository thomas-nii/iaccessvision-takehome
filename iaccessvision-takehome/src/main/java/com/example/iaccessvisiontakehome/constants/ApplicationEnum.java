package com.example.iaccessvisiontakehome.constants;

public enum ApplicationEnum {
    APPLICATION_ONE("app1"),
    APPLICATION_TWO("app2");

    ApplicationEnum(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
