import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VaccinationCenter_Task1 {

    //Static variable declaration
    static int stock = 150;
    static String[] booths = new String[6]; //0-5
    static String[] names;
    static int viewcount;
    static File filevac = new File("VaccinationData.txt");

    //Run the programme
    public static void main(String[] args) {
        //Call the method to show menu options
        showConsole();
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
            addPatient(); //method call
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
            //Disply error message
            System.out.println("\n---- INVALID INPUT. Try Again! ----");
            showConsole();   //Call the method to show menu options
        }
    }

    //Method to View All Vaccination Booths
    private static void viewAllBooths() {

        System.out.println("\n---- View all Vaccination Booths ----");

        //Iterate through booths
        for (int i = 0; i < booths.length; i++) {
            System.out.println("Booth #" + i);
        }
        showConsole(); //Call the method to show menu options

    }

    //Method to View All Empty Vaccination Booths
    private static void viewEmptyBooths() {
        System.out.println("\n---- View all Empty Booths ----");

        //Iterate through booths
        for (int i = 0; i < booths.length; i++) {
            //Check whether the booth is empty
            if (booths[i] == null) {
                System.out.println("Booth #" + i + " - Empty ");
            }
        }
        showConsole();  //Call the method to show menu options
    }

    //Method to Add Patient to a Booth
    private static void addPatient() {

        System.out.println("\n---- Add Patient to a Booth ----");
        //Create scanner to accept booth number input
        Scanner scapb = new Scanner(System.in);
        System.out.println("Enter booth number (0-5) to add the patient");
        //Accept user input for booth number
        int currentboothNum = scapb.nextInt();
        //check whether the booth number is valid
        if (currentboothNum < 0 || currentboothNum > 5) {
            System.out.println("\n---- INVALID Booth Number. Try Again! ----");
            addPatient();
        }

        System.out.println("Enter patient name for booth " + currentboothNum + " :");
        String patientName = scapb.next();
        booths[currentboothNum] = patientName;
        --stock;

        warnStock();

        viewBooths();
        showConsole();
    }

    //Method to Remove Patient from a Booth
    private static void removePatient() {
        System.out.println("\n---- Remove Patient from a Booth ----");

        Scanner scapb = new Scanner(System.in);
        System.out.println("Enter booth number (0-5) to remove the patient");

        int removepatientboothNum = scapb.nextInt();
        if (removepatientboothNum < 0 || removepatientboothNum > 5) {
            System.out.println("\n---- INVALID Booth Number. Try Again! ----");
            removePatient();
        }

        booths[removepatientboothNum] = null;
        ++stock;

        warnStock();
        viewBooths();
        showConsole();
    }

    //Method to View Available Booths
    private static void viewBooths() {
        for (int i = 0; i < booths.length; i++) {
            if (booths[i] == null) {
                System.out.println("Booth #" + i + " - Empty ");
            } else {
                System.out.println("Booth #" + i + " - " + booths[i]);
            }
        }
    }

    //Method to View Patients Sorted in a alphabetical order
    private static void viewSortedPatients() {
        System.out.println("\n---- View Patients Sorted in alphabetical order ----");

        for (int i = 0; i < booths.length; i++) {
            if (booths[i] != null) {
                ++viewcount;
            }
        }

        names = new String[viewcount];

        int n = 0;
        for (int i = 0; i < booths.length; i++) {
            if (booths[i] != null) {
                names[n] = booths[i]; //        0  <-  1
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

            for (int i = 0; i < booths.length; i++) {
                fwvac.write(booths[i] + "-");
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

}
