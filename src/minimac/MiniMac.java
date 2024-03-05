package minimac;

import tools.Publisher;

import java.io.Serializable;
import java.util.List;

public class MiniMac extends Publisher implements Serializable {
    public List<Instruction> program;
    static int ip = 0;
    static boolean halt = false;
    static int size = 10;
    static int[] memory = new int[size];

    public void execute() throws Exception {
        while (!halt && ip < program.size()) {
            try {
                program.get(ip).execute(this);
                System.out.println(program.get(ip).toString());
                ip++;
                System.out.println("\nMemory");
                for (int num = 0; num < 10; num++) {
                    System.out.println(num+": "+memory[num]);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        notifySubscribers();
    }

    public void clear() {
        ip = 0;
        halt = false;
        //clear list and notify
        for (int i = 0; i < size; i++) {
            memory[i] = 0;
        }

        System.out.println("\nMemory");
        for (int num = 0; num < 5; num++) {
            System.out.println(memory[num]);
        }
        notifySubscribers();
    }
}
 /*   public static void main(String[] args) throws Exception {
        MiniMac mac = new MiniMac();

        String instruction = "{ load 2 2;load 1 3;add 1 2 3 } halt load 1 1";

        while (halt == false && ip < parse(instruction).size()) {
            parse(instruction).get(ip).execute(mac);
            ip++;
        }
        System.out.println("\nMemory");
        for(int num = 0; num < 5;num++){
            System.out.println(memory[num]);
        }
    }
}
*/