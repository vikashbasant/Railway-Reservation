package com.rail.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TicketService {

    /*Creating a LinketList of MyBookings type
	which will store the [PNR,BookedTrain,PassengerList] of
	a particular booking in blocks*/
    private static List<MyBooking> booked=new LinkedList<MyBooking>();

    public static void addNewTicket(int tno,List<Passenger> pList)
    {
        Train bookedTrain= TrainServices.findTrain(tno);
        Random r = new Random();
        int pnr=r.nextInt(1000);		//generating pnr no. randomly
        booked.add(new MyBooking(pnr,bookedTrain, pList));		//Adding  [PNR,BookedTrain,PassengerList] details into booked list.
        System.out.println("Tickets booked successfully.");
    }

    public static void cancelTicket(int pnr)
    {
        for(MyBooking bookingtocancle:booked)
        {
            if(bookingtocancle.getPnr()==pnr)
            {
                //total seats booked
                int pseats=bookingtocancle.getpList().size();

                //canceling seats and adding canceled seats to available seats of train
                bookingtocancle.getBookedTrain().setSeats((bookingtocancle.getBookedTrain().getSeats()+pseats));

                //refunding the amount deducted from passenger's account
                bookingtocancle.getpList().get(0).deposit(bookingtocancle.getpList().get(0).getAccountBalance ()+pseats*bookingtocancle.getBookedTrain().getFare());

                //removing that booking from "booked" list
                booked.remove(bookingtocancle);
                System.out.println("Ticket cancelled successfully.");
                return;
            }
        }
        System.out.println("Invalid PNR.");
    }

    public static void showAllBookings()
    {
        if(booked.size()==0)
        {
            System.out.println("No tickets booked yet.");
        }
        else
        {
            System.out.println("***My Bookings***");
            System.out.println("PNR\tTrain Number");
            for(MyBooking obj:booked)
            {
                System.out.println(obj.getPnr()+"\t"+obj.getBookedTrain().getTrainNo ());
            }
        }
    }

    public static void showTicketDetails(int pnr)
    {
        MyBooking booking=null;
        for(MyBooking obj:booked)
        {
            if(obj.getPnr()==pnr)
            {
                booking=obj;
                break;
            }
        }

        if(booking!=null)
        {
            Train train=booking.getBookedTrain();
            List<Passenger> pList=booking.getpList();

            System.out.println("****Ticket Details****");
            System.out.println("PNR-> "+booking.getPnr());
            System.out.println("Train number-> " +train.getTrainNo ());
            System.out.println("From station-> " +train.getFromStation() + "\tTo station-> " +train.getToStation());
            LocalDate d=train.getDoj();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String d1 = d.format(formatter);
            System.out.println("Date-> " +d1+ "\tFare per passenger-> " +train.getFare());
            System.out.println("Total fare-> " +train.getFare()*pList.size());

            System.out.println("\nPassenger info--\n");
            System.out.println("PId\tPName\t\tPAge\tPGender");
            for(Passenger p:pList)
            {
                System.out.println(p.getPassengerId ()+"\t"+p.getPassengerName ()+"\t\t"+p.getAge()+"\t"+p.getGender());
            }
            System.out.println("*******************");
        }
        else
        {
            System.out.println("Invalid pnr.");
        }
    }
}