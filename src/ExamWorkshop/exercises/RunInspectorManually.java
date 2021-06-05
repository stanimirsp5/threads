package ExamWorkshop.exercises;

import ExamWorkshop.InspectorsOnBridge.Chat.Inspector;
import javafx.application.Platform;

public class RunInspectorManually {

    public static void main(String[] args) throws InterruptedException {

        Inspector inspector = new Inspector(2);

        // https://stackoverflow.com/questions/11273773/javafx-2-1-toolkit-not-initialized
        Platform.startup(() -> // This method starts the JavaFX runtime.
        {
            // This block will be executed on JavaFX Thread
            new Thread(inspector::createInspector).start();
        });

    }
}
