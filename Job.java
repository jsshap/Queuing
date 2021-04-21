/*
immutable:

serviceLength/job size, arrival


figure out later at runtime:
departure time --> can calculate wait time in Queue
time in q

*/

public class Job{

    final int serviceLength;
    final int arrivalTime;

    int departureTime=0;
    int timeInQ=0;
    int totalTime=0;

    public Job(int serviceLength, int arrivalTime){
        this.serviceLength = serviceLength;
        this.arrivalTime = arrivalTime;
    }

    public void calculateValues(){
        timeInQ = departureTime-serviceLength-arrivalTime;
        totalTime=serviceLength+timeInQ;
        
        
    }


}