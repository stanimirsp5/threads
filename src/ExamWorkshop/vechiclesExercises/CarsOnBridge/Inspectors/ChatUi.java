package ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors.Inspector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatUi implements ActionListener {

    private Inspector inspector;
    private static JTextArea textArea;
    private JTextField textField;
    private JButton btn;

    public ChatUi(){

    }

    public void start(){
        JFrame f=new JFrame("Inspector" );

        JPanel panel=new JPanel();
        panel.setBounds(0,5, 300,180);

        textArea=new JTextArea(10,20);
        JScrollPane scroll = new JScrollPane (textArea,
               JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll);
        f.add(panel);

        textField = new JTextField("write message");
        textField.setBounds(15,190, 200,30);
        f.add(textField);

        btn=new JButton("Send");
        btn.setBounds(15,225,95,30);
        btn.addActionListener(this);
        f.add(btn);

        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);

        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Print message from client to all text areas
     */
    public static void printToClients(String message){
        textArea.append(message + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String message= textField.getText();
        textArea.append(message + "\n");
    }
}