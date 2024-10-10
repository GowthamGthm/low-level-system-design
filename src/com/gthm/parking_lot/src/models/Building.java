package com.gthm.parking_lot.src.models;


import java.util.ArrayList;
import java.util.List;

public class Building {

    String buildingName;
    List<Floor> floor;



    public Building(String buildingName, List<Floor> floor) {
        this.buildingName = buildingName;
        this.floor = floor;

    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<Floor> getFloor() {
        return floor;
    }

    public void setFloor(List<Floor> floor) {
        this.floor = floor;
    }


}