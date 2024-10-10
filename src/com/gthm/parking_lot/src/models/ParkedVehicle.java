package com.gthm.parking_lot.src.models;

import com.gthm.parking_lot.src.enums.VehicleType;

import java.time.LocalDateTime;

public class ParkedVehicle {

    String registerNumber;
    LocalDateTime parkedTime;
    VehicleType vehicleType;
    LocalDateTime unParkTime;


    public ParkedVehicle(String registerNumber, LocalDateTime parkedTime , VehicleType vehicleType) {
        this.parkedTime = parkedTime;
        this.registerNumber = registerNumber;
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(LocalDateTime parkedTime) {
        this.parkedTime = parkedTime;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public LocalDateTime getUnParkTime() {
        return unParkTime;
    }

    public void setUnParkTime(LocalDateTime unParkTime) {
        this.unParkTime = unParkTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}