import java.util.LinkedList;

enum Policy {FCFS, LCFS, SHORTEST_FIRST, LONGEST_FIRST, RANDOM}


public class Server {
    
    int time;
    int numArrivals;

    int nextArrivalTime;
    int nextDepartureTime;
    LinkedList<Job> queue;
    LinkedList<Job> completed;

    double pArrival, qJobSize;

    Policy policy;

    Job currentJob;

    int[] jobsInSystem;

    int offset = 2000;
    //number of jobs at the beginning we ignore for data collection

    public Server(double p, double q, Policy pol){
        this.pArrival=p;
        this.qJobSize=q;
        this.policy = pol;

        time =0;

        numArrivals=0;
        
        nextArrivalTime=0;
        nextDepartureTime = Integer.MAX_VALUE;
        queue = new LinkedList<Job>();
        completed = new LinkedList<Job>();
    }

    void simulate(int numJobs){

        jobsInSystem = new int[numJobs];

        while (numArrivals < numJobs){
            //System.out.println(numArrivals);
            if (numArrivals > offset){
                jobsInSystem[numArrivals] = queue.size()+ (currentJob == null ? 0 : 1);
                //total jobs is the size of the queue plus possible the current
            }

            int nextEvent = Math.min(nextArrivalTime, nextDepartureTime);
            if (nextEvent== nextDepartureTime){
                processDeparture();
            }
            if (nextEvent == nextArrivalTime){
                numArrivals++;
                processArrival();
            }

        }
    }

    void processArrival(){
        time = nextArrivalTime;
        Job newJob = new Job(time, qJobSize);
        addToQueue(newJob);
        //queue.addLast(newJob);
        if (currentJob==null){//That is the only job in the queue
            currentJob=queue.removeFirst();
            currentJob.timeSpentInQueue=0;
            currentJob.departureTime= time + currentJob.serviceTime;
            nextDepartureTime = currentJob.departureTime;
        }
        nextArrivalTime = time + Generation.generateGeometric(pArrival);
    }

    void addToQueue(Job j){
        if (queue.size()==0){
            queue.add(j);
        }

        //add to queue based on policy. Remember that we always remove from first
        if (this.policy == Policy.FCFS){
            queue.addLast(j);
            //first come first serve, add to end of line
        }
        else if ( this.policy == Policy.SHORTEST_FIRST){
            //https://stackoverflow.com/a/19067509
            int i = 0;
            while (j.serviceTime>queue.get(i).serviceTime){
                i++;
                if (i == queue.size()){
                    i--;
                    break;
                }
            }
            //insert job j into the queue at index i, which should be the first index 
            //where the job's service time is not greater than that of the preceding elelemt
            queue.add(i, j);
        }
        else if ( this.policy == Policy.LONGEST_FIRST){
            //This clause is the same as the one above, but switch the inequality
            int i = 0;
            while (j.serviceTime<queue.get(i).serviceTime){
                i++;
                if (i == queue.size()){
                    i--;
                    break;
                }
            }
            //insert job j into the queue at index i, which should be the first index 
            //where the job's service time is not less than that of the preceding elelemt
            queue.add(i, j);
        }
        else if ( this.policy == Policy.LCFS){
            //last come first serve
            queue.addFirst(j);
        }
        else if (this.policy== Policy.RANDOM){
            queue.add((int)Math.random()*queue.size(), j);

        }

    }

    void processDeparture(){
        time = nextDepartureTime;
        if (numArrivals > offset){
            completed.add(currentJob);
        }//only add to completed if we passed the offset
        //Guarantees that completed.size() is the number of departyres after we hit the arrival offset
        currentJob.departureTime=time;
        currentJob.totalTime = currentJob.departureTime - currentJob.arrivalTime;


        if (! queue.isEmpty()){
            currentJob = queue.removeFirst();
            currentJob.timeSpentInQueue = time-currentJob.arrivalTime;
            nextDepartureTime = time + currentJob.serviceTime;
        }
        else{//the queue is empty
            currentJob=null;
            nextDepartureTime = Integer.MAX_VALUE;
        }
    }

    void processData(){
        int totalInSystem = 0;
        for (int a: jobsInSystem){
            totalInSystem += a;
        }
        double E_N = totalInSystem/(double)(numArrivals-offset);
        
        int totalResponseTime=0;

        for (Job j : completed){
            totalResponseTime += j.totalTime; 
        }   

        double E_T = totalResponseTime / (double)(completed.size());
        System.out.println(pArrival +"," + E_N + "," +E_T);
    }

    void computeVariance() {
        for (Job j : completed){
            System.out.println(j.totalTime);
        }
    }
}
