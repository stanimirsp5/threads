package ExamWorkshop.vechiclesExercises.SandBox;

public class Dog extends Mammal{
    public static Dog dogInstance;
    public static int testNum;

    public Dog(){
        dogInstance = this;
        testNum = 5434534;
    }

    @Override
    public void makeNoice(){
        System.out.println("whof");
    }

    public void bark(){
        System.out.println("Whof whof !!");
    }

    public static Dog getInstance(){
        System.out.println(testNum);
        return dogInstance;
    }
}
