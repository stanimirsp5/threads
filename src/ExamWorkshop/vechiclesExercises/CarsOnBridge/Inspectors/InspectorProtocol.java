package ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

import java.util.Objects;
import java.util.regex.Pattern;

public class InspectorProtocol {
    public static ProtocolStates currentState = ProtocolStates.FREECHAT;
    private static String lastInspectorName;

    public String processInput(String userInput){
        String inspectorName = userInput.split(Pattern.quote(":"))[0];
        userInput = userInput.split(Pattern.quote(":"))[1].trim();
        String message = userInput;


        if(ProtocolStates.FREECHAT != currentState &&
                Objects.equals(lastInspectorName, inspectorName)) {

            return inspectorName + " can't write to yourself.";
        }

        else if(ProtocolStates.FREECHAT == currentState) {
            if (userInput.equalsIgnoreCase("Inspection time") ||
                    userInput.equalsIgnoreCase("IT")) {
                message = "\n*Inspector chat mode ON*\n"
                        + "Waiting for all inspectors to say if vehicles can be stopped. Type Yes/No";
                lastInspectorName = inspectorName;
                currentState = ProtocolStates.INSPECTORCHAT;
            }
        }

        else if(ProtocolStates.INSPECTORCHAT == currentState){
            if (userInput.equalsIgnoreCase("Yes")) {
                message = "All is clear from my side.";
                lastInspectorName = inspectorName;
                currentState = ProtocolStates.GIVENRESPONSE;
            } else if(userInput.equalsIgnoreCase("No")) {
                message = "Bad news inspection can't begin right now."
                        + "*Inspector chat mode OFF*";
                currentState = ProtocolStates.FREECHAT;
            }else {
                message = "You're supposed to say \"Yes or No\"! " +
                        "Try again. Can vehicles be stopped?";
            }
        }

        else if(ProtocolStates.GIVENRESPONSE == currentState){
            if (userInput.equalsIgnoreCase("Stop vehicles")) {
                message = "Vehicles are stopped. An inspection is carried out...";
                lastInspectorName = inspectorName;
                currentState = ProtocolStates.STOPCARMOVEMENT;
                updateStateContainer();
            }else {
                message = "You're supposed to say \"Stop vehicles\" " +
                        "Try again.";
            }
        }

        else if(ProtocolStates.STOPCARMOVEMENT == currentState){
            if (userInput.equalsIgnoreCase("End inspection")) {
                message = "End of inspection. Everything is fine."
                        + "\n*Inspector chat mode OFF*";
                lastInspectorName = inspectorName;
                currentState = ProtocolStates.FREECHAT;
                updateStateContainer();
            }else {
                message = "You're supposed to say \"End inspection\" " +
                        "Try again.";
            }
        }
        return inspectorName + ": "+message;
    }

    private static void updateStateContainer(){
        Bridge bridge = Bridge.getInstance();
        if(bridge == null) return;

        bridge.closeBridge();
    }

}
