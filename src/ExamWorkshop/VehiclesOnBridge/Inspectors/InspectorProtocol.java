package ExamWorkshop.VehiclesOnBridge.Inspectors;

import ExamWorkshop.VehiclesOnBridge.Bridge.Bridge;
import ExamWorkshop.VehiclesOnBridge.Bridge.Movement;

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
                message = "*Inspector chat mode ON*\n"
                        + "Waiting for all inspectors to say if vehicles can be stopped. Type Yes/No";
                lastInspectorName = inspectorName;
                currentState = ProtocolStates.INSPECTORCHAT;
            }
        }

        else if(ProtocolStates.INSPECTORCHAT == currentState ||
                ProtocolStates.SECOND_INSPECTOR_NEGATIVE_RESPONCE == currentState ){
            if (userInput.equalsIgnoreCase("Yes")) {
                // bridge is fully closed
                message = "All is clear from my side.";
                lastInspectorName = inspectorName;
                currentState = ProtocolStates.SECOND_INSPECTOR_POSITIVE_RESPONCE;
            } else if(userInput.equalsIgnoreCase("No") && ProtocolStates.SECOND_INSPECTOR_NEGATIVE_RESPONCE == currentState) {
                message = "Bad news inspection can't begin right now."
                        + "*Inspector chat mode OFF*";
                currentState = ProtocolStates.FREECHAT;
                updateStateContainer(Movement.OPEN);
            }else if(userInput.equalsIgnoreCase("No")) {
                // bridge is half closed, only special vehicles are allowed
                // ask for second time
                message = "Second inspector denied inspection. He will be asked for second time.";
                currentState = ProtocolStates.SECOND_INSPECTOR_NEGATIVE_RESPONCE;
                updateStateContainer(Movement.HALFCLOSED);
            }else {
                message = "You're supposed to say \"Yes or No\"! " +
                        "Try again. Can vehicles be stopped?";
            }
        }

        else if(ProtocolStates.SECOND_INSPECTOR_POSITIVE_RESPONCE == currentState){
            if (userInput.equalsIgnoreCase("Stop vehicles")) {
                message = "Vehicles are stopped. An inspection is carried out...";
                lastInspectorName = inspectorName;
                currentState = ProtocolStates.STOPCARMOVEMENT;
                updateStateContainer(Movement.FULLYCLOSED);
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
                updateStateContainer(Movement.OPEN);
            }else {
                message = "You're supposed to say \"End inspection\" " +
                        "Try again.";
            }
        }
        return inspectorName + ": "+message;
    }

    private static void updateStateContainer(Movement movement){
        Bridge bridge = Bridge.getInstance();
        if(bridge == null) return;

        bridge.closeBridge(movement);
    }

}