package com.example.iaccessvisiontakehome.constants;

public enum EnvironmentEnum {
    DEVELOPMENT("DEV"),
    PRODUCTION("PROD"),
    STAGE("STAGE");

    EnvironmentEnum(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
