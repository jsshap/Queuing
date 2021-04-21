import java.util.LinkedList;

enum Policy{FCFS, LCFS, SHORTEST_FIRST, LONGEST_FIRST;}

public class Simulator {

    Policy policy;

    LinkedList<Job> queue;   
    LinkedList<Job> completed;

    int time;
    int timeOfNextEvent;

    Job currentJob;

    public Simulator(Policy p){
        time=0;
        timeOfNextEvent=0;
        queue= new LinkedList<Job>();
        completed = new LinkedList<Job>();
        currentJob = null;

        this.policy=p;

    }

    void simulate(LinkedList<Job> jobs){//figure out how to use enumerated types for this


        /*
        while !(jobs is empty and queue is empty){
            update next event time
            proceed to next event
            either process arrival or process departure
        }
        */
        while (!(jobs.isEmpty() && queue.isEmpty())){
            //while either jobs or queue has elements in it
            timeOfNextEvent= findTimeOfNextEvent(jobs);
            time = timeOfNextEvent;
            
            int nextDepartureTime = -1;
            int nextArrival = -1;

            if (currentJob != null){
                nextDepartureTime = currentJob.departureTime;
            }
            if (!jobs.isEmpty()){
                nextArrival = jobs.peekFirst().arrivalTime;
            }

            if (time == nextArrival){
                //Add all jobs in jobs list where arrival time is right now
                while (jobs.peekFirst().arrivalTime==time){
                    processArrival(jobs.removeFirst());
                }
            }
            if (time == nextDepartureTime){
                processJob();
            }
        }
    }

    void processJob(){
        //kick out current job then start the next one
        currentJob.departureTime=time;
        currentJob.calculateValues();
        completed.add(currentJob);
        
        currentJob=queue.removeFirst();
        //set current job to be first from the q and then remove it
    }

    void processArrival(Job j){

        if (queue.isEmpty()){
            queue.add(j);
            return;
        //This prevents any null pointers from an empty list
        }
        
        //If there already is a queue, then add according to the policy of this instance of Simulator
        //Process always takes from the fron of the queue
        if (this.policy == Policy.FCFS){
            queue.addLast(j);
            //first come first serve, add to end of line
        }
        else if ( this.policy == Policy.SHORTEST_FIRST){
            int i = 0;
            while (j.serviceLength>queue.get(i).serviceLength){
                i++;
            }
            //insert job j into the queue at index i, which should be the first index 
            //where the job's service time is not greater than that of the preceding elelemt
            queue.add(i, j);
        }
        else if ( this.policy == Policy.LONGEST_FIRST){
            //This clause is the same as the one above, but switch the inequality
            int i = 0;
            while (j.serviceLength<queue.get(i).serviceLength){
                i++;
            }
            //insert job j into the queue at index i, which should be the first index 
            //where the job's service time is not less than that of the preceding elelemt
            queue.add(i, j);
        }
        else if ( this.policy == Policy.LCFS){
            //last come first serve
            queue.addFirst(j);
        }
    }

    void analyze(LinkedList<Job> completed){
        //do stuff

    }

    void exportDataToCSV(LinkedList<Job> completed){
        System.out.println("Arrival Time,Departure Time,Service Time,Time In Queue,Total Time");
        for (Job j : completed){
            System.out.println(j.arrivalTime + "," 
            + j.departureTime + "," 
            + j.serviceLength + "," 
            + j.timeInQ + "," + j.totalTime);
        }
    }

    int findTimeOfNextEvent(LinkedList<Job> jobs){
        //return min of next arrival, time of completion of current job
        if (jobs.isEmpty()){
            return currentJob.arrivalTime+currentJob.serviceLength;
        }
        else if (currentJob == null){
            return jobs.peekFirst().arrivalTime;
        }
        //Only on of those cases can occur. If neither does, then we won't have any null pointer problems

        return Math.min(jobs.peekFirst().arrivalTime, currentJob.arrivalTime+currentJob.serviceLength);
    }
}
