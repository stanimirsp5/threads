package ExamWorkshop.CarsOnBridge.Inspectors;

import ExamWorkshop.CarsOnBridge.Helpers.ExceptionLogger;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatUi implements ActionListener {

    private Inspector inspector;
    private JTextArea textArea;
    private JTextField textField;
    private JButton btn;

    public ChatUi(Inspector inspector){
        this.inspector = inspector;
    }

    public void start(){
        JFrame f=new JFrame("Inspector #" + inspector.number);
        f.setBounds(-400,inspector.number * 350,0,0);
        JPanel panel=new JPanel();
        panel.setBounds(0,5, 300,180);

        textArea=new JTextArea();
        //textArea.setBounds(20,20, 400,200);
        JScrollPane scroll = new JScrollPane (textArea,
               JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,20, 500,150);
        panel.add(scroll);

        f.add(scroll);

        textField = new JTextField("");
        textField.setBounds(15,190, 350,30);
        f.add(textField);

        btn=new JButton("Send");
        btn.setBounds(15,225,95,30);
        btn.addActionListener(this);
        f.add(btn);

        f.setSize(600,300);
        f.setLayout(null);
        f.setVisible(true);

        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Print message from client to all text areas
     */
    public void printToClients(String message){
        textArea.append(message + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String message= textField.getText();
        try {
            inspector.send(message);
        } catch (IOException e) {
            ExceptionLogger.log(e);
        }
        textField.setText("");
    }
}