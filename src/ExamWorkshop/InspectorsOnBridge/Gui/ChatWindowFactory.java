package ExamWorkshop.InspectorsOnBridge.Gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChatWindowFactory {

    public void initChatWindow(int inspectorNumber){
        TextArea textArea=new TextArea();
        textArea.setLayoutY(0);
        textArea.setDisable(true);
        //textArea.setEditable(false);
        TextField tf1=new TextField();
        tf1.setLayoutY(150);

        Button b = new Button("Submit");
        b.setLayoutY(200);
        b.setLayoutX(200);
        String newLine = System.getProperty("line.separator");

        b.setOnAction(e-> {
            System.out.print("You entered: User_ID: " + tf1.getText() + newLine);
            textArea.appendText(newLine + tf1.getText());
            tf1.clear();
        });

        Pane secondaryLayout = new Pane();

        secondaryLayout.getChildren().add(tf1);
        secondaryLayout.getChildren().add(b);
        secondaryLayout.getChildren().add(textArea);


        Scene secondScene = new Scene(secondaryLayout, 430, 300);

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
