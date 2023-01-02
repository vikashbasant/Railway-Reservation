package com.rail.pojo;

public class Passenger extends BankAccount{

    private int passengerId;
    private String passengerName;
    private int age;
    private char gender;


    Passenger(){
        // default constructor:
    }

    public Passenger(int passengerId, String passengerName, int age, char gender){
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.age = age;
        this.gender = gender;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
