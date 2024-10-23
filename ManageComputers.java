//Manage Computers program: maintains an ArrayList of Computer objects, 
//can be either Laptop or Desktop, but never just Computer-type objects themselves

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.IntStream;

public class ManageComputers {

    // Validation whitelists
    private static final Set<String> VALID_CPUS = Set.of("i5", "i7");
    private static final Set<String> VALID_RAM = Set.of("16", "32");
    private static final Set<String> VALID_DISK = Set.of("512", "1024");
    private static final Set<String> VALID_GPU = Set.of("Nvidia", "AMD");
    private static final Set<String> VALID_SCREEN = Set.of("13", "14");

    // Helper method to validate input
    private static String getValidInput(Scanner s, String prompt, Set<String> validValues) {
        String input;
        do {
            System.out.print(prompt);
            input = s.nextLine().trim();
            if (!validValues.contains(input)) {
                System.out.println("Error: '" + input + "' is not valid.");
                System.out.println("Please enter one of these values: " + String.join(", ", validValues));
            }
        } while (!validValues.contains(input));
        return input;
    }

    public static void main(String args[]) {

        //This ArrayList will hold all the computers in the system. Note that the type of objects expected in this
        //ArrayList are Computer, not Laptop or Desktop, but since those are subclasses of Computer they can be
        //stored in an ArrayLiust<Computer> anyway.
        ArrayList<Object> computers = new ArrayList<Object>(); 

        Scanner s = new Scanner(System.in);
        String menuOption="";

        do { //Start of main program loop

            //Show computer data in ArrayList<Computer>
            showComputers(computers); 

            //Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            switch(menuOption) {
                //Add new computer
                case "a": 

                    addComputer(computers,s);

                    break;

                //Delete a computer    
                case "d": 

                    deleteComputer(computers,s);

                    break;

                //Edit a computer    
                case "e": 

                    editComputer(computers, s);

                    break;

            }

        } while ( ! menuOption.equals("x") ); //Stop when "x" is entered

        s.close(); //Close keyboard scanner

    } //End of main

    //-----------------------------
    //Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption="";

        //Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        //Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase(); //Make lower case for comparison purposes

        return menuOption;
    } //End of getMenuSelection

    //-----------------------------
    //Show data for all laptops and desktops stored in ArrayList<Computer> create in main() method
    private static void showComputers(ArrayList<Object> computers) {
        int computerListNumber=0; //This variable is used to hold the "list number" for each computer, starting at 1.

        System.out.println("=========");

        System.out.println("LIST OF COMPUTERS:-");

        for (Object c: computers) {

            computerListNumber++; //Increment list number for each computer

            //Call overridden toString() method for current object to get and display its data
            System.out.println(computerListNumber + ": " + c.toString());
        }

        System.out.println("=========");

    } //End of showComputers

    //-----------------------------
    //Add a new Laptop or Desktop computer to the ArrayList<Computer>
    private static void addComputer(ArrayList<Object> computers, Scanner s) {
        System.out.println("ADDING COMPUTER:-");
        String computerType = getValidInput(s, 
            "Enter type of computer to add ('L' for Laptop, 'D' for Desktop): ", 
            Set.of("l", "d"));

        Computer baseComputer = getComputerData(s);

        switch(computerType) {
            case "l":
                String screenSize = getValidInput(s, 
                    "Enter screen size (13/14): ", 
                    VALID_SCREEN);
                computers.add(Laptop.createLaptop(
                    baseComputer.getCpu(),
                    baseComputer.getRam(),
                    baseComputer.getDisk(),
                    screenSize));
                break;
            
            case "d":
                String gpuType = getValidInput(s, 
                    "Enter GPU (Nvidia/AMD): ", 
                    VALID_GPU);
                computers.add(Desktop.createDesktop(
                    baseComputer.getCpu(),
                    baseComputer.getRam(),
                    baseComputer.getDisk(),
                    gpuType));
                break;
        }
    } //End of addComputer

    //-----------------------------
    //Delete a specified computer from the ArrayList
    private static void deleteComputer(ArrayList<Object> computers, Scanner s) {
        int computerListNumberToDelete=0;

        System.out.println("DELETE COMPUTER:-");

        System.out.print("Enter number of computer to delete:");
        computerListNumberToDelete = Integer.parseInt(s.nextLine());

        //Check if computer list number is valid before deleting computer from list
        if (computerListNumberToDelete>=1 && computerListNumberToDelete<=computers.size()) {
            //Subtract 1 to get ArrayList index from on-screen list number to create correct index in ArrayList to delete
            computers.remove(computerListNumberToDelete-1); 
        }   
        else {
            System.out.println("Invalid computer number entered!");
        }

    } //End of deleteComputer

    //-----------------------------
    //Edit a computer. Since Laptop and Desktop are mutable classses/object get new data values and replace old
    //attribute values in object being edited using object setter methods
    private static void editComputer(ArrayList<Object> computers, Scanner s) {
        System.out.println("EDIT COMPUTER:-");
        
        int computerListNumberToEdit = Integer.parseInt(
            getValidInput(s, "Enter number of computer to edit: ", 
            Set.of(IntStream.rangeClosed(1, computers.size())
                .mapToObj(String::valueOf)
                .toArray(String[]::new)))
        );

        int index = computerListNumberToEdit - 1;
        Object currentComputer = computers.get(index);
        Computer baseComputer = getComputerData(s);

        if (currentComputer instanceof Laptop) {
            String screenSize = getValidInput(s, 
                "Enter screen size (13/14): ", 
                VALID_SCREEN);
            computers.set(index, Laptop.createLaptop(
                baseComputer.getCpu(),
                baseComputer.getRam(),
                baseComputer.getDisk(),
                screenSize));
        } else if (currentComputer instanceof Desktop) {
            String gpuType = getValidInput(s, 
                "Enter GPU (Nvidia/AMD): ", 
                VALID_GPU);
            computers.set(index, Desktop.createDesktop(
                baseComputer.getCpu(),
                baseComputer.getRam(),
                baseComputer.getDisk(),
                gpuType));
        }
    } //End of editComputer

    //-----------------------------
    //Helper method to get data common to Laptop and Desktop (CPU, RAM and disk) objects. Returns a Computer-type object
    //holding these values as attribues
    private static Computer getComputerData(Scanner s) {
        String cpu = getValidInput(s, "Enter CPU (i5/i7): ", VALID_CPUS);
        String ram = getValidInput(s, "Enter RAM (16/32): ", VALID_RAM);
        String disk = getValidInput(s, "Enter Disk (512/1024): ", VALID_DISK);
        return Computer.createComputer(cpu, ram, disk);
    } //End of getComputerData


} //End of ManageComputer class
