package com.gthm.parking_lot.src.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    Integer floorID;

    Integer totalParkingSlots;
    Integer availableParkingSlots;
    Integer takenParkingSlots;

    List<ParkedVehicle> parkedVehicles ;


    public Floor(Integer floorID, Integer totalParkingSlots) {
        this.floorID = floorID;
        this.totalParkingSlots = totalParkingSlots;
        parkedVehicles = new ArrayList<>();
    }

    public Integer getAvailableParkingSlots() {
        return availableParkingSlots;
    }

    public void setAvailableParkingSlots(Integer availableParkingSlots) {
        this.availableParkingSlots = availableParkingSlots;
    }

    public Integer getFloorID() {
        return floorID;
    }

    public void setFloorID(Integer floorID) {
        this.floorID = floorID;
    }

    public Integer getTakenParkingSlots() {
        return takenParkingSlots;
    }

    public void setTakenParkingSlots(Integer takenParkingSlots) {
        this.takenParkingSlots = takenParkingSlots;
    }

    public Integer getTotalParkingSlots() {
        return totalParkingSlots;
    }

    public void setTotalParkingSlots(Integer totalParkingSlots) {
        this.totalParkingSlots = totalParkingSlots;
    }

    public List<ParkedVehicle> getParkedVehicles() {
        return parkedVehicles;
    }

    public void setParkedVehicles(List<ParkedVehicle> parkedVehicles) {
        this.parkedVehicles = parkedVehicles;
    }
}