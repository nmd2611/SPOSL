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
            file = new FileReader("/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A1 - Pass One Assembler/src/input/ip2.asm");
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

            String SYMTAB = assembler.getSymbolTable();
			System.out.println("Symbol Table:");
			System.out.println(SYMTAB);
			fw = new FileWriter("/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A1 - Pass One Assembler/src/SYMTAB.txt");
			bw = new BufferedWriter(fw);
			bw.write(SYMTAB);
			bw.close();


            String LITTAB = assembler.getLiteralTable();
			System.out.println("Literal Table:");
			System.out.println(LITTAB);
			fw = new FileWriter("/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A1 - Pass One Assembler/src/LITTAB.txt");
			bw = new BufferedWriter(fw);
			bw.write(LITTAB);
			bw.close();


            String POOLTAB = assembler.getPoolTable();
			System.out.println("POOLTAB Table:");
			System.out.println(POOLTAB);
            POOLTAB = POOLTAB.replace("#", "");
			fw = new FileWriter("/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A1 - Pass One Assembler/src/POOLTAB.txt");
			bw = new BufferedWriter(fw);
			bw.write(POOLTAB);
			bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
