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

import java.util.NoSuchElementException;

/**
 * This Schedule class is a program that uses linked list for the schedule.
 * 
 * @author Adir Ben-David and Jonathan Abitbol
 * @version 1.0
 * @since 2021-12-01
 *
 */
public class Schedule {

    /**
     * This inner class represents the node class for this linked list of schedules
     * 
     * @author Adir Ben-David and Jonathan Abitbol
     *
     */
    public class AppointmentNode {// By putting this inner class public we will be able to access all
                                  // attributes/methods outside of this class and therefore by using the setter
                                  // and getter method it could cause privacy leaks since the setter and getters
                                  // return a reference to the node which means that the private restriction on
                                  // the instance variable can be defeated.
        /**
         * Attribute that holds the data in the node
         */
        private Appointment app;
        /**
         * Attribute pointing to the next node
         */
        private AppointmentNode next;

        /**
         * Default constructor for node
         */
        public AppointmentNode() {
            app = null;
            next = null;
        }

        /**
         * Parametrized Constructor for App Node
         * 
         * @param app     data in node
         * @param appNode next node pointer
         */
        public AppointmentNode(Appointment app, AppointmentNode appNode) {
            this.app = app;
            this.next = appNode;
        }

        /**
         * Copy constructor
         * 
         * @param appNode copy object
         */
        public AppointmentNode(AppointmentNode appNode) {
            this.app = new Appointment(appNode.getAppointment(), appNode.getAppointment().getAppointmentID());
            this.next = null;
        }

        /**
         * Accessor method to get appointment from node.
         * 
         * @return the appointment
         */
        public Appointment getAppointment() {
            return this.app;
        }

        /**
         * Mutator method for the appointment in the node
         * 
         * @param app data
         */
        public void setAppointment(Appointment app) {
            this.app = app;
        }

        /**
         * Accessor for next node in list
         * 
         * @return location of next node
         */
        public AppointmentNode getNext() {
            return this.next;
        }

        /**
         * Mutator method to set next node
         * 
         * @param oppNode next node
         */
        public void setNext(AppointmentNode oppNode) {
            this.next = oppNode;
        }

        /**
         * Clone method which will clone and return schedule
         */
        public AppointmentNode clone() {

            return new AppointmentNode(this);
        }

    }

    // INNER CLASS ENDS HERE

    /**
     * Attribute holding the head node
     */
    private AppointmentNode head;
    /**
     * Attribute holding the head linked list size
     */
    private int size;

    /**
     * Default constructor for Schedule linked list
     */
    public Schedule() {
        head = null;
        size = 0;
    }

    /**
     * Copy constructor for the schedule class
     * 
     * @param schedule copy object
     */
    public Schedule(Schedule schedule) {

        this.head = schedule.head.clone();
        this.size = schedule.size;

        AppointmentNode currentPosition = schedule.head;
        AppointmentNode copy = this.head;

        for (int i = 0; i < this.size - 1; i++) {
            copy.next = currentPosition.next.clone();
            copy = copy.next;
            currentPosition = currentPosition.next;

        }

    }

    /**
     * Clone that redirects to the copy constructor of the schedule class.
     */
    public Schedule clone() {
        return new Schedule(this);
    }

    /**
     * Accessor to get the head of the schedule list
     * 
     * @return head of sched list
     */
    public AppointmentNode getHead() {
        return head;
    }

    /**
     * Accessor method to get the size of the list of schedules
     * 
     * @return the size of the schedule list
     */
    public int getSize() {
        return size;
    }

    /**
     * Method to add a node to the start of the schedule linked list
     * 
     * @param a which holds value of an appointment
     */
    public void addToStart(Appointment a) {

        AppointmentNode node = new AppointmentNode(a, null);

        if (head == null) {
            head = node;
            size++;
            return;
        }

        AppointmentNode temp = head;
        head = node;
        head.next = temp;
        size++;

    }

    /**
     * Method to insert appointment node into a schedule linked list
     * 
     * @param x is an appointment data node
     * @param n is the index at which will enter the new node
     * @throws NoSuchElementException
     */
    public void insertAtIndex(Appointment x, int n) throws NoSuchElementException {
        if (n <= 0 || n > (size)) {
            throw new NoSuchElementException();
        }

        AppointmentNode appNode = new AppointmentNode(x, null);
        AppointmentNode current = head.next;
        AppointmentNode prev = head;

        for (int i = 1; i < n; i++) {
            prev = current;
            current = current.next;
        }

        prev.next = appNode;
        appNode.next = current;
        size++;

    }

    /**
     * remove first node appointment from list while handling all cases
     */
    public void deleteFromStart() {
        if (size == 0) {
            return;
        }

        if (size == 1) {
            head = null;
            size--;
            return;
        } else {
            head = head.next;
            size--;
            return;
        }

    }

    /**
     * method to delete a node at an index x
     * 
     * @param x is the index at which to delete the node
     * @throws NoSuchElementException when the index is not in the list
     */
    public void deleteFromIndex(int x) throws NoSuchElementException {
        if (head == null || x > size || x <= 0) {

            throw new NoSuchElementException();
        }

        if (x == 1) {
            {
                head = head.next;
                size--;
                return;

            }
        }

        AppointmentNode current = head.next;
        AppointmentNode prev = head;

        for (int i = 2; i < x; i++) {

            prev = current;
            current = current.next;

        }

        prev.next = current.next;
        size--;

    }

    /**
     * This method will replace a node at a given index selected as parameter
     * 
     * @param x data to replace
     * @param y index at which to replace
     */
    public void replaceAtIndex(Appointment x, int y) {

        if (y <= 0 || y > size - 1) {
            return;

        }

        deleteFromIndex(y);
        insertAtIndex(x, y - 1);
    }

    // INNER CLASS STARTS HERE

    /**
     * method to find whether an appointment is in a list by checking with ID
     * 
     * @param appID appointment ID to compare with the ones in the list
     * @return the node which contains the appointment with this ID if any.
     */
    public AppointmentNode find(String appID) {
        AppointmentNode current = head;

        for (int i = 0; i < size; i++) {

            if (current.getAppointment().getAppointmentID().equals(appID)) {
                System.out.println("Appointment ID " + appID + " was found at index:" + i);
                return current;
            }

            current = current.next;
        }
        System.out.println("Appointment ID  " + appID + " is not on the list");
        return null;

    }

    /**
     * Method returning boolean to check if the list contains a appointment with the
     * ID as parameter
     * 
     * @param id to check if it repeats itself
     * @return true or false depending if the set contains the ID or not
     */
    public boolean contains(String id) {

        AppointmentNode current = head;

        for (int i = 0; i < size; i++) {
            if (current.getAppointment().getAppointmentID().equals(id)) {
                return true;
            }

            current = current.next;
        }
        return false;
    }

    /**
     * Equals method to check if two objects are identical with same attributes
     * except for the ID
     * 
     * @param schedule object to compare
     * @return true or false depending on whether the objects compared are the same
     *         or not.
     */
    public boolean equals(Schedule schedule) {

        if (this == schedule) {
            return true;
        }

        if (size != schedule.size) {
            return false;
        }

        AppointmentNode current = head;
        AppointmentNode sched = schedule.head;

        for (int i = 0; i < size; i++) {
            if (!(current.getAppointment().equals(sched.getAppointment()))) {
                return false;
            }

            current = current.next;
            sched = sched.next;
        }
        return true;

    }

    /**
     * useful toString method to display in an organized matter.
     */
    public String toString() {
        String string = "";
        if (head != null) {

            AppointmentNode headN = head;

            for (int i = 0; i < size; i++) {
                string += headN.getAppointment().toString() + "==>";
                headN = headN.getNext();

            }

        }

        string += "null";

        return string;

    }
}
