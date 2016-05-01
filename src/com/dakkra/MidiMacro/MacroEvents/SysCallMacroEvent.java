package com.dakkra.MidiMacro.MacroEvents;

public class SysCallMacroEvent extends MacroEvent{

    public SysCallMacroEvent() {
        this.eventType = MacroEventType.SYSCALL;
    }

    @Override
    public void fireEvent() {
        //TODO fire event....
    }
}
