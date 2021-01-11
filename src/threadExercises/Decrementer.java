package threadExercises;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.TextField;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Decrementer extends Application {
    private static Decrement decrement= new Decrement();
    public static Text text= new Text();
    public static Label label=new Label();
    public static boolean running = true;

    public static Thread worker = new Thread();

    private static CountNumbers countNumbers = new CountNumbers();
    private static int MAX_NUM = 99;
    private static int NUM_THREADS = 10;

    public static String textMessage="";

    @Override
    public void start(Stage primaryStage) throws Exception {
        //StackPane root = new StackPane();
        Group root = new Group();
        //Text text = new Text();

        text.setX(100);
        text.setY(100);


        Button startBtn = new Button("start threads");
        startBtn.setTranslateX(100);
        startBtn.setTranslateY(0);

        Button stopBtn = new Button("stop threads");
        stopBtn.setTranslateX(100);
        stopBtn.setTranslateY(30);

        Scene scene = new Scene(root, 500, 500);
        root.getChildren().add(text);
        root.getChildren().add(label);
        label.setTranslateY(470);
        label.setTranslateX(250);

        Label labelMaxNum= new Label();
        labelMaxNum.setText("Number: "+String.valueOf(MAX_NUM));
        root.getChildren().add(labelMaxNum);
        labelMaxNum.setTranslateY(0);
        labelMaxNum.setTranslateX(220);

        Label labelThreadsNum= new Label();
        labelThreadsNum.setText("Threads "+String.valueOf(NUM_THREADS));
        root.getChildren().add(labelThreadsNum);
        labelThreadsNum.setTranslateY(20);
        labelThreadsNum.setTranslateX(220);

        root.getChildren().add(startBtn);
        root.getChildren().add(stopBtn);

        primaryStage.setTitle("Button Class Example");
        primaryStage.setScene(scene);
        primaryStage.show();


        //Creating EventHandler
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub

                if(event.getSource()==startBtn)
                {
                    if(running) {
                        try {
                            Decrementer.startThreads();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        worker.notifyAll();
                        running = true;
                    }
                }
                if(event.getSource()==stopBtn)
                {
                    try {
                        stopProcess();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // trans.pause(); //animation will be paused when the pause button is clicked
                }
                event.consume();
            }

        };
        //Adding Handler for the play and pause button
        startBtn.setOnMouseClicked(handler);
        stopBtn.setOnMouseClicked(handler);

    }

    public static void main(String[] args) throws InterruptedException {

        //JForms.runForm();
        Application.launch(args);

    }

    public void stopProcess() throws InterruptedException {
        System.out.println("STOOOP!!!");
        worker.wait();
        running = false;
    }

    public static void startThreads() throws InterruptedException {
        long start = System.currentTimeMillis();
        int incrementer = MAX_NUM/ NUM_THREADS;
        int min = 0;
        int max = min+incrementer;
        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable tr1 = new ThreadGenerator(min, max);
            worker = new Thread(tr1);
            worker.start();
            worker.join();
           // worker.interrupt();
            min = max;
        }
        Thread.sleep(100);
        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;

        //System.out.println(countNumbers.getEvenNumbers());
        // System.out.println(countNumbers.getOddNumbers());
        System.out.println(timeElapsed);
        Decrementer.text.setText(Decrementer.textMessage);
        Decrementer.label.setText("Total time: " +timeElapsed);

    }

}

class Decrement{
    static int MAX_NUM = 100;

    public int dec(){
        return MAX_NUM--;
    }
    public int getResult(){
        return MAX_NUM;
    }

}
class CountNumbers{
    static int evenNumbers;
    static int oddNumbers;

    public void countEvenNumbers(int num){
        if(num % 2 == 0){
            evenNumbers++;
        }
        //return evenNumbers;
    }

    public int getEvenNumbers(){
        return evenNumbers;
    }
 public int getOddNumbers(){
        return oddNumbers;
    }

    public void countOddNumbers(int num){
        if(num % 2 != 0){
            oddNumbers++;
        }
    }
}
class ThreadGenerator implements Runnable{
//    private volatile boolean running = true;
    CountNumbers countNumbers = new CountNumbers();
    int min;
    int max;

    StringBuilder sb = new StringBuilder();
    public ThreadGenerator(int min,int max){
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
            for (int i = 0; i >= min && i < max && Decrementer.running; i++) {
                countNumbers.countEvenNumbers(i);
                countNumbers.countOddNumbers(i);
                //System.out.println("Thread with name " + Thread.currentThread().getName() + " even " + countNumbers.getEvenNumbers()+ " odd " + countNumbers.getOddNumbers());

            }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //sb.append("Thread with name "+Thread.currentThread().getName()+" even "+countNumbers.getEvenNumbers());
            Decrementer.textMessage += "Thread with name " + Thread.currentThread().getName() + " even " + countNumbers.getEvenNumbers() + " odd " + countNumbers.getOddNumbers() + System.lineSeparator();

        System.out.println("Thread with name "+Thread.currentThread().getName()+" even "+countNumbers.getEvenNumbers());

       }
}

class Thread1 implements Runnable{
    CountNumbers countNumbers = new CountNumbers();

    @Override
    public void run() {

        for (int i = 0; i < 1000000000; i++) {
            countNumbers.countEvenNumbers(i);
//            countNumbers.countOddNumbers(i);
           // System.out.println("Thread with name " + Thread.currentThread().getName() + " even " + countNumbers.getEvenNumbers());
        }
    }
}

class Thread2 implements Runnable{
    CountNumbers countNumbers = new CountNumbers();

    Decrement decrement = new Decrement();

    @Override
    public void run() {
        System.out.println("Thread 2");
        for (int i = 0; i < 1000000000; i++) {
//            countNumbers.countEvenNumbers(i);
            countNumbers.countOddNumbers(i);
            // System.out.println("Thread with name " + Thread.currentThread().getName() + " even " + countNumbers.getEvenNumbers());
        }
    }
}
