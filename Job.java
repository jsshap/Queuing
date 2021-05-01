public class Job{

    int arrivalTime;
    int departureTime;
    int serviceTime;
    int totalTime;
    int timeSpentInQueue;

    public Job(int arrivalTime, double q){
        this.arrivalTime=arrivalTime;
        this.serviceTime=Generation.generateGeometric(q);
    }

    public String toString(){
        String s = "";

        s+=("JOB--------\n");
        s+=("Arrival: " + arrivalTime +"\n");
        s+= "Spent in q: "+ timeSpentInQueue +"\n";
        s+="Service Time: "+ serviceTime +"\n";
        s+= ("Departure Time: "+ departureTime + "\n");
        s+=("==========\n");

        return s;
    }
}