package minimac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static minimac.MiniMacParser.parse;

public interface Instruction {

    public default void execute(MiniMac mac) throws Exception {
        System.out.print("default execute Instruction");
    }
}
class Move implements Instruction {
    int src, dst;
    public String toString(){return "move" + src + " " + dst;}

    public Move(int src, int dst){
        this.src = src;
        this.dst = dst;
    }
    public void execute(MiniMac mac) {
        mac.memory[dst]=mac.memory[src];
        mac.notifySubscribers();
    }
}
class Load implements Instruction {
    int location, value;

    public String toString() {return "load" + location + " " + value;}

    public Load(int location, int value) {
        this.location = location;
        this.value = value;
    }

    public void execute(MiniMac mac) {
        mac.memory[location] = value;
        mac.notifySubscribers();
    }
}
//halt ::= “halt” // terminates the program
class Halt implements Instruction {
    public String toString() {return "halt";}
    public void execute (MiniMac mac) {
        mac.halt = true;
        mac.notifySubscribers();
    }
}
class Loop implements Instruction {
    int count;
    Instruction instruction;
    public String toString() {return "loop" + count + " " + instruction;}

    public Loop(int count, Instruction instruction){
        this.count = count;
        this.instruction = instruction;
    }
    public void execute(MiniMac mac) throws Exception {
        for (int i = 0; i < this.count; i++){
            instruction.execute(mac);
            mac.notifySubscribers();
        }
    }
}
class Add implements Instruction {
    int src1, src2, dst;
    public String toString() {
        return "add " + src1 + " " + src2 + " -> " + dst;
    }
    public Add(int src1, int src2, int dst) {
        this.src1 = src1;
        this.src2 = src2;
        this.dst = dst;
    }

    public void execute(MiniMac mac) {
        System.out.println(dst +  "::: "+ mac.memory[src1]+ "+ " +  mac.memory[src2]);
        mac.memory[dst] = mac.memory[src1] + mac.memory[src2];
        mac.notifySubscribers();
    }
}
class Mul implements Instruction {
    int src1, src2, dst;

    public String toString() {
        return "mul" + src1 + " " + src2 + " " + dst;
    }

    public Mul(int src1, int src2, int dst) {
        this.src1 = src1;
        this.src2 = src2;
        this.dst = dst;
    }

    public void execute(MiniMac mac) {
        mac.memory[dst] = mac.memory[src1] * mac.memory[src2];
        mac.notifySubscribers();
    }
}
class Bgt implements Instruction {
    int address, offset;

    public String toString() {
        return "bgt" + address + " " + offset;
    }

    public Bgt(int address, int offset) {
        this.address = address;
        this.offset = offset;
    }
    public void execute(MiniMac mac) {
        if (0 < mac.memory[address]) {
            mac.ip += offset;
        }
        mac.notifySubscribers();
    }
}
class Blt implements Instruction {
    int address, offset;

    public String toString() {
        return "blt" + address + " " + offset;
    }

    public Blt(int address, int offset) {
        this.address = address;
        this.offset = offset;
    }

    public void execute(MiniMac mac) {
        if (mac.memory[address] < 0) {
            mac.ip += offset;
        }
        mac.notifySubscribers();
    }
}
class Block implements Instruction {
    List<Instruction> instructions = new ArrayList<Instruction>();
    public Block(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void execute(MiniMac mac) throws Exception {
        for(Instruction i : instructions){
            i.execute(mac);
        }
        mac.notifySubscribers();
    }
}
