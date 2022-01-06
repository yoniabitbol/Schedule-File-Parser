// -------------------------------------------------------
// Assignment 4
// Written by: Adir Ben-David 40190551 & Jonathan Abitbol 40190550
// For COMP 249 Section D-DB Fall 2021
// Due Date = Sunday December 01, 2021.
// -------------------------------------------------------

//Welcome to my ProcessRequests Class.
//This program is written on 12/01/21 by Adir Ben-David and Jonathan Abitbol.
//The purpose of this class is to process booking requests.

/**
 * Adir Ben-David 40190551 & Jonathan Abitbol 40190550
 * COMP 249
 * Assignment #4
 * 12/01/2021
 */
package LinkedLists;

import java.util.ArrayList;
import java.util.Scanner;
import LinkedLists.Schedule.AppointmentNode;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This ProcessRequests class is a program that uses the given files and
 * processes the data.
 * 
 * @author Adir Ben-David and Jonathan Abitbol
 *
 */
public class ProcessRequests {

    /**
     * This ProcessRequests class is a program that uses the given files and
     * processes the data.
     * 
     * @author Adir Ben-David and Jonathan Abitbol
     * @param args parameter args
     */
    public static void main(String[] args) {
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        Scanner scannerSched = null;
        Scanner scannerReq = null;

        File s = new File("src/A4 Files/Schedule.txt");
        File r = new File("src/A4 Files/Requests.txt");

        try {
            scannerSched = new Scanner(new FileInputStream(s));
            scannerReq = new Scanner(new FileInputStream(r));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);

        }

        ArrayList<String> DoctorList = new ArrayList<String>();

        String doc = null;

        while (!(doc = scannerSched.nextLine()).equals("")) {
            DoctorList.add(doc);
        }

        String ID = scannerSched.next();
        String doctorName = scannerSched.nextLine();
        scannerSched.next();
        double startTime = scannerSched.nextDouble();
        scannerSched.next();
        double endTime = scannerSched.nextDouble();
        schedule1.addToStart(new Appointment(ID, doctorName, startTime, endTime));
        scannerSched.nextLine();
        int index = 1;

        while (scannerSched.hasNext()) {
            ID = scannerSched.next();
            doctorName = scannerSched.nextLine().trim();
            scannerSched.next();
            startTime = scannerSched.nextDouble();
            scannerSched.next();
            endTime = scannerSched.nextDouble();
            scannerSched.nextLine();
            schedule1.insertAtIndex(new Appointment(ID, doctorName, startTime, endTime), index);
            index++;

        }
        AppointmentNode start = schedule1.getHead();
        AppointmentNode next;
        int n = 1;
        int k = 1;

        while (start != null) {
            next = start.getNext();
            n = 1;
            while (next != null) {
                if (start.getAppointment().equals(next.getAppointment())) {
                    schedule1.deleteFromIndex(k + n);
                }

                n++;
                next = next.getNext();

            }
            start = start.getNext();
            k++;

        }

        ArrayList<Appointment> appRequests = new ArrayList<Appointment>();

        while (scannerReq.hasNext()) {
            ID = scannerReq.next();
            startTime = scannerReq.nextDouble();
            endTime = scannerReq.nextDouble();
            appRequests.add(new Appointment(ID, null, startTime, endTime));
        }

        System.out.println(schedule1.toString());
        String currentDoc;
        index = 0;
        int count;
        int docCount = 0;
        int reqIndex = 0;
        String message = null;
        Schedule.AppointmentNode currentNode = schedule1.new AppointmentNode();
        currentNode = schedule1.getHead();
        Appointment currentAppointment = currentNode.getAppointment();
        Appointment currentRequest = appRequests.get(0);

        while (reqIndex < appRequests.size()) {
            docCount = 0;
            index = 0;
            currentRequest = appRequests.get(reqIndex);
            while (index < DoctorList.size()) {
                count = 0;
                currentDoc = DoctorList.get(index).substring(DoctorList.get(index).indexOf(" ") + 1,
                        DoctorList.get(index).length());
                currentNode = schedule1.getHead();
                currentAppointment = currentNode.getAppointment();
                while (currentNode != null) {
                    if (currentDoc.equals(currentAppointment.getDoctorName().strip())) {

                        if (currentAppointment.isOnSameTime(currentRequest).equals("Different Time")
                                && !currentAppointment.isOnSameTime(currentRequest).equals("Some Overlap")) {
                            message = "Patient can book appointment " + currentRequest.getAppointmentID() + " from "
                                    + currentRequest.getStartTime() + "0 to " + currentRequest.getEndTime()
                                    + "0 with Dr. " + currentDoc + " as other doctors are not available at this time.";

                            docCount++;
                        } else if (currentAppointment.isOnSameTime(currentRequest).equals("Same Time")
                                || currentAppointment.isOnSameTime(currentRequest).equals("Some Overlap")) {
                            message = "";
                            currentNode = null;
                            currentAppointment = null;
                            index++;

                        }

                    }
                    if (currentNode != null) {
                        currentNode = currentNode.getNext();
                        if (currentNode != null) {
                            currentAppointment = currentNode.getAppointment();
                        } else
                            index++;

                    }

                }

                count++;
            }
            if (docCount == 1)
                System.out.println(message);
            else if (docCount > 1 && !message.equals(""))
                System.out.println("Patient can book appointment " + currentRequest.getAppointmentID() + " from "
                        + currentRequest.getStartTime() + "0 to " + currentRequest.getEndTime()
                        + "0 as nothing is scheduled during that time for mutliple doctors.");
            else
                System.out.println("Patient can't book appointment " + currentRequest.getAppointmentID() + " from "
                        + currentRequest.getStartTime() + "0 to " + currentRequest.getEndTime()
                        + "0 as no doctor is available at this time.");
            reqIndex++;

        }

        // PART D
        boolean searchAnother = true;
        while (searchAnother) {
            Scanner keyIn = new Scanner(System.in);
            System.out.println("\nPlease an appointment ID you would like to find: ");
            String AppointmentID = keyIn.nextLine();
            schedule1.find(AppointmentID.toUpperCase());

            System.out.println("\nDo you want to find another appointment? ");
            String yesorno = keyIn.nextLine();
            if (!yesorno.equalsIgnoreCase("yes")) {

                searchAnother = false;
                System.out.println("\nThank you for using our program!");

            }

        }

        // PART E
        Appointment a = new Appointment("A22", "Smith", 12.00, 13.00);
        Appointment b = new Appointment("B22", "Abitbol", 12.00, 13.00);
        Appointment c = new Appointment("C22", "Ben-David", 12.30, 13.30);
        Appointment d = new Appointment("D22", "Ben-David", 12.30, 13.30);
        Schedule test = new Schedule();

        System.out.println("\nTesting Method ");
        System.out.println("*************************");
        System.out.println("");
        System.out.println(a.isOnSameTime(b) + "\n");
        System.out.println(b.isOnSameTime(c) + "\n");
        System.out.println(a.toString() + "\n");
        System.out.println(b.toString() + "\n");
        System.out.println(a.equals(b) + "\n");
        System.out.println(d.equals(c) + "\n");

        test.addToStart(a);
        System.out.println(test + "\n");
        test.addToStart(b);
        test.addToStart(c);
        System.out.println(test + "\n");
        test.deleteFromIndex(2);
        System.out.println(test + "\n");
        test.insertAtIndex(b, 2);
        System.out.println(test + "\n");
        test.replaceAtIndex(d, 2);
        System.out.println(test + "\n");
        test.deleteFromStart();
        System.out.println(test + "\n");

        // COMPARING CLONE
        System.out.println("****************************************\n");
        Schedule test2 = test.clone();

        System.out.println(test + "\n");

        System.out.println(test2 + "\n");

        // COMPARING TO CHECK IF THEY ARE EQUAL
        System.out.println(test.equals(test2) + "\n");// This should be true

        test2.addToStart(a);
        System.out.println(test2 + "\n");
        System.out.println(test + "\n");

        System.out.println(test.equals(test2) + "\n");// This should be false;

        System.out.println(test.contains("D22"));// this should be true
        System.out.println(test.contains("A22") + "\n");// this should be false

        System.out.println(test + "\n");
        System.out.println(test.find("B22"));
        System.out.println(test.find("D22"));
        System.out.println(test.find("A22"));

    }

}