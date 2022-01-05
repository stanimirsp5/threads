package ExamWorkshop.InspectorsOnBridge.Bridge;

import ExamWorkshop.InspectorsOnBridge.Chat.ProtocolStates;

public class StateContainer {

    public static ProtocolStates currentState = ProtocolStates.FREECHAT;

    private static StateContainer instance = null;
    private static Bridge bridgeInstance = null;

    private void StateContainer(){}

    public static StateContainer getInstance(Bridge bridge){
        if(instance==null){
            instance = new StateContainer();
            bridgeInstance = bridge;
        }
        return instance;
    }
    
    public static void updateBridge(){
        boolean isClosed = currentState == ProtocolStates.STOPCARMOVEMENT;
        bridgeInstance.update(isClosed);
    }
}
