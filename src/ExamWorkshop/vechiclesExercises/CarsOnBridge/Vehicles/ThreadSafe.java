package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//https://stackoverflow.com/questions/4452041/java-naming-conventions-for-thread-safety
//https://jcip.net/annotations/doc/net/jcip/annotations/ThreadSafe.html

@Documented
@Target(value= {ElementType.METHOD, TYPE})
@Retention(value=RUNTIME)
public @interface ThreadSafe{}