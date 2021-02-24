package ThreadPoolImplementation.exercises;

public class Fibonacci {
    public final static int NUM = 100;
    public static void main(String[] args){
        Worker worker = new Worker();
        worker.fibonacciLoop(NUM);
    }
}
class Worker{
    public void fibonacciLoop(int n){
        int prev = 1;
        int curr = 1;
        int newN = 0;
        System.out.print(prev + " ");
        System.out.print(curr + " ");

//        for (int newN = 1; newN < n; newN+= prev) {
//
//            System.out.println(newN);
//            prev = newN;
//        }
        while (newN < n){
            newN = curr+prev;
            System.out.print(newN + " ");
            prev = curr;
            curr = newN;
        }

    }
}
