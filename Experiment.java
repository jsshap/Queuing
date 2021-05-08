import java.io.FileNotFoundException;
import java.io.PrintStream;
public class Experiment {

    
    public static void main(String[] args){
        


        for (Policy pol : Policy.values()){
            String filename = pol.name() + "_variance.csv";
            try{
                PrintStream csvFile = new PrintStream(filename);
                System.setOut(csvFile);
                System.out.println("Response Time");
                System.out.println("p,E[N],E[T]\n"); 
                Server s = new Server(.5, .5, pol);
                s.simulate(300000);
                s.computeVariance();
            }
            catch(FileNotFoundException e){};
            //Here we want to change the file we write to
        }
    


    }
}
