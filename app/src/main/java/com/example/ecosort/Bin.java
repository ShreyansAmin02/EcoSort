package com.example.ecosort;

public class Bin {
    private String type;
    private String location;
    private String capacity;

    public Bin(String type, String location, String capacity) {
        this.type = type;
        this.location = location;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
    public String getCapacity(){return capacity;}
    public void setCapacity(String capacity) {this.capacity = capacity;}
}
