import java.util.LinkedList;

enum Policy{FCFS, LCFS, SHORTEST_FIRST, LONGEST_FIRST;}

public class Simulator {

    LinkedList<Job> queue;   
    LinkedList<Job> completed;

    int time;
    int timeOfNextEvent;

    Job currentJob;

    public Simulator(){
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
        if (queue.isEmpty()){
            queue.add(j);
            return;
        //This prevents any null pointers from an empty list
        }
        

        if (p == Policy.FCFS){
            queue.addLast(j);
        }
        else if ( p == Policy.SHORTEST_FIRST){
            int i = 0;
            while (j.serviceLength>queue.get(i).serviceLength){
                i++;
            }
            //insert job j into the queue at index i, which should be the first index 
            //where the job's service time is not greater than that of the preceding elelemt
            queue.add(i, j);
        }
        else if ( p == Policy.LONGEST_FIRST){
            //This clause is the same as the one above, but switch the inequality
            int i = 0;
            while (j.serviceLength<queue.get(i).serviceLength){
                i++;
            }
            //insert job j into the queue at index i, which should be the first index 
            //where the job's service time is not less than that of the preceding elelemt
            queue.add(i, j);
        }
        else if ( p == Policy.LCFS){
            queue.addFirst(j);
        }


    }

    void analyze(LinkedList<Job> completed){
        //do stuff

    }

    int findTimeOfNextEvent(LinkedList<Job> jobs){
        //return min of next arrival, time of completion of current job
        if (jobs.isEmpty()){
            return currentJob.arrivalTime+currentJob.serviceLength;
        }
        else if (currentJob == null){
            return jobs.peekFirst().arrivalTime;
        }
        //Only on of those can occur. If neither does, then we won't have any null pointer problems

        return Math.min(jobs.peekFirst().arrivalTime, currentJob.arrivalTime+currentJob.serviceLength);
    }


}
