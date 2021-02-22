import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {

        FileReader file ;
        BufferedReader br;
        BufferedWriter bw;
        String line;
        PassOne assembler = new PassOne();

        try{
            file = new FileReader("/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A1 - Pass One Assembler/src/input/ip.asm");
            br = new BufferedReader(file);

            System.out.println("File opened successfully");

            
            assembler.parseCode(br);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
