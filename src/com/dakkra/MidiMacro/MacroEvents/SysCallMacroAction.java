package com.dakkra.MidiMacro.MacroEvents;

public class SysCallMacroAction extends MacroAction {

    public SysCallMacroAction() {
        this.eventType = MacroActionType.SYSCALL;
    }

    @Override
    public void fireEvent() {
        //TODO fire event....
    }
}
