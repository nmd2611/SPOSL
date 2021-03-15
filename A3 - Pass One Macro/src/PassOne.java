import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PassOne {

    ArrayList<MNTEntry> MNT;
    HashMap<String,String> KPDTAB;
    ArrayList<String> MDT;
    ArrayList<String> PNTAB;
    StringBuilder code;

    PassOne()
    {
        MNT = new ArrayList<>();
        KPDTAB = new HashMap<>();
        MDT = new ArrayList<>();
        PNTAB = new ArrayList<>();
        code = new StringBuilder();
    }
    
    public String parseCode(BufferedReader file) throws IOException
    {
        int mdtp = 0;
		int kpdtp = 0;

        String currentLine;
        while((currentLine = file.readLine()) != null)
        {
            String[] words = currentLine.split("\\s+");

            // If MACRO definition
            if(words[0].equals("MACRO"))
            {
                PNTAB.clear();

                String []def = file.readLine().split("\\s+");
                // System.out.println(def[0]);     // gives the name of macro
                // System.out.println(def[1]);     // gives the remaining string
                MNTEntry entry = new MNTEntry(def[0], mdtp, kpdtp);

                String []params = def[1].split(",");    // gives the list of parameters

                int i;
                for(i=0;i<params.length;i++)
                {
                    if(params[i].contains("="))
                    {
                        // Keyword parameter
                        entry.incrementKP();
                        kpdtp++;

                        String []temp = params[i].replace("&", "").split("=");
                        KPDTAB.put(temp[0], temp.length == 2 ? temp[1] : "-");
                        PNTAB.add(temp[0]);
                    }
                    else{
                        // Positional parameter
                        entry.incrementPP();

                        PNTAB.add(params[i].replace("&", ""));
                    }
                }
                MNT.add(entry);
                
                // Now, process the actual content of MACRO
                String line;
                StringBuilder currentRow = new StringBuilder();
                line = file.readLine();
                while(!line.equals("MEND"))
                {
                    String[] temp = line.split("\\s+");

                    currentRow.append(mdtp).append(" ");
                    currentRow.append(temp[0]).append(" ");

                    String[] p = temp[1].split(",");
                    
                    int idx = PNTAB.indexOf(p[0].replace("&", ""));
                    // System.out.println(p[0]);
                    // System.out.println(p[1]);

                    String op;
		            if (idx != -1)
			            op = "(P," + (idx+1) + ")";
		            else
			            op = p[0];
                    
                    currentRow.append(op);

                    if(p.length == 2)
                    {
                        idx = PNTAB.indexOf(p[1].replace("&", ""));
                        //System.out.println(idx);

                        if (idx != -1)
                            op = "(P," + (idx+1) + ")";
                        else
                            op = p[1];
                    
                        currentRow.append(",").append(op);
                    }

                    mdtp++;
                    currentRow.append("\n");
                    line = file.readLine();
                }
                currentRow.append(mdtp).append(" ").append("MEND");
                mdtp++;
                MDT.add(currentRow.toString());

                System.out.println("PNTAB : ");
                System.out.println(getPNTAB());

            }
            else{
                // normal code i.e. add it to intermediate code as it is.
                code.append(currentLine).append("\n");
            }
        }

        return code.toString();
    }

    public String getMNT(){
        StringBuilder mnt = new StringBuilder();

        MNT.forEach((entry) -> {
            mnt.append(entry).append("\n");
        });

        return mnt.toString();
    }

    public String getMDT(){
        StringBuilder mdt = new StringBuilder();

        MDT.forEach((entry) -> {
            mdt.append(entry).append("\n");
        });

        return mdt.toString();
    }

    public String getPNTAB(){
        StringBuilder pntab = new StringBuilder();

        PNTAB.forEach((entry) -> {
            pntab.append(entry).append("\n");
        });

        return pntab.toString();
    }


    public String getKPDTAB(){
        StringBuilder kpdtab = new StringBuilder();

        KPDTAB.forEach((key,value) -> {
            kpdtab.append(key).append(" ").append(value).append("\n");
        });

        return kpdtab.toString();
    }
}
