import java.math.BigInteger;
import java.util.*;

public class Main{
        public static String classify(Set<?> s) {

            return "Set";

        }

        public static String classify(List<?> lst) {

            return "List";

        }

        public static String classify(Collection<?> c) {

            return "Unknown Collection";

        }

        public static void main(String[] args) {

            Collection<?>[] collections = {

                    new HashSet<String>(),

                    new ArrayList<BigInteger>(),

                    new HashMap<String, String>().values()

            };

            for (Collection<?> c : collections)
                System.out.println(classify(c));

        }

    }




//    public static void main(String[] args) {
//        int numOfProcessors = Runtime.getRuntime().availableProcessors();
////        System.out.println(numOfProcessors);
////        System.out.println(Thread.currentThread().getName());
//
//        Thread th1 = new Thread(new ThreadGenerator("th1"));// to check err: 'Main.this' cannot be referenced from a static context
//        th1.start();
//        Thread th2 = new Thread(new ThreadGenerator("th2"));// to check err: 'Main.this' cannot be referenced from a static context
//        th2.start();
//        th1.setPriority(Thread.NORM_PRIORITY-1);
//        System.out.println(th1.getPriority());
//        System.out.println(th2.getPriority());
//
//    }
//
//
//    private static class ThreadGenerator implements Runnable  {
//        String name = "";
//        public ThreadGenerator(String name){
//            this.name= name;
//        }
//
//        @Override
//        public void run() {
//            System.out.println("thread: " +name+" started");
//        }
//    }

//    public static void main(String[] args) {
//        Object a = new Object();
//        Object[] arr = new Object[5];
//        arr[1] = a;
//        arr[2] = a;
//        arr[3] = a;
//        arr[4] = a;
//    }

