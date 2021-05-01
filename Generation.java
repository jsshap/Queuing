public class Generation {
    
    public static int generateGeometric(double p){

        double y = Math.random();
        int value=0;
        int i =1;
        double a =0;
        double b =0;
        while (value==0){
            a = geoPMF(i-1, p) + a;
            b = a + geoPMF(i,p);
            //System.out.println(y +" " +a+" " +b);
            if (y> a && y < b){
                value = i;
            }
            i++;
        }
        return value;
    }
    
    static double geoPMF(int val, double p){
        if (val==0){
            return 0;
        }
        double prob = p*Math.pow(1-p, val-1);
        return prob;

        
    }
    
}
