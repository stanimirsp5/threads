package ExamWorkshop.vechiclesExercises;

public class Dog {
    public static Dog dogInstance;
    public static int testNum;

    public Dog(){
        dogInstance = this;
        testNum = 5434534;
    }

    public void bark(){
        System.out.println("Whof whof !!");
    }

    public static Dog getInstance(){
        System.out.println(testNum);
        return dogInstance;
    }
}
