import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;

public class Generator {

    //somehow create a list of jobs sorted by arrival time

    public static LinkedList<Job> generateJobList(int number){

        LinkedList<Job> toReturn = new LinkedList<Job>();
        Random r = new Random();
        int maxTime = number * 10;

        for (int i =0; i< number; i++){
            Job j = new Job(r.nextInt(1000), r.nextInt(maxTime));
            toReturn.add(j);
        }
        sortJobList(toReturn);

        return toReturn;
    }



    static void sortJobList(LinkedList<Job> l){
        Collections.sort(l);
    }

    
}
