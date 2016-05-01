package com.dakkra.MidiMacro.macroevents;


public abstract class MacroAction {

    protected MacroActionType eventType;

    public abstract void fireEvent();

    public MacroActionType getEventType() {
        return this.eventType;
    }

}
