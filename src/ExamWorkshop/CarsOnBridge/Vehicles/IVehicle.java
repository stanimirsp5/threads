package ExamWorkshop.CarsOnBridge.Vehicles;

import ExamWorkshop.CarsOnBridge.Bridge.Direction;
import ExamWorkshop.CarsOnBridge.Vehicles.VehicleTypes.VehicleType;

public interface IVehicle {

    /**
     * Show vehicle direction of movement
     * @return Direction direction
     */
    Direction getDirection();
    /**
     * Set vehicle direction of movement
     */
    void setDirection(Direction direction);
    /**
     * Show vehicle name. Formatted by:
     * vehicle direction represented with arrows (<---- for left, ----> right),
     * vehicle type (ambulance, firetruck, car...),
     * vehicle number (under which consecutive number the car was created)
     * @return vehicle name as string
     */
    String getName();
    void setName(String name);

    /**
     * Add instance of current thread on witch vehicle is running upon.
     * @param myThread
     */
    void setThread(Thread myThread);
    VehicleType getType();
    void leaveBridge();
}

