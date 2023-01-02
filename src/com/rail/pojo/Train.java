package com.rail.pojo;

import java.time.LocalDate;


public class Train {
    private int trainNo;
    private String trainName;
    private String fromStation;
    private String toStation;
    private int seats;
    private int fare;
    private LocalDate doj;


    Train(){
        // default constructor:
    }



    public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    // This parameterized constructor:
    Train(int trainNo, String trainName, String fromStation, String toStation, int seats, int fare, LocalDate doj) {
        super ();
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.seats = seats;
        this.fare = fare;
        this.doj = doj;
    }




}
