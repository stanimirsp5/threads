package threads.Examples;

public class test {
    public static void main(String[] args) throws InterruptedException {

        CommandSector commandSector = new CommandSector();
        MyThread myThread = new MyThread(commandSector);
        new Thread(myThread).start();
        Thread.sleep(500);
        commandSector.counting();
    }
}

class MyThread implements Runnable{
    public MyThread(CommandSector commandSector) {
        this.commandSector = commandSector;
    }

    CommandSector commandSector;


    @Override
    public void run() {
        try {
            commandSector.countRestrictor();
            //commandSector.counting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CommandSector{
    volatile boolean isCountReached = true;
    int counter;

    public synchronized void countRestrictor() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " wait");

        while (isCountReached){
            wait();
        }

        notifyAll();
        System.out.println(Thread.currentThread().getName() + " wake up");
    }

    public synchronized void counting() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " counting");

        while (true){
            counter++;
            Thread.sleep(100);
            System.out.println("counter "+counter + " by "+Thread.currentThread().getName());
            if(counter == 20) {
                System.out.println(Thread.currentThread().getName() + " ready to stop");
                //notifyAll();
                isCountReached = false;
                countRestrictor();

                break;
            }
        }

    }
}