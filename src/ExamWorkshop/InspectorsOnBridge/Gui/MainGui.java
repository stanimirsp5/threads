package ExamWorkshop.InspectorsOnBridge.Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainGui extends Application {
    public static Pane root = new Pane();
    public static Stage primaryStage;

    // compose with Main
    @Override
    public void start(Stage primaryStage) throws Exception {
        //root = new Pane();
        Button btn1= new Button("Say, Hello World");
        btn1.setLayoutX(250);
        btn1.setLayoutY(250);
        root.getChildren().addAll(btn1);

        //createChatWindow(primaryStage, 1);

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
}