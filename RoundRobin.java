package roundrobin;
import java.util.Random;


public class RoundRobin {
    public static void main(String[] args) {

    int BURST_TIME[] = new int[new Random().nextInt(100)+10];
    int ARRIVAL_TIME[] = new int[BURST_TIME.length];
    int QUANTUM_TIME=new Random().nextInt(40)+1;
    for(int i=0;i<BURST_TIME.length;i++){
        BURST_TIME[i] = new Random().nextInt(30)+5;
    }
    for(int i=1;i<ARRIVAL_TIME.length;i++){
        ARRIVAL_TIME[i]= ARRIVAL_TIME[i-1]+new Random().nextInt(10)+1;
    }
//    findavgTime(BURST_TIME,QUANTUM_TIME,ARRIVAL_TIME);
findavgTime(new int[]{9,2,5,6},3,new int[]{0,1,2,3});
    }
    static void findWaitingTime(int burst_Time[], int waiting_Time[], int QUANTUM_TIME) { 
        int rem_bt[] = new int[burst_Time.length]; 
        for (int i = 0 ; i < burst_Time.length ; i++) 
            rem_bt[i] =  burst_Time[i]; 
        int t = 0;
        while(true){ 
            boolean done = true; 
            for (int i = 0 ; i < burst_Time.length; i++) { 
                if (rem_bt[i] > 0) { 
                    done = false;
                    if (rem_bt[i] > QUANTUM_TIME) { 
                        t += QUANTUM_TIME; 
                        rem_bt[i] -= QUANTUM_TIME; 
                    } 
                    else{ 
                        t = t + rem_bt[i]; 
                        waiting_Time[i] = t - burst_Time[i]; 
                        rem_bt[i] = 0; 
                    } 
                } 
            }        
            if (done == true) 
              break; 
        } 
    }        
    static void findTurnAroundTime(int burst_Time[], int waiting_Time[], int TOTAL_AROUND_TIME[]){ 
        for (int i = 0; i < burst_Time.length ; i++) 
            TOTAL_AROUND_TIME[i] = burst_Time[i] + waiting_Time[i]; 
    } 
    static void findavgTime(int burst_Time[], int quantum,int arrival_Time[]) { 
        int wt[] = new int[burst_Time.length], tat[] = new int[burst_Time.length]; 
        int total_wt = 0, total_tat = 0;        
        findWaitingTime( burst_Time, wt, quantum); 
   findTurnAroundTime(burst_Time, wt, tat);  
        System.out.println("Processes " + " Burst time " + 
                      " Waiting time " + " Turn around time");  
        for (int i=0; i<burst_Time.length; i++) { 
            total_wt = total_wt + wt[i]-arrival_Time[i]; 
            total_tat = total_tat + tat[i]; 
            System.out.println(" " + (i+1) + "\t\t" + burst_Time[i] +"\t " + (wt[i]-arrival_Time[i]) +"\t\t " + tat[i]); 
        }
        System.out.println("Average waiting time = " + 
                          (float)total_wt / (float)burst_Time.length); 
        System.out.println("Average turn around time = " + 
                           (float)total_tat / (float)burst_Time.length); 
    } 
}