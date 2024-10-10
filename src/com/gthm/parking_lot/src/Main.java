package com.gthm.parking_lot.src;

import com.gthm.parking_lot.src.config.SlotsConfig;
import com.gthm.parking_lot.src.enums.VehicleType;
import com.gthm.parking_lot.src.models.Building;
import com.gthm.parking_lot.src.models.Floor;
import com.gthm.parking_lot.src.models.ParkedVehicle;

import java.io.File;
import java.sql.SQLOutput;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static Floor floor1 = new Floor(1, 100);
    public static Floor floor2 = new Floor(2, 100);
    public static Floor floor3 = new Floor(3, 100);

    public static List<Floor> floorList = Arrays.asList(floor1, floor2, floor3);
    public static Building building = new Building("Vajra Building", floorList);


    public static void main(String[] args) {



        for (; ; ) {

            try {
                String actionType = """
                        
                        1. Park
                        2. UnPark
                        """;

                System.out.println("Select your action: " + actionType);
                Scanner scanner = new Scanner(System.in);
                int actionTypeInt = scanner.nextInt();

                if(actionTypeInt > 2) {
                    continue;
                }


                String actionTypeStr = (actionTypeInt == 1) ? "PARK" : "UNPARK";

                if("PARK".equals(actionTypeStr)) {
                    park();
                } else {
                    unpark();
                }
            } catch (Exception e) {
            }

        }
    }



    private static void unpark() {

        System.out.println("Where have you parked your Vehicle, Which Floor");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        if(i < 0 || i > building.getFloor().size()) {
            System.out.println("Invalid FLoor");
            return;
        }

        System.out.println("Please select the Reg-Number from the LIST Of vehicles \n \n");

        int floorIndex = i -1;
        building.getFloor().get(floorIndex)
                .getParkedVehicles().stream().forEach(ele -> System.out.println(ele.getRegisterNumber()));

        System.out.println("Enter Reg Number: ");
        String regNum = scanner.next();

        Optional<ParkedVehicle> parkedVehicle = building.getFloor()
                                                .get(floorIndex)
                                                .getParkedVehicles()
                                                .stream()
                                                .filter(ele -> ele.getRegisterNumber()
                                                                  .equals(regNum))
                                                .findFirst();

        parkedVehicle.ifPresentOrElse(
                ele -> System.out.println(MessageFormat.format("Vehicle Found for Reg Number: {0} " , regNum)),
                () -> System.out.println(MessageFormat.format("Vehicle Not Found for Reg Number: {1} " , regNum ))
        );

        if(parkedVehicle.isPresent()) {
            List<ParkedVehicle> parkedVehicles = building.getFloor()
                                                         .get(floorIndex)
                                                         .getParkedVehicles()
                                                         .stream()
                                                         .filter(ele -> !ele.getRegisterNumber()
                                                                            .equals(regNum))
                                                         .collect(Collectors.toList());

            building.getFloor().get(floorIndex).setParkedVehicles(parkedVehicles);
            System.out.println(MessageFormat.format("Unparked Vehicle RegNum: {0} " , regNum));

//             parking fees calculation
            ParkedVehicle parkedVehicleObj = parkedVehicle.get();
            LocalDateTime parkedTime = parkedVehicleObj.getParkedTime();
            LocalDateTime unparkTime = LocalDateTime.now();
            long hours = Duration.between(unparkTime, parkedTime)
                                 .toHours() + 1;

            Integer pricePerHourBasedOnVehicle = SlotsConfig.getPriceBasedOnVehicle(parkedVehicleObj.getVehicleType());

            long priceToBePaid = pricePerHourBasedOnVehicle * hours;
            System.out.println("Price to be paid is: " + priceToBePaid);

        }

    }

    private static void park() {

        String vehicleTypeStr = """
                    
                    1. TWO_WHEELER,
                    2. THREE_WHEELER,
                    3. CAR,
                    4. TRUCK,
                    5. BUS,
                    6. HEAVY_DUTY
                    """;

        System.out.println("Select your vehicle: " + vehicleTypeStr);
        Scanner scanner = new Scanner(System.in);
        int vehicleTypeInt = scanner.nextInt();
        if(vehicleTypeInt > 6) {
            return;
        }
        VehicleType vehicleType = getVehicleType(vehicleTypeInt);

        String regNum = "KA01-EH-12" + new Random().nextInt(90);
        ParkedVehicle parkedVehicle = new ParkedVehicle(regNum , LocalDateTime.now() , vehicleType);

        String parkResult = parkInTheFloorWhichIsFree(parkedVehicle, vehicleType);
        System.out.println(parkResult);

    }

    private static String parkInTheFloorWhichIsFree(ParkedVehicle parkedVehicle, VehicleType vehicleType) {

        Integer spaceNeededForvehicle = SlotsConfig.getSpaceNeededForvehicle(vehicleType);

        Floor floor = building.getFloor()
                              .stream()
                              .filter(ele -> {
                                  return ele.getTotalParkingSlots() - Optional.ofNullable(ele.getTakenParkingSlots())
                                                                              .orElse(0) > spaceNeededForvehicle;
                              })
                              .findFirst()
                              .orElse(null);

        if(floor == null) {
           return  building.getFloor().stream()
                    .map(ele -> {
                        int availableSlots = ele.getTotalParkingSlots() - Optional.ofNullable(ele.getTakenParkingSlots()).orElse(0);
                        String s = availableSlots == 0 ? "No Space in Floor: " + ele.getFloorID()
                                                       : "Available Slots in Floor: " + ele.getFloorID() + " is " + availableSlots;
                        return s;
                    }).collect(Collectors.joining(System.lineSeparator()));
        }



        building.getFloor().stream().forEach(ele -> {
            if(ele.getFloorID().equals(floor.getFloorID())) {
                ele.getParkedVehicles().add(parkedVehicle);
                Integer takenParking = Optional.ofNullable(floor.getTakenParkingSlots())
                                               .orElse(0);
                ele.setTakenParkingSlots(takenParking + spaceNeededForvehicle);
            }
        });

        System.out.println(floor.getTakenParkingSlots());
        return MessageFormat.format("Parked the vehicle: {0} , reg-no: {1} in floor: {2}",
                vehicleType.name(), parkedVehicle.getRegisterNumber() , floor.getFloorID());

    }

    private static VehicleType getVehicleType(int vehicleTypeInt) {
        VehicleType vehicleType = null;

        switch (vehicleTypeInt) {
            case 1:
                vehicleType = VehicleType.TWO_WHEELER;
                break;
            case 2:
                vehicleType = VehicleType.THREE_WHEELER;
                break;
            case 3:
                vehicleType = VehicleType.CAR;
                break;
            case 4:
                vehicleType = VehicleType.TRUCK;
                break;
            case 5:
                vehicleType = VehicleType.BUS;
                break;
            case 6:
                vehicleType = VehicleType.HEAVY_DUTY;
                break;
        }
        return vehicleType;
    }

}