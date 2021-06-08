package ExamWorkshop.InspectorsOnBridge.Gui;

import javafx.application.Platform;
import javafx.scene.shape.Line;

public class BridgeGui{

    public void initBridge(){

        // Bridge UP
        //Line(startX,startY,endX,endY)
        Line part1 = new Line(1000,200,750,200); // width - 250
        Line part2 = new Line(750,200,750,300); // w - 100
        Line part3 = new Line(750,300,450,300); // w - 300
        Line part4 = new Line(450,300,450,200); // w - 100
        Line part5 = new Line(450,200,200,200); // w - 250

        // Bridge Down
        //Line(startX,startY,endX,endY)
        Line part1Down = new Line(1000,500,750,500); // width - 250
        Line part2Down = new Line(750,500,750,400); // w - 100
        Line part3Down = new Line(750,400,450,400); // w - 300
        Line part4Down = new Line(450,400,450,500); // w - 100
        Line part5Down = new Line(450,500,200,500); // w - 250

        //UI can be updated only from JavaFX Application Thread
        // make UI changes on UI thread - https://stackoverflow.com/questions/35382501/java-lang-illegalstateexception-not-on-fx-application-thread-calling-function
        Platform.runLater(() -> MainGui.root.getChildren().addAll(part1, part2, part3, part4, part5, part1Down, part2Down, part3Down, part4Down, part5Down));
    }
}




