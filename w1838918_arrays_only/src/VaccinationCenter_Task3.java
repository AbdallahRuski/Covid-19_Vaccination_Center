import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VaccinationCenter_Task3 {

    //Static variable declaration
    static int stock = 150;

    static String[][] astrazenecabooths = new String[2][2]; //0-1
    static String[][] sinopharmbooths = new String[2][2]; //2-3
    static String[][] pfizerbooths = new String[2][2]; //4-5
    static String[][] vaccinebooth;

    static String[] names;

    static int viewcount;
    static File filevac = new File("VaccinationData.txt");

    static int currentboothNum;
    static int removepatientboothNum;
    static int n = 0;

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

        viewBooths();
        showConsole(); //Call the method to show menu options

    }

    //Method to View All Empty Vaccination Booths
    private static void viewEmptyBooths() {
        System.out.println("\n---- View all Empty Booths ----");

        //Iterate through booths
        System.out.println("\n---- AstraZeneca Empty Vaccination Booths ----");
        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i][0] == null && astrazenecabooths[i][1] == null) {
                System.out.println("Booth #" + i + " - Empty ");
            }
        }

        System.out.println("\n---- Sinopharm Empty Vaccination Booths ----");
        for (int i = 0; i < sinopharmbooths.length; i++) {
            int view = i + 2;
            if (sinopharmbooths[i][0] == null && sinopharmbooths[i][1] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            }
        }

        System.out.println("\n---- Pfizer Empty Vaccination Booths ----");
        for (int i = 0; i < pfizerbooths.length; i++) {
            int view = i + 4;
            if (pfizerbooths[i][0] == null && pfizerbooths[i][1] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            }
        }

        showConsole();  //Call the method to show menu options
    }

    //Method to Add Patient to a Booth
    private static void addPatient() {

        System.out.println("\n---- Add Patient to a Booth ----");
        //Create scanner to accept booth number input

        Scanner scboothselect = new Scanner(System.in);
        System.out.println("Select the prefered vaccine by entering the following codes. \n "
                + " 1 or AZ: AstraZeneca "
                + " 2 or SI: Sinopharm "
                + " 3 or PF: Pfizer ");

        //Accept user input selecting the vaccine option
        String boothitem = scboothselect.next();
        Scanner scapb = new Scanner(System.in);



        System.out.print("Enter patient first name for booth " + currentboothNum + " :");
        String firstName = scapb.next();

        System.out.print("Enter patient surname for booth " + currentboothNum + " :");
        String surname = scapb.next();
        
        
        
         if (boothitem.equals("1") || boothitem.equalsIgnoreCase("AZ")) {

            if (astrazenecabooths[0][0] == null && astrazenecabooths[1][0] == null) {

                astrazenecabooths[0][0] = firstName;
                astrazenecabooths[0][1] = surname;

            } else if (astrazenecabooths[1][0] == null && astrazenecabooths[1][1] == null) {
               astrazenecabooths[1][0] = firstName;
                astrazenecabooths[1][1] = surname;

            } else if(astrazenecabooths[0] != null && astrazenecabooths[1] != null) {
                System.out.println("\n---- All Booths are occupied. Try Again! ----");
                addPatient();
            }

        } else if (boothitem.equals("2") || boothitem.equalsIgnoreCase("SI")) {
           if (sinopharmbooths[0][0] == null && sinopharmbooths[0][1] == null ) {

                sinopharmbooths[0][0] = firstName;
                sinopharmbooths[0][1] = surname;

            } else if (sinopharmbooths[1][0] == null && sinopharmbooths[1][1] == null) {
               sinopharmbooths[1][0] = firstName;
                sinopharmbooths[1][1] = surname;


            } else {
                System.out.println("\n---- All Booths are occupied. Try Again! ----");
                addPatient();
            }

        } else if (boothitem.equals("3") || boothitem.equalsIgnoreCase("PF")) {
            if (pfizerbooths[0][0] == null && pfizerbooths[0][1] == null) {

                pfizerbooths[0][0] = firstName;
                pfizerbooths[0][1] = surname;

            } else if (pfizerbooths[1][0] == null && pfizerbooths[1][1] == null) {
                pfizerbooths[1][0] = firstName;
                pfizerbooths[1][1] = surname;

            } else {
                System.out.println("\n---- All Booths are occupied. Try Again! ----");
                addPatient();
            }

        } else {
            System.out.println("\n---- INVALID Vaccination Selection. Try Again! ----");
            addPatient();
        }

        
        --stock;

        warnStock();

        viewBooths();
        showConsole();
    }

    //Method to Remove Patient from a Booth
    private static void removePatient() {
        System.out.println("\n---- Remove Patient from a Booth ----");

        Scanner scboothselect = new Scanner(System.in);
        System.out.println("Select the prefered vaccine by entering the following codes. \n "
                + "1 or AZ: AstraZeneca"
                + "2 or SI: Sinopharm"
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
            astrazenecabooths[0][0] = null;
            astrazenecabooths[0][1] = null;
        } else if (removepatientboothNum == 1) {
            astrazenecabooths[1][0] = null;
            astrazenecabooths[1][1] = null;
        } else if (removepatientboothNum == 2) {
            sinopharmbooths[0][0] = null;
            sinopharmbooths[0][1] = null;
        } else if (removepatientboothNum == 3) {
            sinopharmbooths[1][0] = null;
            sinopharmbooths[1][1] = null;
        } else if (removepatientboothNum == 4) {
            pfizerbooths[0][0] = null;
            pfizerbooths[0][1] = null;
        } else if (removepatientboothNum == 5) {
            pfizerbooths[1][0] = null;
            pfizerbooths[1][1] = null;
        }

        ++stock;

        warnStock();
        viewBooths();
        showConsole();
    }

    //Method to View Available Booths
    private static void viewBooths() {

        System.out.println("\n---- AstraZeneca  Vaccination Booths ----");
        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i][0] == null && astrazenecabooths[i][1] == null) {
                System.out.println("Booth #" + i + " - Empty ");
            } else {
                System.out.println("Booth #" + i + " - " + astrazenecabooths[i][0] + " " + astrazenecabooths[i][1]);
            }
        }

        System.out.println("\n---- Sinopharm  Vaccination Booths ----");
        for (int i = 0; i < sinopharmbooths.length; i++) {

            int view = i + 2;

            if (sinopharmbooths[i][0] == null && sinopharmbooths[i][1] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            } else {
                System.out.println("Booth #" + view + " - " + sinopharmbooths[i][0] + " " + sinopharmbooths[i][1]);
            }
        }

        System.out.println("\n---- Pfizer  Vaccination Booths ----");
        for (int i = 0; i < pfizerbooths.length; i++) {
            int view = i + 4;
            if (pfizerbooths[i][0] == null && pfizerbooths[i][1] == null) {
                System.out.println("Booth #" + view + " - Empty ");
            } else {
                System.out.println("Booth #" + view + " - " + pfizerbooths[i][0] + " " + pfizerbooths[i][1]);
            }
        }

    }

    //Method to View Patients Sorted in a alphabetical order
    private static void viewSortedPatients() {
        System.out.println("\n---- View Patients Sorted in alphabetical order ----");

        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i][0] != null && astrazenecabooths[i][1] != null) {
                ++viewcount;
            }
        }

        for (int i = 0; i < sinopharmbooths.length; i++) {
            if (sinopharmbooths[i][0] != null && sinopharmbooths[i][1] != null) {
                ++viewcount;
            }
        }

        for (int i = 0; i < pfizerbooths.length; i++) {
            if (pfizerbooths[i][0] != null && pfizerbooths[i][1] != null) {
                ++viewcount;
            }
        }

        names = new String[viewcount];

        for (int i = 0; i < astrazenecabooths.length; i++) {
            if (astrazenecabooths[i][0] != null && astrazenecabooths[i][1] != null) {
                names[n] = astrazenecabooths[i][0] + " " + astrazenecabooths[i][1]; //        0  <-  1
                n++;
            }
        }

        for (int i = 0; i < sinopharmbooths.length; i++) {
            if (sinopharmbooths[i][0] != null && sinopharmbooths[i][1] != null) {
                names[n] = sinopharmbooths[i][0] + " " + sinopharmbooths[i][1]; //        0  <-  1
                n++;
            }
        }

        for (int i = 0; i < pfizerbooths.length; i++) {
            if (pfizerbooths[i][0] != null && pfizerbooths[i][1] != null) {
                names[n] = pfizerbooths[i][0] + " " + pfizerbooths[i][1]; //        0  <-  1
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
                if (astrazenecabooths[i][0] != null && astrazenecabooths[i][1] != null) {
                    fwvac.write(astrazenecabooths[i][0] + " " + astrazenecabooths[i][1] + "-");
                }
            }

            for (int i = 0; i < sinopharmbooths.length; i++) {
                if (sinopharmbooths[i][0] != null && sinopharmbooths[i][1] != null) {
                    fwvac.write(sinopharmbooths[i][0] + " " + sinopharmbooths[i][1] + "-");
                }
            }

            for (int i = 0; i < pfizerbooths.length; i++) {
                if (pfizerbooths[i][0] != null && pfizerbooths[i][1] != null) {
                    fwvac.write(pfizerbooths[i][0] + " " + pfizerbooths[i][1] + "-");

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

}
