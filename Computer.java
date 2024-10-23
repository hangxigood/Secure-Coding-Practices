//Computer class: manages computer CPU, RAM and Disk information

public final class Computer { //Hangxi: change it to final for immutability
    private final String CPU;
    private final String RAM;
    private final String disk;

    //Hangxi: deleted no-argument constructor for immutability
    // public Computer() {} //No-arg contructor

    public Computer(String CPU, String RAM, String disk) {
        this.CPU=CPU;
        this.RAM=RAM;
        this.disk=disk;
    }

    //Setters
    // public void setCPU(String CPU) {
    //     this.CPU=CPU;
    // }

    // public void setRAM(String RAM) {
    //     this.RAM=RAM;
    // }

    // public void setDisk(String disk) {
    //     this.disk=disk;
    // }

    //Getters
    public String getCPU() {
        return this.CPU;
    }

    public String getRAM() {
        return this.RAM;
    }

    public String getDisk() {
        return this.disk;
    }

    @Override //Hangxi: Override the default toString method
    public String toString() {
        return "CPU: " + CPU + ", RAM: " + RAM + ", Disk: " + disk;
    }
}
