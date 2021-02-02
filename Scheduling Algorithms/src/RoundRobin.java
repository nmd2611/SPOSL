import java.util.*;

public class RoundRobin {
    
    public static Process []p;

    public static void inputProcess(int n){
        int i;
        for(i=0;i<n;i++)
        {
            System.out.println("Process P" + (i+1) + ":");

            p[i] = new Process();
            p[i].createProcess(i+1, "priority");
        }

        //sorting processes in ascending order of arrival time
        Arrays.sort(p, new Comparator<Process>() {
            public int compare(Process idx1, Process idx2) {
                return idx1.ArrivalTime - idx2.ArrivalTime; 
            }
        });
    }
    
    public static void main(String args[])throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.print("Enter the number of processes : ");
        n = sc.nextInt();

        p = new Process[n];

        inputProcess(n);

        double awt=0.0,att=0.0;
        int currentTime = 0;
        int i;
    }
}
