package ExamWorkshop.vechiclesExercises.CarsOnBridge;

/**
 *  >......1>...........<2
 *  car 1 with direction from left to right - right
 *  bridge is direction right
 *  car2 is direction left (from right to left)
 */
public interface IBridge {
    /**
     * One car at same time can access this method.
     * Only cars at same direction can be on the bridge others must wait.
     * @param car
     * @throws InterruptedException
     */
    void takeBridge(Car car) throws InterruptedException;

    /**
     * If only one thread is left active,
     * notify waiting threads(cars) try to begin work(move on bridge) again
     * @param car
     */
    void leaveBridge(Car car);

}
