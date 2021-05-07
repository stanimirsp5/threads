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
        Scene scene=new Scene(root, 1200,800); //x,y layout needs to be added to a scene. Scene remains at the higher level in the hierarchy of application structure
        primaryStage.setScene(scene);
        primaryStage.setTitle("First JavaFX Application");
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
    public static void stackCar(StackPane stack){
        //Platform.runLater(() ->
            root.getChildren().add(stack);
        //);
    }
}

//    MoveTo moveto = new MoveTo(0, 0);
//
//    LineTo line5 = new LineTo(200 + positionNumber, 300);
//
//    // create a Path
//    Path path = new Path(moveto, line5);
//
//        pathTransition = new PathTransition();
//                pathTransition.setDuration(Duration.millis(10000));
//                pathTransition.setNode(stack);
//                pathTransition.setPath(path);
//                pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//                //pathTransition.setCycleCount(10);
//                pathTransition.setAutoReverse(true);