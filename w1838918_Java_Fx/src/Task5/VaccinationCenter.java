package Task5;

import static Task5.PatientRegistrationApplication.boothnum;
import static Task5.PatientRegistrationApplication.receiptButton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class Patient {

    private String firstname;
    private String surname;
    private String city;
    private String nicorpassport;
    private int age;
    private boolean astrazeneca;
    private boolean sinopharm;
    private boolean pfizer;

    public Patient(String firstname, String surname, String city, String nicorpassport, int age) {
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
        this.nicorpassport = nicorpassport;
        this.age = age;
    }

    /**
     * @return the firstname
     */
    String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the surname
     */
    String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the isastrazeneca
     */
    boolean isAstrazeneca() {
        return astrazeneca;
    }

    //@param isastrazeneca the isastrazeneca to set

    void setAstrazeneca(boolean astrazeneca) {
        this.astrazeneca = astrazeneca;
    }

    /**
     * @return the sinopharm
     */
    boolean isSinopharm() {
        return sinopharm;
    }

    /**
     * @param sinopharm the sinopharm to set
     */
    void setSinopharm(boolean sinopharm) {
        this.sinopharm = sinopharm;
    }

    /**
     * @return the pfizer
     */
    boolean isPfizer() {
        return pfizer;
    }

    /**
     * @param pfizer the pfizer to set
     */
    void setPfizer(boolean pfizer) {
        this.pfizer = pfizer;
    }

}

public class VaccinationCenter {

    static Patient[] astrazenecabooths = new Patient[2];
    static Patient[] sinopharmbooths = new Patient[2];
    static Patient[] pfizerbooths = new Patient[2];
    static Patient[] vaccinebooth;

    static LinkedList<Patient> astrazenecall = new LinkedList<>();
    static LinkedList<Patient> sinopharmll = new LinkedList<>();
    static LinkedList<Patient> pfizerll = new LinkedList<>();

    static int stock = 150;
    static String[] names;
    static int viewcount;
    static File filevac = new File("VaccinationData.txt");

    static int currentboothNum;
    static int removepatientboothNum;

    static int n = 0;

    public static void main(String[] args) {

        showConsole();
    }

    private static void viewAllBooths() {

        System.out.println("\n---- View all Vaccination Booths ----");

        viewBooths();
        showConsole(); //Call the method to show menu options

    }

    //Method to View All Empty Vaccination Booths
    private static void viewEmptyBooths() {
        System.out.println("\n---- View all Empty Booths ----");

        //Iterate through booths
        System.out.println("\n---- AstraZeneca Empty Vaccination Booths ----");
        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i] == null) {
                System.out.println("Booth #" + i + " - Empty ");
            }
        }

        System.out.println("\n---- Sinopharm Empty Vaccination Booths ----");
        for (int i = 0; i < sinopharmbooths.length; i++) {
            int view = i + 2;
            if (sinopharmbooths[i] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            }
        }

        System.out.println("\n---- Pfizer Empty Vaccination Booths ----");
        for (int i = 0; i < pfizerbooths.length; i++) {
            int view = i + 4;
            if (pfizerbooths[i] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            }
        }

        showConsole();  //Call the method to show menu options
    }

    //Method to Add Patient to a Booth
    private static void loadPatientFrame() {

        System.out.println("\n---- Add Patient to a Booth ----");

        //Open JavaFX form
        try {
            Application.launch(PatientRegistrationApplication.class);

        } catch (Exception e) {

        }

    }

    //Method to Add Patient to a Booth
    private static void addPatient(Patient currentpatient, String requestedvaccinetype, int boothnumber) {

        if (requestedvaccinetype.equals("AstraZeneca")) {

            if (astrazenecabooths[boothnumber] == null) {

                astrazenecabooths[boothnumber] = currentpatient;
                currentpatient.setAstrazeneca(true);
                currentpatient.setSinopharm(false);
                currentpatient.setPfizer(false);

            } else {
                System.out.println("\n---- Requested Astrazeneca Booth is occupied. Try Again! ----");
            }

        } else if (requestedvaccinetype.equals("Sinopharm")) {

            if (sinopharmbooths[boothnumber - 2] == null) {
                sinopharmbooths[boothnumber - 2] = currentpatient;
                currentpatient.setAstrazeneca(false);
                currentpatient.setSinopharm(true);
                currentpatient.setPfizer(false);

            } else {
                System.out.println("\n---- Requested Sinopharm Booth is occupied. Try Again! ----");

            }

        } else if (requestedvaccinetype.equals("Pfizer")) {

            if (pfizerbooths[boothnumber - 4] == null) {

                pfizerbooths[boothnumber - 4] = currentpatient;
                currentpatient.setAstrazeneca(false);
                currentpatient.setSinopharm(false);
                currentpatient.setPfizer(true);

            } else {
                System.out.println("\n---- Requested Pfizer Booth is occupied. Try Again! ----");

            }

        } else {
            System.out.println("\n---- INVALID Vaccination Selection. Try Again! ----");

        }

        --stock;

        warnStock();

        viewBooths();

        showConsole();
    }

    //Method to View Available Booths
    private static void viewBooths() {
        System.out.println("\n---- AstraZeneca  Vaccination Booths ----");
        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i] == null) {
                System.out.println("Booth #" + i + " - Empty ");
            } else {
                System.out.println("Booth #" + i + " - " + astrazenecabooths[i].getFirstname() + " " + astrazenecabooths[i].getSurname());
            }
        }

        System.out.println("\n---- Sinopharm  Vaccination Booths ----");
        for (int i = 0; i < sinopharmbooths.length; i++) {
            int view = i + 2;
            if (sinopharmbooths[i] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            } else {
                System.out.println("Booth #" + view + " - " + sinopharmbooths[i].getFirstname() + " " + sinopharmbooths[i].getSurname());
            }
        }

        System.out.println("\n---- Pfizer  Vaccination Booths ----");
        for (int i = 0; i < pfizerbooths.length; i++) {
            int view = i + 4;
            if (pfizerbooths[i] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            } else {
                System.out.println("Booth #" + view + " - " + pfizerbooths[i].getFirstname() + " " + pfizerbooths[i].getSurname());
            }
        }
    }

    //Method to Remove Patient from a Booth
    private static void removePatient() {

        System.out.println("\n---- Remove Patient from a Booth ----");

        Scanner scboothselect = new Scanner(System.in);
        System.out.println("Select the prefered vaccine by entering the following codes. \n "
                + "1 or AZ: AstraZeneca  "
                + "2 or SI: Sinopharm  "
                + "3 or PF: Pfizer");

        //Accept user input selecting the vaccine option
        String boothitem = scboothselect.next();

        Scanner scapb = new Scanner(System.in);

        if (boothitem.equals("1") || boothitem.equalsIgnoreCase("AZ")) {
            System.out.print("Enter booth number (0-1) to remove the patient  : ");

            removepatientboothNum = scapb.nextInt();

            if (removepatientboothNum < 0 || removepatientboothNum > 1) {
                System.out.println("\n---- INVALID Booth Number for AstraZeneca. Try Again! ----");
                removePatient();  //Call the method to show vaccination selection

            }

        } else if (boothitem.equals("2") || boothitem.equalsIgnoreCase("SI")) {
            System.out.print("Enter booth number (2-3) to remove the patient  : ");

            removepatientboothNum = scapb.nextInt();

            if (removepatientboothNum < 2 || removepatientboothNum > 3) {
                System.out.println("\n---- INVALID Booth Number for Sinopharm. Try Again! ----");
                removePatient();  //Call the method to show vaccination selection
            }

        } else if (boothitem.equals("3") || boothitem.equalsIgnoreCase("PF")) {
            System.out.print("Enter booth number (4-5) to remove the patient  : ");

            removepatientboothNum = scapb.nextInt();

            if (removepatientboothNum < 4 || removepatientboothNum > 5) {
                System.out.println("\n---- INVALID Booth Number for Pfizer. Try Again! ----");
                removePatient(); //Call the method to show vaccination selection
            }

        }

        if (removepatientboothNum == 0) {
            astrazenecabooths[0] = null;
            //Remove Patient from lobby and add to Astrazeneca booth
            if (astrazenecall.size() > 0) {
                astrazenecabooths[0] = astrazenecall.remove();
            }

        } else if (removepatientboothNum == 1) {
            astrazenecabooths[1] = null;
            //Remove Patient from lobby and add to Astrazeneca booth
            if (astrazenecall.size() > 0) {
                astrazenecabooths[1] = astrazenecall.remove();
            }

        } else if (removepatientboothNum == 2) {
            sinopharmbooths[0] = null;
            //Remove Patient from lobby and add to Sinopharm booth
            if (sinopharmll.size() > 0) {
                sinopharmbooths[0] = sinopharmll.remove();
            }

        } else if (removepatientboothNum == 3) {
            sinopharmbooths[1] = null;
            //Remove Patient from lobby and add to Sinopharm booth
            if (sinopharmll.size() > 0) {
                sinopharmbooths[1] = sinopharmll.remove();
            }

        } else if (removepatientboothNum == 4) {
            pfizerbooths[0] = null;
            //Remove Patient from lobby and add to Pfizer booth
            if (pfizerll.size() > 0) {
                pfizerbooths[0] = pfizerll.remove();
            }

        } else if (removepatientboothNum == 5) {
            pfizerbooths[1] = null;
            //Remove Patient from lobby and add to Pfizer booth
            if (pfizerll.size() > 0) {
                pfizerbooths[1] = pfizerll.remove();
            }
        }

        ++stock;

        warnStock();
        viewBooths();
        showConsole();
    }

    //Method to View Patients Sorted in a alphabetical order
    private static void viewSortedPatients() {
        System.out.println("\n---- View Patients Sorted in alphabetical order ----");

        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i].getFirstname() != null && astrazenecabooths[i].getSurname() != null) {
                ++viewcount;
            }
        }

        for (int i = 0; i < sinopharmbooths.length; i++) {
            if (sinopharmbooths[i].getFirstname() != null && sinopharmbooths[i].getSurname() != null) {
                ++viewcount;
            }
        }

        for (int i = 0; i < pfizerbooths.length; i++) {
            if (pfizerbooths[i].getFirstname() != null && pfizerbooths[i].getSurname() != null) {
                ++viewcount;
            }
        }

        names = new String[viewcount];

        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i].getFirstname() != null && astrazenecabooths[i].getSurname() != null) {
                names[n] = astrazenecabooths[i].getFirstname() + " " + astrazenecabooths[i].getSurname(); //        0  <-  1
                n++;
            }
        }

        for (int i = 0; i < sinopharmbooths.length; i++) {
            if (sinopharmbooths[i].getFirstname() != null && sinopharmbooths[i].getSurname() != null) {
                names[n] = sinopharmbooths[i].getFirstname() + " " + sinopharmbooths[i].getSurname(); //        0  <-  1
                n++;
            }
        }

        for (int i = 0; i < pfizerbooths.length; i++) {
            if (pfizerbooths[i].getFirstname() != null && pfizerbooths[i].getSurname() != null) {
                names[n] = pfizerbooths[i].getFirstname() + " " + pfizerbooths[i].getSurname(); //        0  <-  1
                n++;
            }
        }

        sort(names);

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        showConsole();

    }

    //Method to Store Program Data into file
    private static void storeProgrammeData() {
        System.out.println("\n---- Store Program Data into file ----");

        try {

            if (filevac.createNewFile()) {
                System.out.println(filevac.getName() + " File created: ");
            } else {
                System.out.println(filevac.getName() + " File already exists.");
            }

            FileWriter fwvac = new FileWriter(filevac);

            for (int i = 0; i < astrazenecabooths.length; i++) {
                if (astrazenecabooths[i].getFirstname() != null && astrazenecabooths[i].getSurname() != null) {
                    fwvac.write(astrazenecabooths[i].getFirstname() + " " + astrazenecabooths[i].getSurname() + "-");
                }
            }

            for (int i = 0; i < sinopharmbooths.length; i++) {
                if (sinopharmbooths[i].getFirstname() != null && sinopharmbooths[i].getSurname() != null) {
                    fwvac.write(sinopharmbooths[i].getFirstname() + " " + sinopharmbooths[i].getSurname() + "-");
                }
            }

            for (int i = 0; i < pfizerbooths.length; i++) {
                if (pfizerbooths[i].getFirstname() != null && pfizerbooths[i].getSurname() != null) {
                    fwvac.write(pfizerbooths[i].getFirstname() + " " + pfizerbooths[i].getSurname() + "-");

                }
            }

            fwvac.close();

            System.out.println("Successfully wrote to the file " + filevac.getName());

        } catch (IOException e) {
            System.out.println("An error occurred with the file. Try Again!");
            showConsole();
        }

        showConsole();

    }

    //Method to Load Data from file
    private static void loadProgrammeData() {
        System.out.println("\n---- Load Program Data from a file ----");
        try {
            BufferedReader br = new BufferedReader(new FileReader(filevac));
            String st;

            while ((st = br.readLine()) != null) {
                System.out.println(st);
            }

            br.close();

        } catch (IOException ex) {
            System.out.println("An error occurred with the file. Try Again!");
            showConsole();
        }
        showConsole();
    }

    //Method to View Remaining Vaccinations
    private static void viewRemainingVaccinations() {
        System.out.println("\n----  View Remaining Vaccinations ----");

        System.out.println("Remaining Stock : " + stock);
        showConsole();
    }

    //Method to Add Vaccinations to the Stock
    private static void addVaccinations() {
        System.out.println("\n----  Add Vaccinations to the Stock ----");

        Scanner scavs = new Scanner(System.in);
        System.out.print("Enter vaccination count to add : ");
        int count = scavs.nextInt();

        if (count < 1) {
            System.out.println("\n----Vaccinations Count cannot be a negative value. Try Again! ----");
            addVaccinations();
        }

        stock += count;

        System.out.println("Updated Stock : " + stock);

        showConsole();

    }

    //Method to sort String array alphabetically
    private static void sort(String[] str) {
        String temp;
        for (int j = 0; j < str.length; j++) {
            for (int i = j + 1; i < str.length; i++) {
                // comparing strings
                if (str[i].compareTo(str[j]) < 0) {
                    temp = str[j];
                    str[j] = str[i];
                    str[i] = temp;
                }
            }
        }
    }

    private static void warnStock() {

        if (stock <= 20) {
            System.out.println("The Vaccination Stock Reaches 20. Reorder!");
        }

    }

    //Method to Display menu options
    private static void showConsole() {
        //Create scanner to accept menu option input
        Scanner scconsole = new Scanner(System.in);
        //Show menu option guide
        System.out.println("\n\nHello! Select Functionality by entering the following codes."
                + "\n100 or VVB: View all Vaccination Booths"
                + "\n101 or VEB: View all Empty Booths"
                + "\n102 or APB: Add Patient to a Booth"
                + "\n103 or RPB: Remove Patient from a Booth"
                + "\n104 or VPS: View Patients Sorted in a alphabetical order"
                + "\n105 or SPD: Store Program Data into file"
                + "\n106 or LPD: Load Data from file"
                + "\n107 or VRV: View Remaining Vaccinations"
                + "\n108 or AVS: Add Vaccinations to the Stock"
                + "\n999 or EXT: Exit the Program\n");

        //Accept user input selecting the menu option
        String consoleitem = scconsole.next();

        //Call method for each menu option
        if (consoleitem.equals("100") || consoleitem.equalsIgnoreCase("VVB")) {
            viewAllBooths(); //method call
        } else if (consoleitem.equals("101") || consoleitem.equalsIgnoreCase("VEB")) {
            viewEmptyBooths(); //method call
        } else if (consoleitem.equals("102") || consoleitem.equalsIgnoreCase("APB")) {
            loadPatientFrame(); //method call
        } else if (consoleitem.equals("103") || consoleitem.equalsIgnoreCase("RPB")) {
            removePatient(); //method call
        } else if (consoleitem.equals("104") || consoleitem.equalsIgnoreCase("VPS")) {
            viewSortedPatients();  //method call
        } else if (consoleitem.equals("105") || consoleitem.equalsIgnoreCase("SPD")) {
            storeProgrammeData();  //method call
        } else if (consoleitem.equals("106") || consoleitem.equalsIgnoreCase("LPD")) {
            loadProgrammeData();  //method call
        } else if (consoleitem.equals("107") || consoleitem.equalsIgnoreCase("VRV")) {
            viewRemainingVaccinations(); //method call
        } else if (consoleitem.equals("108") || consoleitem.equalsIgnoreCase("AVS")) {
            addVaccinations(); //method call
        } else if (consoleitem.equals("999") || consoleitem.equalsIgnoreCase("EXT")) {
            System.exit(0);  //Call Exit method
        } else {
            //Display error message
            System.out.println("\n---- INVALID INPUT. Try Again! ----");
            showConsole();   //Call the method to show menu options
        }
    }

    void inputPatientDetails(String firstName, String surname, String city, String nicorpp, int age, String vaccinetype, String boothnum) {


        Patient currentpatient = new Patient(firstName, surname, city, nicorpp, age);
        String requestedvaccinetype = vaccinetype;

        addPatient(currentpatient, requestedvaccinetype, Integer.parseInt(boothnum));

    }
}