public class Job implements Comparable<Job>{

    final int serviceLength;//work needed on the job
    final int arrivalTime;

    int departureTime=0;
    int timeInQ=0;
    int totalTime=0;//service time and time in q

    public Job(int serviceLength, int arrivalTime){
        this.serviceLength = serviceLength;
        this.arrivalTime = arrivalTime;
    }

    public void calculateValues(){
        timeInQ = departureTime-serviceLength-arrivalTime;
        totalTime=serviceLength+timeInQ;
        
        
    }

    public int compareTo(Job j) {
        //positive if this instance is greater
        //negative if this instance is less
        //0 if equal
        if (this.arrivalTime < j.arrivalTime){
            return -1;
        }
        else if (this.arrivalTime > j.arrivalTime){
            return 1;
        }
        else{
            return 0;
        }
    }
}