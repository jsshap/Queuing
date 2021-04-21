import java.util.LinkedList;


public class Simulator {

    enum Policy
    {
    FCFS, SHORTEST_FIRST, LONGEST_FIRST;
    }


    
    static LinkedList<Job> queue = new LinkedList<Job>();    
    static int time=0;
    static int timeOfNextEvent=0;

    static LinkedList<Job> completed = new LinkedList<Job>();

    void startNewSimulator(){
        time=0;
        timeOfNextEvent=0;
        queue= new LinkedList<Job>();
        completed = new LinkedList<Job>();

    }

    void simulate(LinkedList<Job> jobs, Policy p){//figure out how to use enumerated types for this


        /*
        while !(jobs is empty and queue is empty){
            update next event time
            proceed to next event
            either process arrival or process departure
        }


        */


        /*
        take job from jobs, add to queue, select job from queue (based on policy), add jobs to completed list

        then alyze completed list

        */
        /*
        processJob(j)
        remove j from queue and put on completed list

        */

    }

    void processJob(Job j){
        //Take first job in queue
            //jobArrival guarentees the will be in the right order for our policy
        //update instance variables of job and remove from queue
    }

    void jobArrival(Job j, Policy p){
        //grab from top of jobs list
        //place into Queue according to policy
    }

    void analyze(LinkedList<Job> completed){
        //do stuff

    }


}
