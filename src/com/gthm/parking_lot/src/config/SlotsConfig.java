package com.gthm.parking_lot.src.config;

import com.gthm.parking_lot.src.enums.SlotType;
import com.gthm.parking_lot.src.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class SlotsConfig {

    public static Map<VehicleType, SlotType> VEHICLE_SLOT_TYPE_MAPPING = null;
    public static Map<SlotType, Integer> SLOT_TYPE_SIZE_MAPPING_ON_LAND = null;
    public static Map<VehicleType, Integer> PRICE_MAP_FOR_VEHICLES = null;

    static {
        populateSlotTypesBasedOnVehicle();
        populateSLotSizeBasedOnSize();
        setUpPriceForParkingBasedOnVehicle();
    }

    private static void populateSLotSizeBasedOnSize() {
        SLOT_TYPE_SIZE_MAPPING_ON_LAND = new HashMap<>();

        SLOT_TYPE_SIZE_MAPPING_ON_LAND.put(SlotType.X_SMALL, 1);
        SLOT_TYPE_SIZE_MAPPING_ON_LAND.put(SlotType.SMALL, 2);
        SLOT_TYPE_SIZE_MAPPING_ON_LAND.put(SlotType.MEDIUM, 4);
        SLOT_TYPE_SIZE_MAPPING_ON_LAND.put(SlotType.LARGE, 8);
        SLOT_TYPE_SIZE_MAPPING_ON_LAND.put(SlotType.XLARGE, 16);

    }

    private static void populateSlotTypesBasedOnVehicle() {
        VEHICLE_SLOT_TYPE_MAPPING = new HashMap<>();

        VEHICLE_SLOT_TYPE_MAPPING.put(VehicleType.TWO_WHEELER, SlotType.X_SMALL);
        VEHICLE_SLOT_TYPE_MAPPING.put(VehicleType.THREE_WHEELER, SlotType.SMALL);
        VEHICLE_SLOT_TYPE_MAPPING.put(VehicleType.CAR, SlotType.MEDIUM);
        VEHICLE_SLOT_TYPE_MAPPING.put(VehicleType.BUS, SlotType.LARGE);
        VEHICLE_SLOT_TYPE_MAPPING.put(VehicleType.TRUCK, SlotType.XLARGE);
        VEHICLE_SLOT_TYPE_MAPPING.put(VehicleType.HEAVY_DUTY, SlotType.XLARGE);
    }

    private static void setUpPriceForParkingBasedOnVehicle() {
        PRICE_MAP_FOR_VEHICLES = new HashMap<>();

        PRICE_MAP_FOR_VEHICLES.put(VehicleType.TWO_WHEELER, 10);
        PRICE_MAP_FOR_VEHICLES.put(VehicleType.THREE_WHEELER, 20);
        PRICE_MAP_FOR_VEHICLES.put(VehicleType.CAR, 30);
        PRICE_MAP_FOR_VEHICLES.put(VehicleType.BUS, 40);
        PRICE_MAP_FOR_VEHICLES.put(VehicleType.TRUCK, 50);
        PRICE_MAP_FOR_VEHICLES.put(VehicleType.HEAVY_DUTY, 60);
    }


    public static Integer getSpaceNeededForvehicle(VehicleType vehicleType) {
        SlotType slotType = VEHICLE_SLOT_TYPE_MAPPING.get(vehicleType);
        Integer i = SLOT_TYPE_SIZE_MAPPING_ON_LAND.get(slotType);
        return i;
    }

    public static Integer getPriceBasedOnVehicle(VehicleType vehicleType) {
        Integer price = PRICE_MAP_FOR_VEHICLES.get(vehicleType);
        return price;
    }


}