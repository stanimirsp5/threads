package ExamWorkshop.InspectorsOnBridge.Gui;

import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CarGui{
    Direction direction;
    PathTransition pathTransition;

    public CarGui(Direction direction) {
        this.direction = direction;
        Rectangle car = initCar();

        if (direction == Direction.Left) {
            leftCarAnimation(car);
        } else {
            rightCarAnimation(car);
        }
    }
    public Rectangle initCar(){
        Rectangle rect = new Rectangle(); // nodes
        rect.setX(450);
        rect.setY(400);
        rect.setWidth(20);
        rect.setHeight(20);
        rect.setStroke(Color.RED);
        rect.setFill(Color.WHITE);
        rect.setStrokeWidth(2);
        MainGui.root.getChildren().add(rect);

        return rect;
    }

    public void runCar(){
        pathTransition.play();
    }

    private void rightCarAnimation(Rectangle rect){
        MoveTo moveto = new MoveTo(1000, 300);

        LineTo line1 = new LineTo(800, 300);
        LineTo line2 = new LineTo(800, 350);
        LineTo line3 = new LineTo(400, 350);
        LineTo line4 = new LineTo(400, 300);
        LineTo line5 = new LineTo(200, 300);

        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);

        drawPath(path,rect);
    }

    private void leftCarAnimation(Rectangle rect){
        MoveTo moveto = new MoveTo(200, 500);

        LineTo line1 = new LineTo(400, 400);
        LineTo line2 = new LineTo(500, 350);
        LineTo line3 = new LineTo(800, 350);
        LineTo line4 = new LineTo(850, 400);
        LineTo line5 = new LineTo(1100, 500);

        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);

        drawPath(path,rect);
    }

    private void drawPath(Path path,Rectangle rect){
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(5000));
        pathTransition.setNode(rect);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //pathTransition.setCycleCount(10);
        pathTransition.setAutoReverse(true);
    }

}
