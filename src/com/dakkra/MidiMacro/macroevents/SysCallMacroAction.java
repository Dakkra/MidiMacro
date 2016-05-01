package com.dakkra.MidiMacro.macroevents;

public class SysCallMacroAction extends MacroAction {

    public SysCallMacroAction() {
        this.eventType = MacroActionType.SYSCALL;
    }

    @Override
    public void fireEvent() {
        //TODO fire event....
    }
}
