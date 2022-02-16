package ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors.Inspector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatUi implements ActionListener {

    private Inspector inspector;
    private JTextArea textArea;
    private JTextField textField;
    private JButton btn;

    public ChatUi(){

    }

    public void start(){
        JFrame f=new JFrame("Inspector" );

        JPanel panel=new JPanel();
        panel.setBounds(10,30, 300,180);

        textArea=new JTextArea(10,20);
        JScrollPane scroll = new JScrollPane (textArea,
               JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll);
        f.add(panel);

        textField = new JTextField("write message");
        textField.setBounds(30,215, 200,30);
        f.add(textField);

        JButton btn=new JButton("Send");
        btn.setBounds(30,250,95,30);
        btn.addActionListener(this);
        f.add(btn);

        f.setSize(350,350);
        f.setLayout(null);
        f.setVisible(true);

        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String message= textField.getText();
        textArea.append(message + "\n");
    }
}