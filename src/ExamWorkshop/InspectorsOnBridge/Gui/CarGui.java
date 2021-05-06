package ExamWorkshop.InspectorsOnBridge.Gui;

import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CarGui{
    Direction direction;
    PathTransition pathTransition;
    String name;
    int positionNumber;

    public CarGui(Direction direction, String name, int positionNumber) {
        this.direction = direction;
        this.name = name;
        this.positionNumber = positionNumber * 20; // prevent car collision

        StackPane car = initCar();

        if (direction == Direction.Left) {
            leftCarAnimation(car);
        } else {
            rightCarAnimation(car);
        }
    }
    public StackPane initCar(){
        int carPositionX= 0;
        int carPositionY= 0;

        if (direction == Direction.Left) {
            carPositionX = 200 + positionNumber;
            carPositionY = 400;
        } else {
            carPositionX = 1000 + positionNumber;
            carPositionY = 250;
        }

        Rectangle rect = new Rectangle(); // nodes
//        rect.setX(carPositionX);
//        rect.setY(carPositionY);
        rect.setWidth(20);
        rect.setHeight(20);
        rect.setStroke(Color.RED);
        rect.setFill(Color.WHITE);
        rect.setStrokeWidth(2);

        Text rectText = new Text(name);

        StackPane stack=new StackPane(); //stackCarAndText
        stack.getChildren().addAll(rect, rectText);

        stack.setLayoutX(carPositionX);
        stack.setLayoutY(carPositionY);

        Platform.runLater(() ->
            MainGui.root.getChildren().add(stack));

        return stack;
    }

    public void runCar(){
        pathTransition.play();
    }

    private void rightCarAnimation(StackPane car){
        MoveTo moveto = new MoveTo(1000+ positionNumber, 300);

        LineTo line1 = new LineTo(800 + positionNumber, 300);
        LineTo line2 = new LineTo(800 + positionNumber, 350);
        LineTo line3 = new LineTo(400 + positionNumber, 350);
        LineTo line4 = new LineTo(400 + positionNumber, 300);
        LineTo line5 = new LineTo(200 + positionNumber, 300);

        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);

        drawPath(path,car);
    }

    private void leftCarAnimation(StackPane car){

        MoveTo moveto = new MoveTo(200 + positionNumber, 500);

        LineTo line1 = new LineTo(400  + positionNumber, 400);
        LineTo line2 = new LineTo(500  + positionNumber, 350);
        LineTo line3 = new LineTo(800  + positionNumber, 350);
        LineTo line4 = new LineTo(850  + positionNumber, 400);
        LineTo line5 = new LineTo(1100 + positionNumber, 500);

        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);

        drawPath(path,car);
    }

    private void drawPath(Path path,StackPane car){
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setNode(car);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //pathTransition.setCycleCount(10);
        pathTransition.setAutoReverse(true);
    }

}
