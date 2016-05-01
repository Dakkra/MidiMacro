package com.dakkra.MidiMacro.MacroEvents;


public abstract class MacroAction {

    protected MacroActionType eventType;

    public abstract void fireEvent();

    public MacroActionType getEventType() {
        return this.eventType;
    }

}
