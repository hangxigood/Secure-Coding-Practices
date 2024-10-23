// Desktop class: Represents a desktop computer
// Uses composition to include Computer functionality plus GPU information
// Class is immutable (final) and all fields are final
public final class Desktop {
    // Composition: contains a Computer instance instead of inheriting from it
    private final Computer computer;  // Core computer specifications
    private final String gpuType;     // Desktop-specific GPU information

    // Private constructor enforces use of factory method
    private Desktop(Computer computer, String gpuType) {
        this.computer = computer;
        this.gpuType = gpuType;
    }

    // Factory method to create Desktop instances
    // Parameters:
    //   cpu     - CPU type (i5/i7)
    //   ram     - RAM amount (16/32)
    //   disk    - Disk size (512/1024)
    //   gpuType - GPU type (Nvidia/AMD)
    // Returns: new immutable Desktop instance
    public static Desktop createDesktop(String cpu, String ram, String disk, String gpuType) {
        return new Desktop(Computer.createComputer(cpu, ram, disk), gpuType);
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

    // Desktop-specific getter
    public String getGpuType() {
        return gpuType;
    }

    // Provides formatted string representation of desktop specifications
    // Format: "Type:Desktop    [computer specs]    GPU:[value]"
    @Override
    public String toString() {
        return "Type:Desktop\t" + computer.toString() + "\tGPU:" + gpuType;
    }
}
