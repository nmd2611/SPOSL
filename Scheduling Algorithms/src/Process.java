import java.util.*;

public class Process {
	public int ProcessName;
	public int BurstTime;
	public int ArrivalTime;
	public int WaitingTime;
	public int TurnAroundTime;
	public static Scanner sc = new Scanner(System.in);
	
	public void createProcess(int i) {
		//System.out.print("Process Name : ");
		ProcessName = i;

		System.out.print("Enter Arrival Time : ");
		ArrivalTime = sc.nextInt();

		System.out.print("Enter Burst Time : ");
		BurstTime = sc.nextInt();
	}
	
	public void setTurnAroundTime() {
		TurnAroundTime = BurstTime + WaitingTime;
	}

	public void display(int CompletionTime) {
		System.out.println("     P" + ProcessName + "\t\t\t" + ArrivalTime + "\t\t" + BurstTime +"\t\t" + WaitingTime + "\t\t" + TurnAroundTime + "\t \t \t" + CompletionTime);

	}
}
