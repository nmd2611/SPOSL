import java.util.Hashtable;

public class OpcodeTable {
  
    private Hashtable<String,OptabEntry> OPTAB;

    OpcodeTable()
    {
        OPTAB = new Hashtable<String,OptabEntry>();

        OPTAB.put("STOP", new OptabEntry("IS", 0));
        OPTAB.put("ADD", new OptabEntry("IS", 1));
        OPTAB.put("SUB", new OptabEntry("IS", 2));
        OPTAB.put("MULT", new OptabEntry("IS", 3));
        OPTAB.put("MOVER", new OptabEntry("IS", 4));
        OPTAB.put("MOVEM", new OptabEntry("IS", 5));
        OPTAB.put("COMP", new OptabEntry("IS", 6));
        OPTAB.put("BC", new OptabEntry("IS", 7));
        OPTAB.put("DIV", new OptabEntry("IS", 8));
        OPTAB.put("READ", new OptabEntry("IS", 9));
        OPTAB.put("PRINT", new OptabEntry("IS", 10));

        OPTAB.put("START", new OptabEntry("AD", 1));
        OPTAB.put("END", new OptabEntry("AD", 2));
        OPTAB.put("ORIGIN", new OptabEntry("AD", 3));
        OPTAB.put("EQU", new OptabEntry("AD", 4));
        OPTAB.put("LTORG", new OptabEntry("AD", 5));

        OPTAB.put("AREG", new OptabEntry("REG", 1));
        OPTAB.put("BREG", new OptabEntry("REG", 2));
        OPTAB.put("CREG", new OptabEntry("REG", 3));
        OPTAB.put("DREG", new OptabEntry("REG", 4));
        
        OPTAB.put("DS", new OptabEntry("DL", 1));
        OPTAB.put("DC", new OptabEntry("DL", 2));

    }

    public OptabEntry getEntry(String key)
    {
        return OPTAB.get(key);
    }
}
