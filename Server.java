import java.util.LinkedList;

import java.io.IOException;
import java.io.FileWriter;

enum Policy{FCFS, LCFS, SHORTEST_FIRST, LONGEST_FIRST, RANDOM;}

public class Server {

    Policy policy;

    LinkedList<Job> queue;   
    LinkedList<Job> completed;

    int time;
    int timeOfNextEvent;

    Job currentJob;

    public Server(Policy p){
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
            assert(timeOfNextEvent > time);
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
        
        //If there already is a queue, then add according to the policy of this instance of Server
        //Process always takes from the fron of the queue
        if (this.policy == Policy.FCFS){
            queue.addLast(j);
            //first come first serve, add to end of line
        }
        else if ( this.policy == Policy.SHORTEST_FIRST){
            //https://stackoverflow.com/a/19067509
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
        else if (this.policy== Policy.RANDOM){
            queue.add((int)Math.random()*queue.size(), j);

        }
    }

    void analyze(LinkedList<Job> completed){
        //do stuff

    }

    void exportDataToCSV(String filename){

        //https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Arrival Time,Departure Time,Service Time,Time In Queue,Total Time\n");
            for (Job j : completed) {
                writer.write(
                j.arrivalTime + "," 
                + j.departureTime + "," 
                + j.serviceLength + "," 
                + j.timeInQ + ","
                + j.totalTime + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }

    void exportDataToCSV(){
        exportDataToCSV("data.csv");
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