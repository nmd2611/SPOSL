import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) throws Exception {

        BufferedReader br;
        BufferedWriter bw;
        FileReader file;
        FileWriter fw;

        PassOne macro = new PassOne();
        
        
        String path = "/media/kalilinux/D8922BCB922BAD461/TE practicals/++SEM-II/SPOSL/A3 - Pass One Macro/src/";

        try {
            br = new BufferedReader(new FileReader(path + "input.asm"));
            String ic = macro.parseCode(br);
            fw = new FileWriter(path + "IC-output.txt");
            fw.write(ic);
            fw.close();
            br.close();

           
            String mnt = macro.getMNT();
            fw = new FileWriter(path + "MNT.txt");
            fw.write(mnt);
            fw.close();


            String mdt = macro.getMDT();
            fw = new FileWriter(path + "MDT.txt");
            fw.write(mdt);
            fw.close();


            String pntab = macro.getPNTAB();
            fw = new FileWriter(path + "PNTAB.txt");
            fw.write(pntab);
            fw.close();
           

            String kpdtab = macro.getKPDTAB();
            fw = new FileWriter(path + "KPDTAB.txt");
            fw.write(kpdtab);
            fw.close();







        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
