package threads.Synchronization;

//        class IntShare1 {
//            private int intShared = -1;
//            public void setIntShared( int val ){
//                System.out.println( Thread.currentThread().getName() +
//                        " set intShared: " + val );
//                intShared = val;
//            }
//            public int getIntShared(){
//                System.out.println( Thread.currentThread().getName() +
//                        " get intShared " + intShared );
//                return intShared;
//            }
//        }
      class IntShare1 {
        private int intShared = -1;
        private boolean writable= true;
        public synchronized void setIntShared( int val ){
            while(!writable) {
                try{     wait();   }
                catch(InterruptedException e){
                    System.err.println(e);
                }
            }
            System.out.println( Thread.currentThread().getName() +
                    " set intShared: " + val );
            intShared = val;
            writable=false;
            notify();
        }
        public synchronized int getIntShared(){
            while(writable) {
                try{     wait();   }
                catch(InterruptedException e){
                    System.err.println(e);
                }
            }
            System.out.println( Thread.currentThread().getName() +
                    " get intShared " + intShared );
            writable=true;
            notify();
            return intShared;
        }
    }

    class SetShared extends Thread {
        private IntShare1 pGuarde;
        public SetShared( IntShare1 h ){
            super( "Set Shared" );
            pGuarde = h;
        }
        public void run(){
            for ( int counter = 1; counter <= 10; counter++ ) {
                // sleep fot random time
                try {Thread.sleep( (int) ( Math.random() * 30 ) );}
                catch( InterruptedException e ) {
                    System.err.println( e.toString() );
                }
                pGuarde.setIntShared( counter );
            }
            System.out.println( getName() + " finished"  );
        }
    }
    class GetShared extends Thread {
        private IntShare1 cGuarde;
        public GetShared( IntShare1 h ){
            super( "GetShared" );
            cGuarde = h;
        }
        public void run(){
            int val, sum = 0;
            do {
                // sleep fot random time
                try {Thread.sleep( (int) ( Math.random() * 10 ) );}
                catch( InterruptedException e ) {
                    System.err.println( e.toString() );
                }
                val = cGuarde.getIntShared();
                sum += val;
            } while ( val != 10 );
            System.out.println(
                    getName() + " Total: " + sum);
        }
    }
    public class Example1 {
        public static void main( String args[] ){
            IntShare1 h =new IntShare1();
            SetShared p = new SetShared( h );
            GetShared c = new GetShared( h );
            p.start();
            c.start();
        }
    }
