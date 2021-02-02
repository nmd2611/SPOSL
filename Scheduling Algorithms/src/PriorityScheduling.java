import java.util.*;

public class PriorityScheduling {


    public static Process []p;

    public static void inputProcess(int n){
        int i,priority;
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


    public static void nonPremptive(int n)
    {
        // assuming AT=0
        // sorting processes by priority
        Arrays.sort(p, new Comparator<Process>() {
            public int compare(Process idx1, Process idx2) {
                return idx1.priority - idx2.priority; 
            }
        });

        int i;
        double awt=0.0,att=0.0;
        int currentTime = 0;
        
        System.out.println("ProcessName \t BurstTime \t WaitingTime \t TurnAroundTime \t CompletionTime ");


        for(i=0;i<n;i++)
        {
            p[i].WaitingTime = currentTime;
            
            currentTime += p[i].BurstTime;
            p[i].CompletionTime = currentTime;

            p[i].setTurnAroundTime();

            awt += p[i].WaitingTime;
            att += p[i].TurnAroundTime;

            p[i].display("priority");
        }


        awt /= n;
        att /= n;

        System.out.println("Avg. Waiting Time : " + awt);
        System.out.println("Avg. Turn Around Time : " + att);

    }
    
    public static void main(String args[])throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.print("Enter the number of processes : ");
        n = sc.nextInt();

        p = new Process[n];

        inputProcess(n);

        nonPremptive(n);

        double awt=0.0,att=0.0;
        int currentTime = 0;
        int i;
    }
}
