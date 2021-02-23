import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) throws Exception {

        FileReader file ;
        BufferedReader br;
        BufferedWriter bw;
        String line;
        FileWriter fw;
        PassOne assembler = new PassOne();

        try{
            file = new FileReader("/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A1 - Pass One Assembler/src/input/ip.asm");
            br = new BufferedReader(file);

            System.out.println("File opened successfully");

            
            String IC = assembler.parseCode(br);
            System.out.println("Intermediate Code : ");
            System.err.println(IC);

            file.close();
            br.close();

            fw = new FileWriter("/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A1 - Pass One Assembler/src/IC-output.txt");
            bw = new BufferedWriter(fw);

            bw.write(IC);
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
