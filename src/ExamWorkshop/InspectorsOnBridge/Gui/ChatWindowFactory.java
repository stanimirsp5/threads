package ExamWorkshop.InspectorsOnBridge.Gui;

import ExamWorkshop.InspectorsOnBridge.Chat.Inspector;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChatWindowFactory {
    TextArea textArea;
    TextField textField;
    Inspector inspector;

    public ChatWindowFactory(Inspector inspector) {
        this.inspector = inspector;
    }

    public void writeToTextArea(String msg){
        if(msg == null) return;
        //System.out.println(msg);
        String newLine = System.getProperty("line.separator");

        textArea.appendText(newLine + msg);
    }


    public void initChatWindow(int inspectorNumber){
        textArea=new TextArea();
        textArea.setLayoutY(0);
        textArea.setDisable(true);
        textField=new TextField();
        textField.setLayoutY(200);

        Button b = new Button("Submit");
        b.setLayoutY(250);
        b.setLayoutX(200);

        b.setOnAction(e-> {
            inspector.send(textField.getText());
            textField.clear();
        });

        Pane secondaryLayout = new Pane();

        secondaryLayout.getChildren().add(textField);
        secondaryLayout.getChildren().add(b);
        secondaryLayout.getChildren().add(textArea);


        Scene secondScene = new Scene(secondaryLayout, 450, 300);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Inspector #"+ inspectorNumber);
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        if(inspectorNumber % 2 != 0) {
            newWindow.setX(-200);
            newWindow.setY(900);
        }else {
            newWindow.setX(1470);
            newWindow.setY(900);
        }

        newWindow.show();
    }
}
