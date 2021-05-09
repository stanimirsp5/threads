package ExamWorkshop.InspectorsOnBridge.Gui;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage. Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Hello_World extends Application{
    public final int positionNumber = 0;
    boolean hideCar = false;

    public static void main (String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        Button btn1= new Button("hide car");
        btn1.setLayoutX(250);
        btn1.setLayoutY(250);

        Pane root = new Pane();
        composeBridge(root);
        //root.getChildren().addAll(addButtons()[0],addButtons()[1]);
        root.getChildren().add(btn1);
        //root.getChildren().add(stack);
        createChatWindow(primaryStage);


//        CarFactory carFactory = new CarFactory(root,1);
//        StackPane carStack = carFactory.createCar(0,0);
//        carFactory.run();
//
//        CarFactory carFactory2 = new CarFactory(root,2);
//        carFactory2.createCar(40,0);
//        carFactory2.run();

//        btn1.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent arg0) {
//                //leftCarAnimation(stack);
//                //runCar();
//                hideCar = !hideCar;
//                System.out.println("hide");
//                carStack.setVisible(hideCar);
//
//            }
//        });

        Scene scene=new Scene(root, 1200,800); //x,y layout needs to be added to a scene. Scene remains at the higher level in the hierarchy of application structure
        primaryStage.setScene(scene);
        primaryStage.setTitle("First JavaFX Application");

        primaryStage.show();
    }
    public void createChatWindow(Stage primaryStage){
        Label secondLabel = new Label("I'm a Label on new Window");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
    }
    public class CarFactory{
        Pane root;
        StackPane stack;
        public int positionNumber;

        public CarFactory(Pane root, int positionNumber) {
            this.root = root;
            this.positionNumber = positionNumber;
        }

        public StackPane createCar(int x,int y) {
            Rectangle rect = new Rectangle(); // nodes
            rect.setWidth(20);
            rect.setHeight(20);
            rect.setStroke(Color.RED);
            rect.setFill(Color.WHITE);
            rect.setStrokeWidth(2);

            Text text = new Text( " " +positionNumber);

            stack = new StackPane(); // layout must be implemented order to visualize the widgets properly. It exists at the top level of the scene graph
            stack.setLayoutX(x);
            stack.setLayoutY(y);
            stack.getChildren().addAll(rect,text);

            root.getChildren().add(stack);

            return stack;
        }

        public void run(){
            if(positionNumber == 1) {
                leftCarAnimation1(stack);
            }else{
                leftCarAnimation2(stack,40);
            }

        }
    }
    public void rightCarAnimation(StackPane rect){
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

//    public void leftCarAnimation(StackPane rect, int positionNumber){
//        MoveTo moveto = new MoveTo(positionNumber, 450);
//
//        LineTo line1 = new LineTo(400  +positionNumber, 450);
//        LineTo line2 = new LineTo(400  +positionNumber, 350);
//        LineTo line3 = new LineTo(800  +positionNumber, 350);
//        LineTo line4 = new LineTo(800  +positionNumber, 450);
//        LineTo line5 = new LineTo(1100 +positionNumber, 450);
//
//
//        // create a Path
//        Path path = new Path(moveto, line1,line2,line3,line4,line5);
//        drawPath(path,rect);
//    }
    public void leftCarAnimation1(StackPane rect){
        MoveTo moveto = new MoveTo(0, 450);

        LineTo line1 = new LineTo(400  , 450);
        LineTo line2 = new LineTo(400  , 350);
        LineTo line3 = new LineTo(800  , 350);
        LineTo line4 = new LineTo(800  , 450);
        LineTo line5 = new LineTo(1100 , 450);


        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);
        drawPath(path,rect);
    }

    public void leftCarAnimation2(StackPane rect, int positionNumber){
        MoveTo moveto = new MoveTo(0, 450);

        LineTo line1 = new LineTo(400  -positionNumber, 450);
        LineTo line2 = new LineTo(400  -positionNumber, 350);
        LineTo line3 = new LineTo(800  -positionNumber, 350);
        LineTo line4 = new LineTo(800  -positionNumber, 450);
        LineTo line5 = new LineTo(1100 , 450);


        // create a Path
        Path path = new Path(moveto, line1,line2,line3,line4,line5);
        drawPath(path,rect);
    }

    private void drawPath(Path path,StackPane car){
        PathTransition pathTransition;

        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setNode(car);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //pathTransition.setCycleCount(10);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
//         runCar();
    }

//    public void runCar(){
//        pathTransition.play();
//    }

    public void composeBridge(Pane root){
        // btn1.po(200);
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

        root.getChildren().addAll(part1,part2,part3,part4,part5,part1Down,part2Down,part3Down,part4Down,part5Down);
    }
    public Button[] addButtons(){
        // TODO Auto-generated method stub
        Button btn1= new Button("hide car");
        btn1.setLayoutX(250);
        btn1.setLayoutY(250);

        Button btn2= new Button("run car");
        btn2.setLayoutX(350);
        btn2.setLayoutY(350);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
               // runCar();
            }
        });
        return new Button[]{btn1, btn2};
    }
    public Rectangle moveRectangle(Rectangle rect){

        //Setting up the path
        Path path = new Path(); // provides facilities required for basic construction and management of a geometric path.
        // getElements Gets observable list of path elements of this path.
        path.getElements().add (new MoveTo(1000, 200.0f));// x,y Creates an addition to the path by moving to the specified coordinates.
        //path.getElements().add (new CubicCurveTo(240f, 230f, 500f, 340f, 600, 50f)); // Creates a curved path element, defined by three new points
        CubicCurveTo carPath1 = new CubicCurveTo();
        carPath1.setControlX1(800.0f); // Defines the X coordinate of the first Bézier control point
        carPath1.setControlX2(600.0f); // Defines the X coordinate of the second Bézier control point
        carPath1.setControlY1(200.0f); // Defines the Y coordinate of the first Bézier control point.
        carPath1.setControlY2(400.0f); // Defines the Y coordinate of the second Bézier control point.
        carPath1.setX(400.0f); // Defines the X coordinate of the final end point.
        carPath1.setY(200.0f); // Defines the Y coordinate of the final end point.
        path.getElements().add(carPath1);
        //Instantiating PathTransition class
        PathTransition pathTransition = new PathTransition();

        //Setting duration for the PathTransition
        pathTransition.setDuration(Duration.millis(5000));

        //Setting Node on which the path transition will be applied
        pathTransition.setNode(rect);

        //setting path for the path transition
        pathTransition.setPath(path);

        //setting orientation for the path transition
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        //setting up the cycle count
        pathTransition.setCycleCount(10);

        //setting auto reverse to be true
        pathTransition.setAutoReverse(true);

        //Playing path transition
        pathTransition.play();

        return rect;
    }
    public Rectangle moveRectangleTranslateTransition(){

        Rectangle rect = new Rectangle(); // nodes
        rect.setX(400);
        rect.setY(400);
        rect.setWidth(100);
        rect.setHeight(100);
        rect.setStroke(Color.RED);
        rect.setFill(Color.WHITE);
        rect.setStrokeWidth(2);

        //Instantiating TranslateTransition class
        TranslateTransition translate = new TranslateTransition();

        //shifting the X coordinate of the centre of the circle by 400
        translate.setByX(600);

        //setting the duration for the Translate transition
        translate.setDuration(Duration.millis(1000));

        //setting cycle count for the Translate transition
        //translate.setCycleCount(500);

        //the transition will set to be auto reversed by setting this to true
        //translate.setAutoReverse(true);

        //setting Circle as the node onto which the transition will be applied
        translate.setNode(rect);

        //playing the transition
        translate.play();

        return rect;
    }
}
