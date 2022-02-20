package ExamWorkshop.CarsOnBridge.Bridge;

/**
 * Three states of movement on the bridge
 * OPEN - all vehicles are allowed to drive on the bridge
 * HALFCLOSED - only ambulances and firetrucks are allowed
 * FULLYCLOSED - vehicles are not allowed
 */
public enum Movement {
    OPEN,
    HALFCLOSED,
    FULLYCLOSED,
}
