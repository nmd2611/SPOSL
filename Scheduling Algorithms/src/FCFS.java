import java.util.*;

public class FCFS {
    /*
    FCFS: First Come First Serve (FIFO)
    It simply queues processes in the order that they arrive in the ready queue.

    Arrival Time(AT) - The time at which the process arrives in the Ready Queue.
    Burst Time - The time required to complete the process.
    Turnaround Time - WT + Burst Time
    Completion Time: Time instant at which process completes its execution.

    Average WT = Avg of all WT's
    Average TT = Avg of all TT's

    Given -> Processes, their AT and burst time
    To find -> WT, AWT , TT, ATT

    What is Convoy Effect?
    -> Convoy Effect is a situation where many processes, who need to use a resource for short time 
    are blocked by one process holding that resource for a long time.

    This essentially leads to poort utilization of resources and hence poor performance.
    */
    public static Process []p;

    public static void inputProcess(int n){
        int i;
        for(i=0;i<n;i++)
        {
            System.out.println("Process P" + (i+1) + ":");

            p[i] = new Process();
            p[i].createProcess(i+1);
        }

        // sorting processes in ascending order of arrival time
        Arrays.sort(p, new Comparator<Process>() {
            public int compare(Process idx1, Process idx2) {
                return idx1.ArrivalTime - idx2.ArrivalTime; 
            }
        });
    }
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n;

        System.out.print("Enter the number of processes : ");
        n = sc.nextInt();

        p = new Process[n];

        inputProcess(n);
        
        double awt=0.0,att=0.0;
        int currentTime = 0;
        int i;

        System.out.println("ProcessName \t ArrivalTime \t BurstTime \t WaitingTime \t TurnAroundTime \t CompletionTime ");
        
        for(i=0;i<n;i++)
        {
            if(currentTime != 0)
                p[i].WaitingTime = Math.max(0, currentTime - p[i].ArrivalTime);
        
            else{
                p[i].WaitingTime = currentTime;
				currentTime = p[i].ArrivalTime;

            }
            currentTime += p[i].BurstTime;
            
            p[i].TurnAroundTime = p[i].WaitingTime + p[i].BurstTime;

            awt += p[i].WaitingTime;
            att += p[i].TurnAroundTime;

            p[i].display(currentTime);
        }

        awt /= n;
        att /= n;

        System.out.println("Avg. Waiting Time : " + awt);
        System.out.println("Avg. Turn Around Time : " + att);

        sc.close();
    }
    
}
