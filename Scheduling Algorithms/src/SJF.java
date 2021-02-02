import java.util.*;
public class SJF {
    /*
        SJF - Shortest Job First, also known as SRTF - Shortest Remaining Time First.
    */
    
    public static Process []p;

    public static void inputProcess(int n){
        int i;
        for(i=0;i<n;i++)
        {
            System.out.println("Process P" + (i+1) + ":");

            p[i] = new Process();
            p[i].createProcess(i+1, "no priority");
        }

        //sorting processes in ascending order of arrival time
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

        /* 
            currentTime - keeps track of the current time instant.
            cnt - to store the count of processess which have been completed.
            finishTime - to store the time at which a process completes its execution.
            rt[] - stores te remaining burst time.
            minVal - stores the smallest rt
            min - stores the index of the smallest rt
        */
        int cnt = 0,finishTime=0;   
        int rt[] = new int[n];
        int minVal = Integer.MAX_VALUE, min = 0;

        boolean flag = false;

        for(i=0;i<n;i++)
            rt[i] = p[i].BurstTime;


        while(cnt != n)
        {
            for(i=0;i<n;i++)
            {
                if(p[i].ArrivalTime <= currentTime  && rt[i] < minVal && rt[i] > 0)
                {
                    minVal = rt[i];
                    min = i;
                    flag = true;
                }
            }

            if(flag == false)
            {
                currentTime++;
                continue;
            }

            rt[min] --;

            minVal = rt[min];

            // i.e. if the process has completed its execution, reset all the values.
            if(rt[min] == 0)
            {
                minVal = Integer.MAX_VALUE;

                cnt++;
                flag=false;

                finishTime = currentTime + 1;
                p[min].CompletionTime = finishTime;

                p[min].WaitingTime = finishTime - p[min].BurstTime - p[min].ArrivalTime;

                if(p[min].WaitingTime < 0)
                    p[min].WaitingTime = 0;
            }

            currentTime++;
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

        sc.close();
    }
}
