package ExamWorkshop.InspectorsOnBridge.Gui;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
import ExamWorkshop.InspectorsOnBridge.Bridge.Car;
import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class MainGui extends Application {
   public static Pane root = new Pane();

    // compose with Main
    @Override
    public void start(Stage primaryStage) throws Exception {
        //root = new Pane();
        Button btn1= new Button("Say, Hello World");
        btn1.setLayoutX(250);
        btn1.setLayoutY(250);

        root.getChildren().addAll(btn1);

        createChatWindow(primaryStage, 1);

        Scene scene=new Scene(root, 1200,800); //x,y layout needs to be added to a scene. Scene remains at the higher level in the hierarchy of application structure
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inspectors on bridge");
        primaryStage.show();
//        BridgeGui bridgeGui = new BridgeGui();
//        bridgeGui.initBridge();
//

//        CarGui carGui = new CarGui(Direction.Right,"test", 0);
//        //carGui.initCar();
//        btn1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent arg0) {  carGui.runCar(); }
//        });

    }
    public void createChatWindow(Stage primaryStage, int inspectorNumber){
        Label secondLabel = new Label("I'm a Label on new Window");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Inspector #" + inspectorNumber);
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
    }
}