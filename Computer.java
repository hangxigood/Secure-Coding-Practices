// Computer class: Core class that manages basic computer specifications
// This class is immutable (final) and all its fields are final

public final class Computer {
    // Immutable fields for basic computer specifications
    private final String cpu;    // Stores CPU type (e.g., "i5", "i7")
    private final String ram;    // Stores RAM amount (e.g., "16", "32")
    private final String disk;   // Stores disk size (e.g., "512", "1024")

    // Private constructor prevents direct instantiation
    // Forces use of factory method to create instances
    private Computer(String cpu, String ram, String disk) {
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
    }

    // Factory method pattern: provides controlled object creation
    // Parameters:
    //   cpu  - CPU type (i5/i7)
    //   ram  - RAM amount (16/32)
    //   disk - Disk size (512/1024)
    // Returns: new immutable Computer instance
    public static Computer createComputer(String cpu, String ram, String disk) {
        return new Computer(cpu, ram, disk);
    }

    // Getter methods - no setters as class is immutable
    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getDisk() {
        return disk;
    }

    // Provides string representation of computer specifications
    // Format: "CPU:[value]    RAM:[value]    Disk:[value]"
    @Override
    public String toString() {
        return "CPU:" + cpu + "\tRAM:" + ram + "\tDisk:" + disk;
    }
}
