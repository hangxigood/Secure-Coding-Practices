

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