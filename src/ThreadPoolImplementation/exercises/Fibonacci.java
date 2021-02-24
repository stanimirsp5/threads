package ThreadPoolImplementation.exercises;

public class Fibonacci {
    public final static int NUM = 100;
    public static void main(String[] args){
        Worker worker = new Worker();
        System.out.print("1 ");
        worker.fibonacciLoop(NUM);
        System.out.println();
        System.out.print("1 ");
        worker.fibonacciRec(NUM,1,1);
    }
}
class Worker{
    public void fibonacciLoop(int n){
        int prev = 1;
        int curr = 1;
        int newN = 0;
        System.out.print(curr + " ");

        while (newN < n){
            newN = curr+prev;
            System.out.print(newN + " ");
            prev = curr;
            curr = newN;
        }

    }

    public void fibonacciRec(int n,int curr, int prev){

        if(n <= curr){

            return;
        }
        System.out.print(curr +" ");
        fibonacciRec(n,prev+curr, curr);

    }
}
