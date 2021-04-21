public class Job{

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


}