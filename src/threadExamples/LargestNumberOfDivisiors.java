package threadExamples;

public class LargestNumberOfDivisiors {

    public static void main(String[] args){
        divisors(100000);
    }

    private static void divisors(int num){

        int divisorsCount = 0;
        int oldDivisorsCount = 0;

        int numWithMostDivisors = 0;

        for (int i = 2; i <= num ; i++) {// num to divide

            for (int j = 1; j <= num; j++) { // dividers

                if(i % j == 0){
                    divisorsCount++;
                }
            }

            if(oldDivisorsCount < divisorsCount){
                oldDivisorsCount = divisorsCount;
                numWithMostDivisors = i;

            }
            divisorsCount=0;

            // oldDivisorsCount = divisorsCount;

        }
        System.out.println(numWithMostDivisors +" divisors: "+ oldDivisorsCount);
    }

}
