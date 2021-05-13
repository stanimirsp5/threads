package ExamWorkshop.InspectorsOnBridge.Gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChatWindowFactory {

    public void initChatWindow(int inspectorNumber){
        Label secondLabel = new Label("I'm a Label on new Window");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Inspector #" + inspectorNumber);
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
//        newWindow.setX(primaryStage.getX() + 200);
//        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
    }
}
