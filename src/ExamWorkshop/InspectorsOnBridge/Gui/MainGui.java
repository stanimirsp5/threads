package ExamWorkshop.InspectorsOnBridge.Gui;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
import ExamWorkshop.InspectorsOnBridge.Bridge.Car;
import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MainGui extends Application {
    static Pane root;
    // compose with Main
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Pane();
        Button btn1= new Button("Say, Hello World");
        btn1.setLayoutX(250);
        btn1.setLayoutY(250);

        //root.getChildren().add(rect);
        //root.getChildren().add(btn1);
        //root.getChildren().addAll(part1,part2,part3,part4,part5,part1Down,part2Down,part3Down,part4Down,part5Down);
        //root.getChildren().add(btn1);

        Scene scene=new Scene(root, 1200,800); //x,y layout needs to be added to a scene. Scene remains at the higher level in the hierarchy of application structure
        primaryStage.setScene(scene);
        primaryStage.setTitle("First JavaFX Application");
        primaryStage.show();
//        BridgeGui bridgeGui = new BridgeGui();
//        bridgeGui.initBridge();
    }
}
