import java.io.FileNotFoundException;
import java.io.PrintStream;
public class Experiment {

    
    public static void main(String[] args){
        


        for (Policy pol : Policy.values()){
            String filename = pol.name() + "data.csv";
            try{
                PrintStream csvFile = new PrintStream(filename);

                System.setOut(csvFile);
                System.out.println("p,E[N],E[T]\n"); 
                for (double p =.01; p <.51; p+=.01){
                    Server s = new Server(p, .5, pol);
                    s.simulate(300000);
                    //Here, we want to write to a file
                    s.processData();//Currently processData prints to stout
                    //If there were a way to change what stout between line 7 and 8, that would do it
                }
            }
            catch(FileNotFoundException e){};
            //Here we want to change the file we write to
        }

    }
}
