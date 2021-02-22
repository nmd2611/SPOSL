import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class PassOne {
   public OpcodeTable OPTAB;
   public ArrayList<OperandEntry> LITTAB;
   public HashMap<String, OperandEntry> SYMTAB;
   public ArrayList<Integer> POOLTAB;
   public String currentLine;
   public StringBuilder code;   // stores the final o/p code

    int LC = 0;
	int pooltab_ptr = 0;
	int littab_ptr = 0;
	int symtab_ptr = 0;
   
    PassOne()
    {
        SYMTAB = new HashMap<>();
        POOLTAB = new ArrayList<>();
        LITTAB = new ArrayList<>();
        OPTAB = new OpcodeTable();
        code = new StringBuilder();
   }

   public void parseCode(BufferedReader br) throws IOException {
    POOLTAB.add(pooltab_ptr);
    while((currentLine = br.readLine() ) != null)
    {
        String[] words = currentLine.split("\\s+");    // this is used to split a sentence into an array of space separated words
        
        // if a label is present at the start of the statement
        if(!words[0].isEmpty())
        {

        }
        else{

            // If LTORG statement
             if(words[1].equals("LTORG") || words[1].equals("END"))
            {

            }
            // If START or ORIGIN statement
            else if(words[1].equals("START") || words[1].equals("ORIGIN"))
            {

            }
            // If EQU statement
            else if(words[1].equals("EQU"))
            {

            }
            // If DL statement
            else if(OPTAB.getEntry(words[1]).isDeclarative())
            {

            }
            // If Imperative statement
            else if(OPTAB.getEntry(words[1]).isImperative())
            {
                if(words[1].equals("STOP"))
                {
                    code.append(LC + "  ");
                    code.append(OPTAB.getEntry(words[1])).append("\n");
                    LC++;
                }
                else{
                    String operands[] = words[1].split(",");
                    //String val = "(IS," + OPTAB.getEntry(words[1]).getOpcode() + ")"; 
                    code.append(LC + "  " + OPTAB.getEntry(words[1])).append("  ");
                }
            }
        }

        System.out.println("0 - " + words[0]);
        System.out.println("1 - " + words[1]);
        System.out.println("2 - " + words[2]);
    }
    
   }
}
