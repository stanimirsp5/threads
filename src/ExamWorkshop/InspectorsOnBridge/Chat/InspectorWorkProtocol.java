package ExamWorkshop.InspectorsOnBridge.Chat;

public class InspectorWorkProtocol {
    ProtocolStates currentState = ProtocolStates.FREECHAT;

    public String processInput(String userInput,String workerName){
        String message = userInput;

//        if(ProtocolStates.NEWWORKER == currentState){
//            message = "New worker arrived. You must greet all other workers.";
//            currentState = ProtocolStates.GREETING;
//        }else
        if(ProtocolStates.FREECHAT == currentState) {
            if (userInput.equalsIgnoreCase("Inspection time")) {
                message = "*Inspector chat mode ON*\n"
                        + "Waiting for all inspectors to say if cars can be stopped. Type Yes/No";
                currentState = ProtocolStates.INSPECTORCHAT;
            }
        }
//            else {
//                message = "You're supposed to say \"Hello all\"! " +
//                        "Try again. Greet all other workers";
//            }

        else if(ProtocolStates.INSPECTORCHAT == currentState){
            if (userInput.equalsIgnoreCase("Yes")) {
                message = "All is clear from my side.";
                currentState = ProtocolStates.GIVENRESPONSE;
            } else if(userInput.equalsIgnoreCase("No")) {
                message = "Bad news inspection can't begin right now."
                        + "*Inspector chat mode OFF*";
                //currentState = ProtocolStates.GIVENRESPONSE;
                currentState = ProtocolStates.FREECHAT;
            }else {
                message = "You're supposed to say \"Yes or No\"! " +
                        "Try again. Can cars be stopped?";
            }
        }

        else if(ProtocolStates.GIVENRESPONSE == currentState){
            if (userInput.equalsIgnoreCase("Stop cars")) {
                message = "Cars are stopped. An inspection is carried out..."
                        + "*Inspector chat mode OFF*";
                currentState = ProtocolStates.FREECHAT;
            }else {
                message = "You're supposed to say \"Stop cars\"" +
                        "Try again.";
            }
        }

        return workerName + ": "+ message;
    }
}

enum ProtocolStates{
    INSPECTORCHAT,
    GIVENRESPONSE,
    FREECHAT

}