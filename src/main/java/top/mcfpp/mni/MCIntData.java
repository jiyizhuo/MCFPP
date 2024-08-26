package top.mcfpp.mni;

import top.mcfpp.annotations.MNIRegister;
import top.mcfpp.var.lang.JsonTextConcrete;
import top.mcfpp.var.lang.MCInt;
import top.mcfpp.lib.ListChatComponent;
import top.mcfpp.lib.ScoreChatComponent;
import top.mcfpp.util.ValueWrapper;

public class MCIntData {

    @MNIRegister(caller = "int", returnType = "text", override = true)
    public static void toText(MCInt caller, ValueWrapper<JsonTextConcrete> returnValue) {
        var l = new ListChatComponent();
        l.getComponents().add(new ScoreChatComponent(caller));
        returnValue.setValue(new JsonTextConcrete(l, "re"));
    }
}
