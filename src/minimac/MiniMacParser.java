package minimac;

import java.util.*;

import minimac.Instruction.*;

class ParserUtils {
    protected static Integer parseInteger(Iterator<String> it) throws Exception {
        if (it.hasNext()) {
            return Integer.parseInt(it.next());
        } else {
            throw new Exception("Parsing error, integer expected");
        }
    }
}

public class MiniMacParser extends ParserUtils {

    static public List<Instruction> parse(String program) throws Exception {
        List<Instruction> result = new ArrayList<Instruction>();
        // split program into a list of tokens (i.e. numbers and operations):
        List<String> tokens = Arrays.asList(program.split("\\s*;\\s*|\\s+"));
        Iterator<String> it = tokens.iterator();
        while(it.hasNext()) {
            result.add(parseNext(it));
        }
        return result;
    }

    // dispatcher method:
    static public Instruction parseNext(Iterator<String> it) throws Exception {
        String opcode = it.next();
        if (opcode.equalsIgnoreCase("halt")) return new Halt();
        if(opcode.equalsIgnoreCase("move")) return parseMove(it);
        if (opcode.equalsIgnoreCase("add")) return parseAdd(it);
        if (opcode.equalsIgnoreCase("mul")) return parseMul(it);
        if (opcode.equalsIgnoreCase("load")) return parseLoad(it);
        if (opcode.equalsIgnoreCase("loop")) return parseLoop(it);
        if (opcode.equalsIgnoreCase("Bgt")) return parseBgt(it);
        if (opcode.equalsIgnoreCase("Blt")) return parseBlt(it);
        if (opcode.equalsIgnoreCase("{")) return parseBlock(it);
        if (opcode.equalsIgnoreCase("}")) return null; // used to indicate the end of a block
        throw new Exception("Parsing error, unrecognized operator: " + opcode);
    }

    static protected Move parseMove(Iterator<String> it) throws Exception {
        int src1 = parseInteger(it);
        int src2 = parseInteger(it);
        return new Move(src1, src2);
    }
    static protected Add parseAdd(Iterator<String> it) throws Exception {
        int src1 = parseInteger(it);
        int src2 = parseInteger(it);
        int dest = parseInteger(it);
        return new Add(src1, src2, dest);
    }
    static protected Mul parseMul(Iterator<String> it) throws Exception {
        int src1 = parseInteger(it);
        int src2 = parseInteger(it);
        int dest = parseInteger(it);
        return new Mul(src1, src2, dest);
    }
    static protected Load parseLoad(Iterator<String> it) throws Exception {
        int loc = parseInteger(it);
        int value = parseInteger(it);
        return new Load(loc, value);
    }
    static protected Loop parseLoop(Iterator<String> it) throws Exception {
        int count = parseInteger(it);
        Instruction instruction = parseNext(it);
        return new Loop(count, instruction);
    }
    static protected Bgt parseBgt(Iterator<String> it) throws Exception {
        int loc = parseInteger(it);
        int offset = parseInteger(it);
        return new Bgt(loc, offset);
    }
    static protected Blt parseBlt(Iterator<String> it) throws Exception {
        int address = parseInteger(it);
        int offset = parseInteger(it);
        return new Blt(address, offset);
    }
    static protected Block parseBlock(Iterator<String> it) throws Exception {
        //block ::= “{“ ~ instruction ~ (“;” ~ instruction)* ~ “}”
        List<Instruction> instructions=new ArrayList<Instruction>();
        Instruction temp = parseNext(it);
        while (temp != null){
            instructions.add(temp);
            temp = parseNext(it);
        }
        return new Block(instructions);
    }

}