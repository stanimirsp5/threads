package ExamWorkshop.InspectorsOnBridge.Chat;

public class InspectorWorkProtocol {
    ProtocolStates currentState = ProtocolStates.NEWWORKER;

    public String processInput(String userInput,String workerName){
        String message = userInput;

        if(ProtocolStates.NEWWORKER == currentState){
            message = "New worker arrived. You must greet all other workers.";
            currentState = ProtocolStates.GREETING;
        }else if(ProtocolStates.GREETING == currentState){
            if (userInput.equalsIgnoreCase("Hello all")) {
                message = "Whats the work condition?";
                currentState = ProtocolStates.ROUTINE;
            } else {
                message = "You're supposed to say \"Hello all\"! " +
                        "Try again. Greet all other workers";
            }
        }else if(ProtocolStates.ROUTINE == currentState){
            if (userInput.equalsIgnoreCase("Great")) {
                message = "Good you can start work and communicate with other workers!";
                currentState = ProtocolStates.FREECHAT;
            } else {
                message = "You're supposed to say \"Great\"! " +
                        "Try again. Whats the work condition?";
            }
        }

        return workerName + ": "+ message;
    }
}

enum ProtocolStates{
    NEWWORKER,
    GREETING,
    ROUTINE,
    FREECHAT

}