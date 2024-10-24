//Desktop computer: adds GPU type
// 3. Desktop Class Redesign(Shelyn)
// Redesign the Desktop class to use composition instead of inheritance and make it immutable.
// Remove inheritance from Computer and add a private final Computer field.
// Implement a constructor and getter methods.

public final class Desktop { //Hangxi: change it to final for immutability
    private final Computer computer; //Composition
    private final String GPUType; // Hangxi: change it to final for immutability

    //Constructors
    // public Desktop() {

    // } //No-arg constructor

    public Desktop(String CPU, String RAM, String disk, String GPUType) {
        //Inherited from Computer superclass
        // this.CPU=CPU;
        // this.RAM=RAM;
        // this.disk=disk;
        this.computer=new Computer(CPU, RAM, disk);

        //Only in Desktop subclass
        this.GPUType=GPUType;
    }

    //Setter
    // public void setGPUType(String GPUType) {
    //     this.GPUType=GPUType;
    // }

    public Computer getComputer() {
        return this.computer;
    }

    //Getter
    public String getGPUType() {
        return this.GPUType;
    }

    @Override
    public String toString() {
        //return "Type:Desktop\tCPU:" + this.CPU + "\tRAM:" + this.RAM + "\tDisk:" + this.disk + "\tGPU:" + this.GPUType;
        // return "Type:Desktop\tCPU:" + this.computer.getCPU() + "\tRAM:" + this.computer.getRAM() + "\tDisk:" + this.computer.getDisk() + "\tGPU:" + this.GPUType;
        return "Type:Desktop\t" + this.computer.toString() + "\tGPU:" + this.GPUType; //Hangxi: use the toString method of the Computer class
    }
}
