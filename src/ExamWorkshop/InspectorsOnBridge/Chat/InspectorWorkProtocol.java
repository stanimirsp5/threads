package ExamWorkshop.InspectorsOnBridge.Chat;

import ExamWorkshop.InspectorsOnBridge.Bridge.StateContainer;

public class InspectorWorkProtocol {
    public static ProtocolStates currentState = ProtocolStates.FREECHAT;

    public ProtocolStates getCurrentState() {
        return currentState;
    }

    public String processInput(String userInput,String workerName){
        String message = userInput;

        if(ProtocolStates.FREECHAT == currentState) {
            if (userInput.equalsIgnoreCase("Inspection time")) {
                message = "*Inspector chat mode ON*\n"
                        + "Waiting for all inspectors to say if cars can be stopped. Type Yes/No";
                currentState = ProtocolStates.INSPECTORCHAT;
            }
        }

        else if(ProtocolStates.INSPECTORCHAT == currentState){
            if (userInput.equalsIgnoreCase("Yes")) {
                message = "All is clear from my side.";
                currentState = ProtocolStates.GIVENRESPONSE;
            } else if(userInput.equalsIgnoreCase("No")) {
                message = "Bad news inspection can't begin right now."
                        + "*Inspector chat mode OFF*";
                currentState = ProtocolStates.FREECHAT;
            }else {
                message = "You're supposed to say \"Yes or No\"! " +
                        "Try again. Can cars be stopped?";
            }
        }

        else if(ProtocolStates.GIVENRESPONSE == currentState){
            if (userInput.equalsIgnoreCase("Stop cars")) {
                message = "Cars are stopped. An inspection is carried out...";
                //currentState = ProtocolStates.FREECHAT;
                currentState = ProtocolStates.STOPCARMOVEMENT;
                updateStateContainer();
            }else {
                message = "You're supposed to say \"Stop cars\"" +
                        "Try again.";
            }
        }

        else if(ProtocolStates.STOPCARMOVEMENT == currentState){
            if (userInput.equalsIgnoreCase("End inspection")) {
                message = "End of inspection. Everything is fine."
                        + "*Inspector chat mode OFF*";
                currentState = ProtocolStates.FREECHAT;
                updateStateContainer();
            }else {
                message = "You're supposed to say \"End inspection\"" +
                        "Try again.";
            }
        }

        return workerName + ": "+ message;
    }

    private void updateStateContainer(){
        StateContainer.currentState = currentState;
        StateContainer.updateBridge();
    }
}

