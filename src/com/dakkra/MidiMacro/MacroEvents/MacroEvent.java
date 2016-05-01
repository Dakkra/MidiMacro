package com.dakkra.MidiMacro.MacroEvents;


public abstract class MacroEvent {

    protected MacroEventType eventType;

    public abstract void fireEvent();

    public MacroEventType getEventType() {
        return this.eventType;
    }

}
