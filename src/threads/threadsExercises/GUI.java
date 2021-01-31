package threads.threadsExercises;

import javax.swing.*;

public class GUI {
    static JFrame f = new JFrame();//creating instance of JFrame
    //static JLabel runnableLabel = new JLabel();
    static JButton startB = new JButton("Start"), pauseB = new JButton("Pause"), newB = new JButton("New");

    public void init() {


        startB.setBounds(70, 10, 100, 40);//x axis, y axis, width, height
        pauseB.setBounds(200, 10, 100, 40);//x axis, y axis, width, height
        newB.setBounds(130, 60, 100, 40);//x axis, y axis, width, height

        //runnableLabel.setBounds(50, 50, 100, 30);

        f.add(startB);f.add(pauseB);f.add(newB);
        //f.add(runnableLabel);

        f.setSize(400, 500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        startB.addActionListener(new ThreadFactory.StartThread());
        pauseB.addActionListener(new ThreadFactory.StopThread());
        newB.addActionListener(new ThreadFactory.NewThread());
    }

}
