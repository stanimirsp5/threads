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

public class CarGui {
    Direction direction;
    public PathTransition pathTransition;
    String name;
    int positionNumber;
    StackPane stack;

    public CarGui(Direction direction, String name, int positionNumber) {
        this.direction = direction;
        this.name = name;
        this.positionNumber = (positionNumber % 10) * 20 ; // prevent car collision
    }
    public StackPane initCar(){
        int carPositionX= 0;
        int carPositionY= 0;

        if (direction == Direction.Left) {
            carPositionX = 150 - positionNumber;
            carPositionY = 450;
        } else {
            carPositionX = 1000 + positionNumber;
            carPositionY = 250;
        }

        Rectangle rect = new Rectangle(); // nodes
        rect.setWidth(20);
        rect.setHeight(20);
        rect.setStroke(Color.RED);
        rect.setFill(Color.WHITE);
        rect.setStrokeWidth(2);

        Text rectText = new Text(name);

        stack=new StackPane(); //stackCarAndText
        stack.setLayoutX(carPositionX);
        stack.setLayoutY(carPositionY);

        stack.getChildren().addAll(rect, rectText);
        Platform.runLater(() ->
                MainGui.root.getChildren().add(stack)
        );

        if (direction == Direction.Left) {
            leftCarAnimation(stack,carPositionX);
        } else {
            rightCarAnimation(stack);
        }

        return stack;
    }

    public void runCar(){
        pathTransition.play();
    }

    public void hideCar(){
        stack.setVisible(false);
    }

    private void rightCarAnimation(StackPane car){
        MoveTo moveto = new MoveTo(positionNumber, 0);

        LineTo line1 = new LineTo(-220 - positionNumber, 0);
        LineTo line2 = new LineTo(-220 - positionNumber, 100);
        LineTo line3 = new LineTo(-630 - positionNumber, 100);
        LineTo line4 = new LineTo(-630 - positionNumber, 0);
        LineTo line5 = new LineTo(-1100, 0);

        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);

        drawPath(path,car);
    }

    private void leftCarAnimation(StackPane car, int carPositionX){
        MoveTo moveto = new MoveTo(0, 0);

        LineTo line1 = new LineTo(250 + positionNumber, 0);
        LineTo line2 = new LineTo(250 + positionNumber, -100);
        LineTo line3 = new LineTo(680 + positionNumber, -100);
        LineTo line4 = new LineTo(680 + positionNumber, 0);
        LineTo line5 = new LineTo(1100, 0);

        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);

        drawPath(path,car);
    }

    private void drawPath(Path path,StackPane car){
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(7000));
        pathTransition.setNode(car);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    }

}
