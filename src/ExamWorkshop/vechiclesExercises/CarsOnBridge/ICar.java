package ExamWorkshop.vechiclesExercises.CarsOnBridge;

public interface ICar {

    /**
     * Returns current position of car on the road in meters
     * @return current position
     */
    double getPosition();

    /**
     * find needed time to cover road distance
     * d = 1000m, v = 90km/h or 25 m/s, t = d/v
     * time = 1000/25 = 40seconds needed to cover distance
     * program has 5 iterations in loop so 40/5 = 8s. If you drive with 90km/h (25m/s) you need 8s to cover 200m
     * @return distance in meters
     */
    double setPosition();
}
