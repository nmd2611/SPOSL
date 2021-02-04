import java.util.*;

public class RoundRobin {
    
    public static Process []p;

    public static void inputProcess(int n){
        Scanner sc = new Scanner(System.in);
        int i;
        for(i=0;i<n;i++)
        {
            System.out.println("Process P" + (i+1) + ":");

            p[i] = new Process();

            p[i].ProcessName = i;
            
            System.out.print("Enter Arrival Time : ");
		    p[i].ArrivalTime = sc.nextInt();

		    System.out.print("Enter Burst Time : ");
            p[i].BurstTime = sc.nextInt();
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
        int n, q;

        System.out.print("Enter the number of processes : ");
        n = sc.nextInt();

        p = new Process[n];

        inputProcess(n);

        System.out.print("Enter the time quantum : ");
        q = sc.nextInt();

        double awt=0.0,att=0.0;
        int currentTime = 0;
        int i;

        int rt[] = new int[n];
        for(i=0;i<n;i++)
            rt[i] = p[i].BurstTime;


        boolean flag = false;

        while(!flag)
        {
            flag =true;
            for(i=0;i<n;i++)
            {
                if(rt[i] > 0 && p[i].ArrivalTime <= currentTime)
                {
                    flag = false;
                    if(rt[i] > q)
                    {
                        currentTime += q;
                        rt[i] -= q;
                    }
                    else {
                        currentTime += rt[i];
                        p[i].WaitingTime = currentTime - p[i].BurstTime - p[i].ArrivalTime;
                        rt[i] = 0;
                        p[i].CompletionTime = currentTime;   
                    }
                } 
                // else
                //     currentTime++;
            }
        }

        System.out.println("ProcessName \t ArrivalTime \t BurstTime \t WaitingTime \t TurnAroundTime \t CompletionTime ");

        // calculating TT from data.
        for (i = 0; i < n; i++) 
        {
            p[i].setTurnAroundTime();

            awt += p[i].WaitingTime;
            att += p[i].TurnAroundTime;

            p[i].display("not priority");
        }

        awt /= n;
        att /= n;

        System.out.println("Avg. Waiting Time : " + awt);
        System.out.println("Avg. Turn Around Time : " + att);

    }
}
