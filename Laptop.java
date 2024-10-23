// Laptop class: Represents a laptop computer
// Uses composition to include Computer functionality plus screen size information
// Class is immutable (final) and all fields are final
public final class Laptop {
    // Composition: contains a Computer instance instead of inheriting from it
    private final Computer computer;    // Core computer specifications
    private final String screenSize;    // Laptop-specific screen size information

    // Private constructor enforces use of factory method
    private Laptop(Computer computer, String screenSize) {
        this.computer = computer;
        this.screenSize = screenSize;
    }

    // Factory method to create Laptop instances
    // Parameters:
    //   cpu        - CPU type (i5/i7)
    //   ram        - RAM amount (16/32)
    //   disk       - Disk size (512/1024)
    //   screenSize - Screen size (13/14)
    // Returns: new immutable Laptop instance
    public static Laptop createLaptop(String cpu, String ram, String disk, String screenSize) {
        return new Laptop(Computer.createComputer(cpu, ram, disk), screenSize);
    }

    // Delegate methods to access Computer properties
    public String getCpu() {
        return computer.getCpu();
    }

    public String getRam() {
        return computer.getRam();
    }

    public String getDisk() {
        return computer.getDisk();
    }

    // Laptop-specific getter
    public String getScreenSize() {
        return screenSize;
    }

    // Provides formatted string representation of laptop specifications
    // Format: "Type:Laptop    [computer specs]    Screen:[value]"
    @Override
    public String toString() {
        return "Type:Laptop\t" + computer.toString() + "\tScreen:" + screenSize;
    }
}
