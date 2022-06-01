package com.testing;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

    /* Name= sachika Herath
       UOW_NO= w1809813
       IIT_NO= 2019712 */

class Hotel{

    public static  Room[] rooms = new Room[6];

    String username;

    Hotel(String username){
        this.username= username;
    }

    public static void viewRooms() {

        for (int x = 0; x < 6; x++) {
            System.out.println("room " + x + " occupied by " + rooms[x].username);   //person name who rented the room
        }
        System.out.println("*************************************");

    }


    public static void viewEmptyRoom(){

        for ( int x = 0; x<6; x++){
            if (rooms[x].username == "none"){
                System.out.println(x + " is an empty room");
            }
        }
        System.out.println("*************************************");

    }

}

class Room{

    public int id;
    public String username;

    Room(int id, String username){
        this.id=id;
        this.username=username;
    }

    public void addCustomer(String name){
       this.username = name;
    }

    public void deleteCustomer(){
        this.username = "none";
    }

}

class Person{

    public int id;
    public static String username;



    public static String first_name[] = new String[6];
    public static String sir_name[] = new String[6];
    public static int cardNo[] = new int[6];
    public static int Noguest[] = new int[6];

    Person(int id, String username){
        this.id=id;
        this.username=username;
    }

    public static void additionalDetails(int num){

            Scanner input = new Scanner(System.in);
            System.out.println("Please enter additional details : ");

            String fname = username;

            System.out.print("Enter the number of guests : ");
            int No_guest = input.nextInt();


            System.out.print("Enter customer surname : ");
            String sname = input.next();

            System.out.print("Enter customer card number : ");
            int cardNumber = input.nextInt();

            first_name[num] = fname;
            sir_name[num]= sname;
            cardNo[num] = cardNumber;
            Noguest[num] = No_guest;
        }




    }




class CircularQueue<E> {

    private int currentSize; //Current Circular Queue Size
    private E[] circularQueueElements;
    private int maxSize; //Circular Queue maximum size

    private int rear;//rear position of Circular queue(new element enqueued at rear).
    private int front; //front position of Circular queue(element will be dequeued from front).

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        circularQueueElements = (E[]) new Object[this.maxSize];
        currentSize = 0;
        front = -1;
        rear = -1;
    }

    /**
     * Enqueue elements to rear.
     */
    public void enqueue(E item) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException("Circular Queue is full. Element cannot be added");
        }
        else {
            rear = (rear + 1) % circularQueueElements.length;
            circularQueueElements[rear] = item;
            currentSize++;

            if (front == -1) {
                front = rear;
            }
        }
    }

    /**
     * Dequeue element from Front.
     */
    public E dequeue() throws QueueEmptyException {
        E deQueuedElement;
        if (isEmpty()) {
            throw new QueueEmptyException("Circular Queue is empty. Element cannot be retrieved");
        }
        else {
            deQueuedElement = circularQueueElements[front];
            circularQueueElements[front] = null;
            front = (front + 1) % circularQueueElements.length;
            currentSize--;
        }
        return deQueuedElement;
    }

    /**
     * Check if queue is full.
     */
    public boolean isFull() {
        return (currentSize == circularQueueElements.length);
    }

    /**
     * Check if Queue is empty.
     */
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public String toString() {
        return "CircularQueue [" + Arrays.toString(circularQueueElements) + "]";
    }

}

class QueueFullException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueFullException() {
        super();
    }

    public QueueFullException(String message) {
        super(message);
    }

}

class QueueEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueEmptyException() {
        super();
    }

    public QueueEmptyException(String message) {
        super(message);
    }

}

public class Main {

    public static String roomName;
    public static int roomNum = 0;
    public static Hotel hotel;
    public static CircularQueue<String> CQ = new CircularQueue<String>(8);


    public static void main(String[] args) {

        initialise();
        handleMenu();


    }

    private static void initialise() {
        for ( int x = 0 ; x<6;x++) {

            hotel.rooms[x] = new Room(x, "none");
        }
//        for (int x = 0; x < 6; x++) hotelRef[x] = "none";
//        System.out.println("initilise ");
    }

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



    }

    //Menu-functionality

    public static boolean checkEmpty(){

        for ( int x = 0; x<6; x++){
            if (hotel.rooms[x].username == "none"){
                return true;
            }
        }
        return false;

    }

    private static void addCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter room number (0-5) or 6 to stop : ");
        try {
        roomNum = input.nextInt();  //enter the room Number

            if (roomNum == 6) {
                handleMenu();
            } else if (roomNum > 6) {
                System.out.print("enter a valid value : ");
                addCustomer();

            } else if (roomNum < 6) {
                System.out.print("Enter customer first name for room " + roomNum + " : ");
                roomName = input.next();    //enter the name of the person who rented the room
                if (checkEmpty()) {
                    hotel.rooms[roomNum].addCustomer(roomName);
                    Person obj = new Person(roomNum, roomName);
                    obj.additionalDetails(roomNum);

                } else {
                    System.out.println("hotel is full");
                    System.out.println("will be adding to the waiting list");
                    CQ.enqueue(roomName);
                    System.out.println(CQ);
                }


            }
        }catch(Exception e){
            System.out.println("Please enter valid amount");
        }

    }                 //add customers to the system

    private static void viewRooms() {
        hotel.viewRooms();
        handleMenu();

    }                   //view rooms rented by customers

    private static void viewEmptyRoom(){
        hotel.viewEmptyRoom();
        handleMenu();

    }                // view empty rooms

    private static void deleteCustomer(){
       try {
           Scanner input = new Scanner(System.in);
           System.out.print("Enter Room number : ");
           int roomNumber = input.nextInt();
           hotel.rooms[roomNumber].deleteCustomer();

           if (CQ.isEmpty()){
               handleMenu();
           }
           else {
               hotel.rooms[roomNumber].username=CQ.dequeue();
           }
           handleMenu();
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
        String name = input.nextLine();
//        int index = Arrays.asList(hotel).indexOf(input.nextLine());

        for (int i = 0; i < hotel.rooms.length; i++) {
            if (name.equals(hotel.rooms[i].username) ) {
                System.out.println("Room number is -> " + i);
            }
//            System.out.println(index);
        }
        System.out.println("Sorry cannot find the room");
    }                    //search rooms by customer name

    private static void exportData() {

        try {
            FileWriter myWriter = new FileWriter("data.txt");
            for (int x = 0; x < 6; x++ ){
                myWriter.append(x + "=" + hotel.rooms[x].username);
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
//                hotel.rooms[Integer.parseInt(room[0])] = room[1];
                hotel.rooms[Integer.parseInt(room[0])].id = Integer.parseInt(room[0]);
                hotel.rooms[Integer.parseInt(room[0])].username = room[1];
                System.out.println(Arrays.toString(room));

            }

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

        List<Room> hotelLists = Arrays.asList(hotel.rooms);
        List<String> customers = new ArrayList<String>();
        for (int i = 0; i < hotelLists.size(); i++) {
            customers.add(hotelLists.get(i).username);
        }

        customers.sort( Comparator.comparing( String::toString ) );
        System.out.println(customers
        );   //person name who rented the room



    }                  //view customers in ascending order


}



