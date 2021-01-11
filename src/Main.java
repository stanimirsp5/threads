public class Main{
    public static void main(String[] args) {
        int numOfProcessors = Runtime.getRuntime().availableProcessors();
//        System.out.println(numOfProcessors);
//        System.out.println(Thread.currentThread().getName());

        Thread th1 = new Thread(new ThreadGenerator("th1"));// to check err: 'Main.this' cannot be referenced from a static context
        th1.start();
        Thread th2 = new Thread(new ThreadGenerator("th2"));// to check err: 'Main.this' cannot be referenced from a static context
        th2.start();
        th1.setPriority(Thread.NORM_PRIORITY-1);
        System.out.println(th1.getPriority());
        System.out.println(th2.getPriority());

    }


    private static class ThreadGenerator implements Runnable  {
        String name = "";
        public ThreadGenerator(String name){
            this.name= name;
        }

        @Override
        public void run() {
            System.out.println("thread: " +name+" started");
        }
    }
}
