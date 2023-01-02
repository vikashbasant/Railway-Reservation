package com.rail.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TrainServices {
    public static List<Train> train = new LinkedList<Train> ();


    //static block is executed automatically when the .class file is loaded into the memory
    static
    {
        LocalDate date=LocalDate.now();
        train.add(new Train(12991,"UDZ JP EXP","Udaipur","Jaipur",288, 120,date));
        train.add(new Train(97221,"UDZ JP SF SPL","Udaipur","Jaipur",53, 130,date));
        train.add(new Train(12956,"JP MMCT SF EXP","Jaipur","Mumbai Central",88,335,date.plusDays(1)));
        train.add(new Train(14701,"ARAVALI EXPRESS","Jaipur","Bandra Terminus",34,1255,date.plusDays(2)));
        train.add(new Train(12904,"JP PUNE EXP","Jaipur","Kalyan",103,560,date.plusDays(2)));
    }


    public static Scanner scan = new Scanner(System.in);
    static int menu(){
        System.out.println ("1: Search Train by Number.");
        System.out.println ("2: Train Details.");
        System.out.println ("3: Search Trains for required criteria.");
        System.out.println ("4: Book Ticket");
        System.out.println ("5: My Bookings");
        System.out.println ("6: Ticket Details");
        System.out.println ("7: Cancel Ticket");
        System.out.println ("8: Exit");

        System.out.println ("Enter your choice: ");
        int choice = scan.nextInt();
        return choice;
    }

    static void services() throws Exception {
        while(true){
            try{
                int trainNumber;
                Train temp;
                int passengerId;
                String passengerName;
                int age;
                char gender;
                int pnr;
                List<Passenger> pList = new LinkedList<Passenger>();

                switch(menu()){
                    case 1:
                        // Search train by number:
                        System.out.println ("===Enter Train Number===");
                        trainNumber = scan.nextInt();
                        temp = findTrain(trainNumber);
                        if(temp != null){
                            System.out.println ("Train "+ trainNumber +" exist.");
                        }else{
                            System.out.println ("Train not found");
                        }
                        scan.nextLine ();
                        break;


                    case 2:
                        //Train details.
                        System.out.println("===Enter train number===");
                        trainNumber=scan.nextInt();
                        temp=findTrain(trainNumber);
                        if(temp!=null)
                        {
                            //changing the format of date
                            LocalDate d1=temp.getDoj();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            String d2 = (String)d1.format(formatter);

                            //Printing the details of train
                            System.out.println("Tr.No\tTr.Name\t\tFrom\t\tTO\tDoj\t\tSeats Available\tFare");
                            System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\t\t%d\n",temp.getTrainNo (),temp.getTrainName (),
                                    temp.getFromStation(),temp.getToStation(),d2,temp.getSeats(),temp.getFare());
                            break;
                        }
                        else
                        {
                            System.out.println("Train not found");
                        }
                        System.out.println();
                        scan.nextLine();
                        break;

                    case 3:
                        //Searching train for required criteria.
                        System.out.println("===Enter from station===");
                        String fromstation=scan.next();
                        scan.nextLine();	//consuming the leftover new line using the nextLine() method.
                        System.out.println("===Enter to station===");
                        String tostation=scan.nextLine();
                        System.out.println("===Enter date===");
                        String d=scan.nextLine();

                        System.out.println("===Seats required===");
                        int seatReqd=scan.nextInt();
                        if(seatReqd<1)
                        {
                            //if seats entered are less than 1 the explicitly call throw keyword
                            throw new Exception("Invalid seats.");
                        }


                        //Calling the TrainServices function which will search trains for required criteria
                        searchTrainsBetweenStations(fromstation, tostation, d, seatReqd);
                        scan.nextLine();
                        break;



                    case 4:
                        //Booking tickets.
                        System.out.println("===Enter train number===");
                        trainNumber=scan.nextInt();

                        //searching the train for entered train number
                        temp=TrainServices.findTrain(trainNumber);
                        if(temp!=null)
                        {
                            System.out.println();
                            System.out.println("1)Add passenger.");
                            System.out.println("2)Remove passenger.");
                            System.out.println("3)Show passengers.");
                            System.out.println("4)Stop.");

                            boolean flag=true;
                            //nested switch-case
                            while(flag)
                            {
                                System.out.println("Enter your choice-- ");
                                int choice=scan.nextInt();
                                switch(choice)
                                {
                                    case 1:
                                        //Add passenger
                                        System.out.println("Enter passenger ID-");
                                        passengerId=scan.nextInt();
                                        for(Passenger p:pList)
                                        {
                                            if(p.getPassengerId ()==passengerId)
                                                throw new Exception("Duplicate ID.");
                                        }

                                        System.out.println("Enter passenger name-");
                                        passengerName = scan.next();
                                        scan.nextLine();

                                        System.out.println("Enter passenger gender-");
                                        gender = scan.next().charAt(0);
                                        scan.nextLine();
                                        //	if(gender!='M' || gender!='F')
                                        //	{	throw new Exception("Invalid gender.");}
                                        System.out.println("Enter passenger age-");
                                        age = Integer.parseInt(scan.nextLine());
                                        if(age<1)
                                        {	throw new Exception("Invalid age.");}
                                        pList.add(new Passenger(passengerId, passengerName, age, gender));
                                        break;

                                    case 2:
                                        //Remove passenger.
                                        System.out.println("Enter passenger ID-");
                                        passengerId=scan.nextInt();
                                        for(Passenger p:pList)
                                        {
                                            if(p.getPassengerId ()==passengerId)
                                            {
                                                pList.remove(p);
                                                break;
                                            }
                                        }
                                        break;

                                    case 3:
                                        //Show passenger details.
                                        if(pList.isEmpty())
                                        {	System.out.println("No passenger added yet.");	}
                                        else
                                        {
                                            System.out.println("PId\tPName\t\tPAge\tPGender");
                                            for(Passenger x:pList)
                                            {
                                                System.out.println(x.getPassengerId ()+"\t"+x.getPassengerName ()+
                                                        "\t\t"+x.getAge()+"\t"+x.getGender());
                                            }
                                        }
                                        break;

                                    case 4:
                                        //Stop.
                                        flag=false;
                                        break;

                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                            //after adding required number of passengers , booking the tickets using bookTicket function
                            TrainServices.bookTicket(trainNumber, pList);
                        }
                        else
                            System.out.println("Train not found.");

                        scan.nextLine();
                        break;

                    case 5:
                        // My Booking:
                        TicketService.showAllBookings();
                        scan.nextLine ();
                        break;

                    case 6:
                        // Ticket Details
                        System.out.println ("=== Enter pnr no: ===");
                        pnr = scan.nextInt ();
                        TicketService.showTicketDetails(pnr);
                        scan.nextLine();
                        break;


                    case 7:
                        // Canceling Ticket:
                        System.out.println ("===Cancel Ticket===");
                        System.out.println ("Enter pnr no: ");
                        pnr = scan.nextInt();
                        TicketService.cancelTicket(pnr);
                        scan.nextLine();
                        break;

                    case 8:
                        System.exit(0);
                        break;
                    default:
                        System.out.println ("Invalid choice:");
                }
            }catch(InputMismatchException im)
            {
                System.out.println("Input mismatch.");
            }
            catch(Exception e) {
                System.out.println(e);
            }
            finally		//to overcome infinite loop in the case of exception catch
            {
                scan.nextLine();
            }
        }
    }



    public static Train findTrain(int trainNumber)
    {
        Train temp=null;
        for(Train x:train)
        {
            if(x.getTrainNo () == trainNumber)
            {
                temp=x;
                return temp;
            }
        }
        return temp;
    }

    public static void searchTrainsBetweenStations(String fromstation, String tostation,String date, int seats)
    {
        List<Train> availTrain=new LinkedList<Train>();

        for(Train x:train)
        {
            //took date input from user in (dd-MM-yyyy) format
            LocalDate d=x.getDoj();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String d1 = d.format(formatter);
            if(x.getFromStation().equals(fromstation) &&
                    x.getToStation().equals(tostation) &&
                    d1.equals(date) && x.getSeats()>=seats)
            {
                availTrain.add(x);
            }

        }

        if(availTrain.size()==0)
        {
            System.out.println("Sorry no trains available for required criteria.");
        }
        else
        {
            System.out.println("Tr.No\tTr.Name\t\tFrom\t\tTO\tDoj\t\tSeats Available\tFare");
            for(Train train:availTrain)
            {
                //Formatter for displaying and parsing date-time objects
                LocalDate d=train.getDoj();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String d1 = (String)d.format(formatter);

                System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\t\t%d\n",train.getTrainNo (),train.getTrainName (),train.getFromStation(),
                        train.getToStation(),d1,train.getSeats(),train.getFare());

            }
        }
    }

    public static void bookTicket(int trainNumber,List<Passenger> pList)
    {
        int pCount=pList.size();	//total number of passengers
        Passenger p1=pList.get(0);	//Passenger class object
        TrainServices.payment(trainNumber,pCount,p1);	//payment

        //subtracting the number of seats booked, from the actual available number of seats
        Train train=findTrain(trainNumber);
        train.setSeats((train.getSeats()-pCount));

        //Adding passenger list to MyBookings
        TicketService.addNewTicket(trainNumber,pList);
    }

    public static void payment(int tno,int n,Passenger p1)
    {
        System.out.println("Enter account details for payment:");
        System.out.println("Enter account number-");
        int accNumber=scan.nextInt();
        p1.setAccountNumber(accNumber);	//Passenger class object accessing parent BankAccount class method

        //finding train Fare
        Train temp=findTrain(tno);
        int totalFare=n * temp.getFare();	//total fare
        p1.withdrawl(totalFare);	//Passenger class object accessing parent BankAccount class method
        System.out.println("Payment done.");

    }





}
