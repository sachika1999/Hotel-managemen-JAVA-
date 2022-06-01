package com.testing;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    /* Name= sachika Herath
       UOW_NO= w1809813
       IIT_NO= 2019712 */


    public static String[] hotel = new String[6];
    public static String roomName;
    public static int roomNum = 0;

    public static String first_name[] = new String[6];
    public static String sir_name[] = new String[6];
    public static int cardNo[] = new int[6];


    public static void main(String[] args) {

        initialise(hotel);
        handleMenu();


    }

    private static void initialise(String hotelRef[]) {
        for (int x = 0; x < 6; x++) hotelRef[x] = "none";
        System.out.println("initilise ");
    } //initialization

    private static void handleMenu() {
        Scanner input = new Scanner(System.in);

        //for (int x = 0; x < 6; x++ ) hotel[x] = ""; //initialise


        System.out.println("press A - Add new Customer");
        System.out.println("press V - View Rooms");
        System.out.println("press E - View empty rooms");
        System.out.println("press D - Delete Customer");
        System.out.println("press F - Find room from Customer Name");
        System.out.println("press S - Export data to a file");
        System.out.println("press L - Import data from a file");
        System.out.println("press O - View Guests");
        System.out.println("********************************");
        System.out.print("Enter : ");
        String code = input.next();

        switch (code) {
            case "A":
                addCustomer();
                break;

            case "V":
                viewRooms();
                break;
            case "E":
                viewEmptyRoom();
                break;
            case "D":
                deleteCustomer();
                break;

            case "F":
                nameSearch();
                break;
            case "S":
                exportData();
                break;
            case "L":
                importData();
                break;
            case "O":
                viewCustomer();
                break;

        }

        handleMenu();



    }                  //Menu-functionality

    private static void addCustomer() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter room number (0-5) or 6 to stop : ");
            roomNum = input.nextInt();  //enter the room Number

            if (roomNum == 6) {
                handleMenu();
            } else if (roomNum > 6) {
                System.out.print("enter a valid value : ");
                addCustomer();

            } else if (roomNum < 6) {
                System.out.print("Enter customer name for room " + roomNum + " : ");
                roomName = input.next();    //enter the name of the person who rented the room
                hotel[roomNum] = roomName;
                additionalDetails(roomNum);
                handleMenu();


            } else {
                handleMenu();
            }
        }catch(Exception e){
            System.out.print("enter a valid value : ");
        }

    }                 //add customers to the system

    private static void viewRooms() {
       for (int x = 0; x < 6; x++) {
            System.out.println("room " + x + " occupied by " + hotel[x]);   //person name who rented the room
        }
        System.out.println("*************************************");
        handleMenu();

    }                   //view rooms rented by customers

    private static void viewEmptyRoom(){

        for ( int x = 0; x<6; x++){
            if (hotel[x] == "none"){
                System.out.println(x + " is an empty room");
            }
        }
        System.out.println("*************************************");
        handleMenu();

    }                // view empty rooms

    private static void deleteCustomer(){
       try {
           Scanner input = new Scanner(System.in);
           System.out.print("Enter Room number : ");
           int nmbr = input.nextInt();
           if (nmbr<6){
               hotel[nmbr] = "none";
               handleMenu();
           }
           else {
               handleMenu();
           }
       }
       catch (NumberFormatException ex){
           handleMenu();
       }
       finally {
           handleMenu();
       }

    }               //delete customers from system

    private static void nameSearch(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter customer Name : ");

        int index = Arrays.asList(hotel).indexOf(input.nextLine());
        System.out.println(index);

    }                   //search rooms by customer name

    private static void exportData() {

        try {
            FileWriter myWriter = new FileWriter("data.txt");
            for (int x = 0; x < 6; x++ ){
                myWriter.append(x + "=" + hotel[x]);
                if ( x != 5 ) myWriter.append("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        handleMenu();

    }                  //export data to a text file

    private static void importData(){
        try
        {
            File file=new File("data.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
            String line;
            while((line=br.readLine())!=null)
            {
                String[] room = line.split("=");
                hotel[Integer.parseInt(room[0])] = room[1];
                System.out.println(Arrays.toString(room));

            }
            System.out.println(Arrays.toString(hotel));
            fr.close();    //closes the stream and release the resources
            System.out.println("Contents of File: ");
            System.out.println(sb.toString());   //returns a string that textually represents the object
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        handleMenu();
    }                   //import data from a text file

    private static void viewCustomer(){

        List<String> hotelLists = Arrays.asList(hotel);
        hotelLists.sort( Comparator.comparing( String::toString ) );
        System.out.println(hotelLists);   //person name who rented the room
        System.out.println("*************************************");



    }                  //view customers in ascending order

    public static void additionalDetails(int num){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter additional details : ");

        String fname = roomName;

        System.out.print("Enter customer surname : ");
        String sname = input.next();

        System.out.print("Enter customer card number : ");
        int cardNumber = input.nextInt();

        first_name[num] = fname;
        sir_name[num]= sname;
        cardNo[num] = cardNumber;


    }


}



