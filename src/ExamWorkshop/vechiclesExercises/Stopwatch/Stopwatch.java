package ExamWorkshop.vechiclesExercises.Stopwatch;

import java.util.concurrent.TimeUnit;

public class Stopwatch{
    long start;
    long end;

    long pauseStart;
    long pauseEnd;

    public void start(){
        start = System.nanoTime();
    }

    public void end(){
        end = System.nanoTime();
    }

    public void pause(){
        pauseStart = System.nanoTime();
    }

    public void resume(){
        pauseEnd = System.nanoTime();
    }

    public long getTime(){
        end();
        long elapsedTime = end - start;
        long elapsedPauseTime = pauseEnd - pauseStart;
        pauseEnd -= elapsedPauseTime;
        long time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        return time;
    }
    public long getTimeInSeconds(){
        end();
        long elapsedTime = end - start;
        long elapsedPauseTime = pauseEnd - pauseStart;
        pauseEnd -= elapsedPauseTime;
        long time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        return time;
    }
}
