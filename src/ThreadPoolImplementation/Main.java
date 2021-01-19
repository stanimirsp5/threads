package ThreadPoolImplementation;
//https://stackoverflow.com/questions/13429129/task-vs-thread-differences
public class Main {
    public static void main(String[] args){
        ThreadPool pool = new ThreadPool(3);

        for (int i = 0; i < 5; i++) {
            Task task = new Task(i, 1000/(i+1));
            pool.execute(task);
        }
       // pool.shutdown();
    }
}
