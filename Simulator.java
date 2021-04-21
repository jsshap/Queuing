import java.util.LinkedList;

enum Policy{FCFS, SHORTEST_FIRST, LONGEST_FIRST;}

public class Simulator {

    static LinkedList<Job> queue = new LinkedList<Job>();    
    static int time=0;
    static int timeOfNextEvent=0;
    static Job currentJob;

    static LinkedList<Job> completed = new LinkedList<Job>();

    void startNewSimulator(){
        time=0;
        timeOfNextEvent=0;
        queue= new LinkedList<Job>();
        completed = new LinkedList<Job>();
        currentJob = null;

    }

    void simulate(LinkedList<Job> jobs, Policy p){//figure out how to use enumerated types for this


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

            int nextDepartureTime = currentJob.departureTime;
            int nextArrival = jobs.peekFirst().arrivalTime;


            if (time == nextArrival){
                //Add all jobs in jobs list where arrival time is right now
                while (jobs.peekFirst().arrivalTime==time){
                    processArrival(jobs.removeFirst(), p);
                }
            }
            if (time == nextDepartureTime){
                processJob();
            }

        }
        /*
        take job from jobs, add to queue, select job from queue (based on policy), add jobs to completed list
        then alyze completed list
        */
        /*
        processJob(j)
        remove j from queue and put on completed list

        */

    }

    void processJob(){//j is the new job, the first in the queue

        //kick out current job then start the next one
        currentJob.departureTime=time;
        currentJob.calculateValues();
        completed.add(currentJob);
        
        //j was the first in the queue, now it is current
        currentJob=queue.removeFirst();//this removes j from the queue

    }

    void processArrival(Job j, Policy p){
        //grab from top of jobs list
        //place into Queue according to policy
    }

    void analyze(LinkedList<Job> completed){
        //do stuff

    }

    int findTimeOfNextEvent(LinkedList<Job> jobs){
        //return min of next arrival, time of completion of current job
        return Math.min(jobs.peekFirst().arrivalTime, currentJob.arrivalTime+currentJob.serviceLength);
    }


}
