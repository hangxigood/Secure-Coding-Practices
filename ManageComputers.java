import java.util.ArrayList;
import java.util.Scanner;

public class ManageComputers {

    // Define whitelist arrays for validation
    private static final String[] CPU_WHITELIST = { "i5", "i7" };
    private static final String[] RAM_WHITELIST = { "16", "32" };
    private static final String[] DISK_WHITELIST = { "512", "1024" };
    private static final String[] GPU_WHITELIST = { "Nvidia", "AMD" };
    private static final String[] SCREEN_SIZE_WHITELIST = { "13", "14" };

    public static void main(String args[]) {
        ArrayList<Computer> computers = new ArrayList<Computer>();

        Scanner s = new Scanner(System.in);
        String menuOption = "";

        do {
            showComputers(computers);
            menuOption = getMenuSelection(s);

            switch (menuOption) {
                case "a":
                    addComputer(computers, s);
                    break;
                case "d":
                    deleteComputer(computers, s);
                    break;
                case "e":
                    editComputer(computers, s);
                    break;
            }

        } while (!menuOption.equals("x"));

        s.close();
    }

    // -----------------------------
    // Helper method to validate input against whitelist
    private static boolean isValidInput(String input, String[] whitelist) {
        for (String validValue : whitelist) {
            if (validValue.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    // -----------------------------
    // Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption = "";

        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        System.out.print("Enter menu selection:");
        menuOption = s.nextLine().toLowerCase();

        return menuOption;
    }

    // -----------------------------
    // Show data for all laptops and desktops stored in ArrayList<Computer>
    private static void showComputers(ArrayList<Computer> computers) {
        int computerListNumber = 0;

        System.out.println("=========");
        System.out.println("LIST OF COMPUTERS:-");

        for (Computer c : computers) {
            computerListNumber++;
            System.out.println(computerListNumber + ": " + c.toString());
        }

        System.out.println("=========");
    }

    // -----------------------------
    // Add a new Laptop or Desktop computer to the ArrayList<Computer>
    private static void addComputer(ArrayList<Computer> computers, Scanner s) {
        String computerType = "";
        Computer tempComputer = null;

        System.out.println("ADDING COMPUTER:-");
        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType = s.nextLine().toLowerCase();

        switch (computerType) {
            case "l":
                tempComputer = getComputerData(s);

                String screenSize;
                do {
                    System.out.print("Enter screen size (13/14): ");
                    screenSize = s.nextLine();
                } while (!isValidInput(screenSize, SCREEN_SIZE_WHITELIST));

                computers.add(
                        new Laptop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(), screenSize));
                break;

            case "d":
                tempComputer = getComputerData(s);

                String GPUType;
                do {
                    System.out.print("Enter GPU (Nvidia/AMD): ");
                    GPUType = s.nextLine();
                } while (!isValidInput(GPUType, GPU_WHITELIST));

                computers.add(
                        new Desktop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(), GPUType));
                break;

            default:
                System.out.println("Invalid computer type entered!");
        }
    }

    // -----------------------------
    // Delete a specified computer from the ArrayList
    private static void deleteComputer(ArrayList<Computer> computers, Scanner s) {
        int computerListNumberToDelete = 0;

        System.out.println("DELETE COMPUTER:-");
        System.out.print("Enter number of computer to delete:");
        computerListNumberToDelete = Integer.parseInt(s.nextLine());

        if (computerListNumberToDelete >= 1 && computerListNumberToDelete <= computers.size()) {
            computers.remove(computerListNumberToDelete - 1);
        } else {
            System.out.println("Invalid computer number entered!");
        }
    }

    // -----------------------------
    // Edit a computer
    private static void editComputer(ArrayList<Computer> computers, Scanner s) {
        int computerListNumberToEdit = 0;
        String computerType = "";
        Computer tempComputer = null;

        System.out.println("EDIT COMPUTER:-");
        System.out.print("Enter number of computer to edit:");
        computerListNumberToEdit = Integer.parseInt(s.nextLine());

        if (computerListNumberToEdit >= 1 && computerListNumberToEdit <= computers.size()) {

            if (computers.get(computerListNumberToEdit - 1) instanceof Laptop) {
                computerType = "laptop";
            } else if (computers.get(computerListNumberToEdit - 1) instanceof Desktop) {
                computerType = "desktop";
            }

            switch (computerType) {
                case "laptop":
                    System.out.println("Editing a Laptop:");
                    tempComputer = getComputerData(s);

                    String newScreenSize;
                    do {
                        System.out.print("Enter screen size (13/14): ");
                        newScreenSize = s.nextLine();
                    } while (!isValidInput(newScreenSize, SCREEN_SIZE_WHITELIST));

                    computers.set(computerListNumberToEdit - 1,
                            new Laptop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(),
                                    newScreenSize));
                    break;

                case "desktop":
                    System.out.println("Editing a Desktop:");
                    tempComputer = getComputerData(s);

                    String newGPUType;
                    do {
                        System.out.print("Enter GPU (Nvidia/AMD): ");
                        newGPUType = s.nextLine();
                    } while (!isValidInput(newGPUType, GPU_WHITELIST));

                    computers.set(computerListNumberToEdit - 1,
                            new Desktop(tempComputer.getCPU(), tempComputer.getRAM(), tempComputer.getDisk(),
                                    newGPUType));
                    break;

                default:
                    System.out.println("Invalid computer type!");
            }
        } else {
            System.out.println("Invalid computer number entered!");
        }
    }

    // -----------------------------
    // Helper method to get data common to Laptop and Desktop (CPU, RAM, and Disk)
    private static Computer getComputerData(Scanner s) {
        String CPU, RAM, disk;

        do {
            System.out.print("Enter CPU (i5/i7): ");
            CPU = s.nextLine();
        } while (!isValidInput(CPU, CPU_WHITELIST));

        do {
            System.out.print("Enter RAM (16/32): ");
            RAM = s.nextLine();
        } while (!isValidInput(RAM, RAM_WHITELIST));

        do {
            System.out.print("Enter Disk (512/1024): ");
            disk = s.nextLine();
        } while (!isValidInput(disk, DISK_WHITELIST));

        return new Computer(CPU, RAM, disk);
    }

}
