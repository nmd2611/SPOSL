import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        FileReader file ;
        BufferedReader br;
        BufferedWriter bw;
        FileWriter fw;
        PassTwo assembler = new PassTwo();

        String path = "/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A2 - Pass Two Assembler/src/";
   
        try {

            System.out.println("File opened successfully");

            br = new BufferedReader(new FileReader(path + "LITTAB.txt"));
            assembler.setLittab(br);

            br = new BufferedReader(new FileReader(path + "SYMTAB.txt"));
            assembler.setSymtab(br);

            file = new FileReader(path + "IC-input.txt");
            br = new BufferedReader(file);

            String MC = assembler.parseCode(br);
            System.out.println("Machine Code : ");
            System.err.println(MC);

            br.close();
            file.close();

            fw = new FileWriter(path + "output.txt");
            bw = new BufferedWriter(fw);

            bw.write(MC);
            bw.close();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }
}
