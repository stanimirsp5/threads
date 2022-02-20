package ExamWorkshop.CarsOnBridge.Helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionLogger {

    public static void log(Throwable error){
        Logger logger = Logger.getLogger(ExceptionLogger.class.getName());
        logger.setLevel(Level.WARNING);
        logger.warning(error.toString());
        error.printStackTrace();
    }
}
