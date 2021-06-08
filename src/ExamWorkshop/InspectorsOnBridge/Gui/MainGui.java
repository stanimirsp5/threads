package ExamWorkshop.InspectorsOnBridge.Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGui extends Application {
    public static Pane root = new Pane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene=new Scene(root, 1200,800); //x,y layout needs to be added to a scene. Scene remains at the higher level in the hierarchy of application structure
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inspectors on bridge");
        primaryStage.show();
    }
}