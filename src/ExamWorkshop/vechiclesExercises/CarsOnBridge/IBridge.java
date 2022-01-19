package ExamWorkshop.vechiclesExercises.CarsOnBridge;

public interface IBridge {

    void takeBridge(Car car) throws InterruptedException;

    void leaveBridge(Car car);

}
