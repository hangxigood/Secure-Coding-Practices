//Desktop computer: adds GPU type
// 3. Desktop Class Redesign(Shelyn)
// Redesign the Desktop class to use composition instead of inheritance and make it immutable.
// Remove inheritance from Computer and add a private final Computer field.
// Implement a constructor and getter methods.

public class Desktop { //Inherits from Computer
    private final Computer computer; //Composition
    String GPUType=null;

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

    //Return formatted version of data
    public String toString() {
        //return "Type:Desktop\tCPU:" + this.CPU + "\tRAM:" + this.RAM + "\tDisk:" + this.disk + "\tGPU:" + this.GPUType;
        return "Type:Desktop\tCPU:" + this.computer.getCPU() + "\tRAM:" + this.computer.getRAM() + "\tDisk:" + this.computer.getDisk() + "\tGPU:" + this.GPUType;
    }

}