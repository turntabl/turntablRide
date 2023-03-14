package com.ttrides.turntablRides.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Ride {

    @Id
    private String Id;

    private Rider rider;

    private RideDetails rideDetails;


    private class Rider {

        @Id
        private String Id;

        private User user;

        private boolean isDriving;

        private Vehicle vehicle;

        private boolean booked;


        private class Vehicle {
            @Id
            private String Id;

            private String regNumber;
            private String seatCapacity;
        }
    }

    private class RideDetails {

        @Id
        private String Id;

        private Coordinate departure;

        private Stop stop;

        private Coordinate destination;


        private class Stop {

            @Id
            private String Id;

            private Coordinate pickUp;
            private Coordinate dropOff;
        }
    }
}
