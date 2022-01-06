package LinkedLists;

import java.util.Scanner;

/**
 * 
 * This Appointment class is to get information about an Appointment
 * 
 * @author Adir Ben-David and Jonathan Abitbol
 * @version 1.0
 * @since 2021-12-01
 *
 */

public class Appointment implements Bookable {

    /**
     * Attribute that holds the value of the appointment ID
     */
    String appointmentID;
    /**
     * Attribute that holds the value of the doctor's name
     */
    String doctorName;
    /**
     * Attribute that holds the value of the start time
     */
    double startTime;
    /**
     * Attribute that holds the value of the end time
     */
    double endTime;

    /**
     * Constructor that initializes all the attributes and creates an Appointment.
     * 
     * @param appointmentID Holds the appointment ID
     * @param doctorName    Holds the doctor name
     * @param startTime     Holds the start time
     * @param endTime       Hold the end time
     */

    public Appointment(String appointmentID, String doctorName, double startTime, double endTime) {
        this.appointmentID = appointmentID;
        this.doctorName = doctorName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Copy Constructor
     * 
     * @param a     a is an object of type Appointment. It will copy the same
     *              attributes of
     *              an Appointment to another Appointment.
     * @param value Since the ID has to be different for every Appointment then
     *              value is to ID
     */
    public Appointment(Appointment a, String value) {
        this.appointmentID = value;
        this.doctorName = a.doctorName;
        this.endTime = a.endTime;
        this.startTime = a.startTime;

    }

    /**
     * Getter method that access the start time
     * 
     * @return the value of the start time of the appointment
     */
    public double getStartTime() {
        return this.startTime;
    }

    /**
     * Setter methods that set the appointments start time
     * 
     * @param startTime appointments start time
     */
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method that access the value of the ID
     * 
     * @return the value of the ID of the appointment
     */

    public String getAppointmentID() {
        return this.appointmentID;
    }

    /**
     * Setter methods that set the appointments ID
     * 
     * @param appointmentID appointments id
     */
    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Getter method that access the Doctor's name
     * 
     * @return the value of the Doctor's name
     */

    public String getDoctorName() {
        return this.doctorName;
    }

    /**
     * Setter methods that set the appointments Doctor's name
     * 
     * @param DoctorName appointments Doctor's name
     */

    public void setDoctorName(String DoctorName) {
        this.doctorName = DoctorName;
    }

    /**
     * Getter method that access the end time
     * 
     * @return the value of the end time of the appointment
     */
    public double getEndTime() {
        return this.endTime;
    }

    /**
     * Setter methods that set the appointments end time
     * 
     * @param endTime appointments end time
     */

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    // Method from Interface Bookable

    /**
     * This is a method from the Interface Bookable that we have to implement.
     * This method is to check if two appointments are happening at a same time,
     * different time or if it overlaps.
     * 
     * @param a This is an object a of type Appointment
     * 
     */

    public String isOnSameTime(Appointment a) {
        if (this.startTime == a.startTime) {
            return "Same Time";
        } else if ((a.startTime > this.startTime && a.startTime < this.endTime)
                || (this.startTime > a.startTime && this.startTime < a.endTime)) {
            return "Some Overlap";
        } else
            return "Different Time";
    }

    /**
     * 
     * This method will prompt the user to enter a new appointmentID, then creates
     * and returns a clone of the calling object with the exception of the
     * appointmentID, which is assigned the value entered by the user;
     * 
     * @return Appointment A copy Object
     */
    public Appointment clone() {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("Enter Appointment ID: ");
        String AppointmentID = keyIn.nextLine();
        keyIn.close();
        return new Appointment(this, AppointmentID);
    }

    /**
     * This method takes all the attributes needed for the information of an
     * Appointment
     * and puts it into a string.
     * 
     * @return a string with all the attributes necessary to create an Appointment
     *         with
     *         the necessary information.
     */

    public String toString() {

        return "Appointment ID is: " + appointmentID + "\nDoctor's name is: " + doctorName + "\nStart Time is: "
                + startTime
                + "\nEnd Time is: " + endTime;
    }

    /**
     * function to compare two appointments for equality
     * 
     * @param o other appointment to compare too
     * @return boolean identifying if they are equal
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass() || o == null) {
            return false;
        }
        Appointment a = (Appointment) o;
        return this.doctorName.equals(a.doctorName) && this.startTime == a.startTime && this.endTime == a.endTime;
    }

}
