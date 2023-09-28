package com.example.iaccessvisiontakehome.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "client")
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String ipaddress;

    @Column
    private String application;

    @Column
    private String environment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
