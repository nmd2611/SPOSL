import java.util.*;

public class PriorityScheduling {


    public static Process []p;

    public static void inputProcess(int n){
        Scanner sc = new Scanner(System.in);
        int i;
        for(i=0;i<n;i++)
        {
            System.out.println("Process P" + (i+1) + ":");

            p[i] = new Process();
            p[i].ProcessName = i+1;

            System.out.print("Enter Burst Time : ");
            p[i].BurstTime = sc.nextInt();
            
            System.out.print("Enter priority : ");
            p[i].priority = sc.nextInt();
        }

    }

    public static void premptive(int n)
    {

        Scanner sc = new Scanner(System.in);
        int i;
        for(i=0;i<n;i++)
        {
            System.out.println("Process P" + (i+1) + ":");

            p[i] = new Process();
            p[i].ProcessName = i+1;

            System.out.print("Enter Arrival Time : ");
            p[i].ArrivalTime = sc.nextInt();

            System.out.print("Enter Burst Time : ");
            p[i].BurstTime = sc.nextInt();
            
            System.out.print("Enter priority : ");
            p[i].priority = sc.nextInt();
        }

        // sorting processes by priority and AT
        Arrays.sort(p, new Comparator<Process>() {
            public int compare(Process idx1, Process idx2) {
                if(idx1.ArrivalTime == idx2.ArrivalTime)
					return idx1.priority - idx2.priority;
				return idx1.ArrivalTime - idx2.ArrivalTime;
            }
        });


        double awt=0.0,att=0.0;
        int currentTime = 0;
        int rt[] = new int[n];

        for(i=0;i<n;i++)
            rt[i] = p[i].BurstTime;


        while(true) {
			boolean done = true;
			boolean idle = true;
			
			int idx = -1;
			int minPriority = Integer.MAX_VALUE;
			
			 i = 0;
			while( i < n && currentTime > p[i].ArrivalTime) {
				if(minPriority > p[i].priority && rt[i] != 0) {
					minPriority = p[i].priority;
					idx = i;
					idle = false;
				}
				i++;
			}
			
			if(idle) {
				//timeline.add("IDLE");
			} else {
				rt[idx]--;
				//timeline.add(p[idx].ProcessName);
				if(rt[idx] == 0) {
					p[idx].WaitingTime = currentTime - p[idx].BurstTime - p[idx].ArrivalTime;
                    p[idx].TurnAroundTime = p[idx].BurstTime + p[idx].WaitingTime;
                    
                    p[idx].CompletionTime = currentTime;
                }
                //System.out.print(currentTime + "  P" + p[idx].ProcessName + "  ");
			}
			
			for(i = 0 ; i < n ; i++ ) {
				if(rt[i] != 0) {
					done = false;
				}
			}
			
			if(done) {
				break;
			}
			currentTime++;
        }
        //System.out.println(currentTime);


        System.out.println("ProcessName \t BurstTime \t WaitingTime \t TurnAroundTime \t CompletionTime ");

        // calculating TT from data.
        for (i = 0; i < n; i++) 
        {
            awt += p[i].WaitingTime;
            att += p[i].TurnAroundTime;

            p[i].display("priority");
        }


        awt /= n;
        att /= n;

        System.out.println("Avg. Waiting Time : " + awt);
        System.out.println("Avg. Turn Around Time : " + att);
        
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
        
        
        System.out.println("Gantt Chart : ");
        for(i=0;i<n;i++)
        {
            System.out.print(currentTime + "  P"+ p[i].ProcessName + "  ");
            p[i].WaitingTime = currentTime;
            
            currentTime += p[i].BurstTime;
            p[i].CompletionTime = currentTime;
            
            p[i].setTurnAroundTime();
            
            awt += p[i].WaitingTime;
            att += p[i].TurnAroundTime;
        }
        System.out.println(currentTime + "\n");
        System.out.println("ProcessName \t BurstTime \t WaitingTime \t TurnAroundTime \t CompletionTime ");

        for(i=0;i<n;i++)
            p[i].display("priority");


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
        // premptive(n);
    }
}
