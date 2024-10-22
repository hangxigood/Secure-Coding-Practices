import java.util.ArrayList;
import java.util.Scanner;

public class ManageComputers {

    public static void main(String args[]) {
        // This ArrayList will hold all the computers in the system. Note that the type of objects expected in this
        // ArrayList are Computer, not Laptop or Desktop, but since those are subclasses of Computer they can be
        // stored in an ArrayList<Computer> anyway.
        ArrayList<Computer> computers = new ArrayList<Computer>(); 

        Scanner s = new Scanner(System.in);
        String menuOption = "";

        do { // Start of main program loop

            // Show computer data in ArrayList<Computer>
            showComputers(computers); 

            // Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            switch (menuOption) {
                // Add new computer
                case "a": 
                    addComputer(computers, s);
                    break;

                // Delete a computer    
                case "d": 
                    deleteComputer(computers, s);
                    break;

                // Edit a computer    
                case "e": 
                    editComputer(computers, s);
                    break;
            }

        } while (!menuOption.equals("x")); // Stop when "x" is entered

        s.close(); // Close keyboard scanner

    } // End of main

    //-----------------------------
    // Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption = "";

        // Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        // Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase(); // Make lower case for comparison purposes

        return menuOption;
    } // End of getMenuSelection

    //-----------------------------
    // Show data for all laptops and desktops stored in ArrayList<Computer> create in main() method
    private static void showComputers(ArrayList<Computer> computers) {
        int computerListNumber = 0; // This variable is used to hold the "list number" for each computer, starting at 1.

        System.out.println("=========");
        System.out.println("LIST OF COMPUTERS:-");

        for (Computer c : computers) {
            computerListNumber++; // Increment list number for each computer

            // Call overridden toString() method for current object to get and display its data
            System.out.println(computerListNumber + ": " + c.toString());
        }

        System.out.println("=========");

    } // End of showComputers

    //-----------------------------
    // Add a new Laptop or Desktop computer to the ArrayList<Computer>
    private static void addComputer(ArrayList<Computer> computers, Scanner s) {
        String computerType = "";
        Computer tempComputer = null;

        System.out.println("ADDING COMPUTER:-");
        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType = s.nextLine();
        computerType = computerType.toLowerCase(); // Convert to lower case for comparison purposes

        switch (computerType) {
            // Add a laptop
            case "l": 
                tempComputer = getComputerData(s); 
                System.out.print("Enter screen size:");
                String screenSize = s.nextLine();
                computers.add(new Laptop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(), screenSize)); 
                break;
            
            // Add a desktop    
            case "d": 
                tempComputer = getComputerData(s); 
                System.out.print("Enter GPU:");
                String GPUType = s.nextLine();
                computers.add(new Desktop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(), GPUType)); 
                break;

            // Invalid computer type to add entered
            default:
                System.out.println("Invalid computer type entered!");
        }
    } // End of addComputer

    //-----------------------------
    // Delete a specified computer from the ArrayList
    private static void deleteComputer(ArrayList<Computer> computers, Scanner s) {
        int computerListNumberToDelete = 0;

        System.out.println("DELETE COMPUTER:-");
        System.out.print("Enter number of computer to delete:");
        computerListNumberToDelete = Integer.parseInt(s.nextLine());

        // Check if computer list number is valid before deleting computer from list
        if (computerListNumberToDelete >= 1 && computerListNumberToDelete <= computers.size()) {
            computers.remove(computerListNumberToDelete - 1); // Subtract 1 to get correct ArrayList index
        } else {
            System.out.println("Invalid computer number entered!");
        }

    } // End of deleteComputer

    //-----------------------------
    // Edit a computer. Since Laptop and Desktop are now immutable, replace old object with a new one
    private static void editComputer(ArrayList<Computer> computers, Scanner s) {
        int computerListNumberToEdit = 0;
        String computerType = "";
        Computer tempComputer = null;

        System.out.println("EDIT COMPUTER:-");
        System.out.print("Enter number of computer to edit:");
        computerListNumberToEdit = Integer.parseInt(s.nextLine());

        // Check that computerListNumberToEdit is valid first
        if (computerListNumberToEdit >= 1 && computerListNumberToEdit <= computers.size()) {

            if (computers.get(computerListNumberToEdit - 1) instanceof Laptop) { 
                computerType = "laptop";
            } else if (computers.get(computerListNumberToEdit - 1) instanceof Desktop) { 
                computerType = "desktop";
            }

            switch (computerType) {
                // Editing a laptop
                case "laptop": 
                    System.out.println("Editing a Laptop:");
                    tempComputer = getComputerData(s); 
                    System.out.print("Enter screen size:");
                    String newScreenSize = s.nextLine();
                    computers.set(computerListNumberToEdit - 1, 
                        new Laptop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(), newScreenSize));
                    break;

                // Editing a desktop
                case "desktop": 
                    System.out.println("Editing a Desktop:");
                    tempComputer = getComputerData(s); 
                    System.out.print("Enter GPU:");
                    String newGPUType = s.nextLine();
                    computers.set(computerListNumberToEdit - 1, 
                        new Desktop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(), newGPUType));
                    break;

                default:
                    System.out.println("Invalid computer type!");
            }
        } else {
            System.out.println("Invalid computer number entered!");
        }

    } // End of editComputer

    //-----------------------------
    // Helper method to get data common to Laptop and Desktop (CPU, RAM and disk) objects.
    // Returns a Computer-type object holding these values as attributes.
    private static Computer getComputerData(Scanner s) {
        System.out.print("Enter CPU:");
        String CPU = s.nextLine();
        System.out.print("Enter RAM:");
        String RAM = s.nextLine();
        System.out.print("Enter Disk:");
        String disk = s.nextLine();

        return new Computer(CPU, RAM, disk);
    } // End of getComputerData

} // End of ManageComputer class
