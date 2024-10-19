public class Computer {

    // For immutability
    private final String CPU;
    private final String RAM;
    private final String disk;

    // Constructors
    public Computer(String CPU, String RAM, String disk) {
        if (CPU == null || CPU.isEmpty()) throw new IllegalArgumentException("CPU cannot be null or empty");
        if (RAM == null || RAM.isEmpty()) throw new IllegalArgumentException("RAM cannot be null or empty");
        if (disk == null || disk.isEmpty()) throw new IllegalArgumentException("Disk cannot be null or empty");

        this.CPU = CPU;
        this.RAM = RAM;
        this.disk = disk;
    }

    // Getter methods to access the fields
    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getDisk() {
        return disk;
    }

    // Overriding toString to return a formatted string representation of the Computer object
    @Override
    public String toString() {
        return "CPU: " + CPU + ", RAM: " + RAM + ", Disk: " + disk;
    }
}

// The immutable Laptop class that uses composition with Computer
public class Laptop {

    // Private final fields for immutability
    private final Computer computer;  // Composition: Laptop "has-a" Computer
    private final String screenSize;

    // Constructors
    public Laptop(String CPU, String RAM, String disk, String screenSize) {
        if (screenSize == null || screenSize.isEmpty()) throw new IllegalArgumentException("Screen size cannot be null or empty");

        this.computer = new Computer(CPU, RAM, disk);  // Composition over inheritance
        this.screenSize = screenSize;
    }

    // Getter method for the screen size
    public String getScreenSize() {
        return screenSize;
    }

    // Getters to retrieve computer-related information through the composed Computer object
    public String getCPU() {
        return computer.getCPU();
    }

    public String getRAM() {
        return computer.getRAM();
    }

    public String getDisk() {
        return computer.getDisk();
    }

    // Overriding toString to provide a formatted string representation of the Laptop object
    @Override
    public String toString() {
        return "Type: Laptop, " + computer.toString() + ", Screen size: " + screenSize;
    }
}

// Example usage in the main method
public class Main {
    public static void main(String[] args) {
        // Creating a Laptop object with valid details
        Laptop laptop = new Laptop("Intel i7", "16GB", "512GB SSD", "15.6 inches");

        // Accessing the details of the laptop
        System.out.println("Laptop details: ");
        System.out.println("CPU: " + laptop.getCPU());
        System.out.println("RAM: " + laptop.getRAM());
        System.out.println("Disk: " + laptop.getDisk());
        System.out.println("Screen Size: " + laptop.getScreenSize());

        // Displaying the laptop using toString
        System.out.println("\nFormatted Laptop details: ");
        System.out.println(laptop.toString());
    }
}
