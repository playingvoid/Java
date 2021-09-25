package Misc;

public class Power {
    public double myPow(double x, int n) {

        if(n < 0){
            n = -n;
            x = 1.0 / x;
        }

        double result = 1.0;

        while(n != 0){
            if((n & 1) != 0){
                result *= x;
            }

            x *= x;
            n = n >>> 1;
        }

        return result;
    }

    public static void main(String[] args){
        Power p = new Power();
        System.out.println(p.myPow(2.1, 3));
    }
}
