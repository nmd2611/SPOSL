import java.io.*;
import java.util.*;


public class PassTwo {

    public ArrayList<OperandEntry> LITTAB;
    public ArrayList<OperandEntry> SYMTAB;

    public StringBuilder code;   // stores the final m/c 

    PassTwo()
    {
        LITTAB = new ArrayList<>();
        SYMTAB = new ArrayList<>();

        code = new StringBuilder();
    }

    public void setLittab(BufferedReader br) throws IOException
    {
        String line ;
        while((line = br.readLine()) != null)
        {
            // System.out.println(line);
            String[] words = line.split("\\s+");    // this is used to split a sentence into an array of space separated words
            // System.out.println(words[0]);
            // System.out.println(words[1]);
            // System.out.println(words[2]);

            OperandEntry tp = new OperandEntry(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[0]));
            LITTAB.add(tp);
        }
    }

    public void setSymtab(BufferedReader br) throws IOException
    {
        String line ;
        while((line = br.readLine()) != null)
        {
            // System.out.println(line);
            String[] words = line.split("\\s+");    // this is used to split a sentence into an array of space separated words
            // System.out.println(words[0]);
            // System.out.println(words[1]);
            // System.out.println(words[2]);

            OperandEntry tp = new OperandEntry(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[0]));
            SYMTAB.add(tp);
        }
    }
    

    public String parseCode(BufferedReader file) throws IOException
    {
        String currentLine;
        while((currentLine = file.readLine()) != null)
        {
            String[] words = currentLine.split("\\s+");

            // System.out.println(words[0]);
            code.append(words[0] + "  ");

            if(words[1].contains("AD"))
            { 
                code.append("\n");
                continue;    
            }

            if(words[1].contains("IS"))
            {
                code.append("+").append(words[1].substring(4,6)).append("  ");

                switch(words.length)
                {
                    case 2 :
                            code.append("00  0").append("\n");
                            break;
                    case 3:
                            code.append("00").append("  ");
                            break;
                    case 4: 
                            if(words[2].contains("CC"))
                            {
                                code.append(words[2].substring(4,6)).append("  ");
                            }
                            else
                            {
                                code.append(words[2].charAt(1)).append("  ");
                            }
                            
                            char type = words[3].charAt(1);
                            int idx = words[3].charAt(3) - '0';

                            if(type == 'L')
                            {
                                code.append((LITTAB.get(idx)).getAddress()).append("\n");
                            }
                            else if(type == 'S'){
                                code.append((SYMTAB.get(idx)).getAddress()).append("\n");
                            }

                            break;
                }
            }

            if(words[1].contains("DL"))
            {
                code.append("00" + "  " + "0").append("  ");

                int value = Integer.parseInt(words[2].replaceAll("[^0-9]", ""));

                if(words[2].contains("C"))
                    code.append(value).append("\n");

            }
            

        }

        return code.toString();
    }
}
