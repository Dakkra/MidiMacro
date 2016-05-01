package com.dakkra.MidiMacro.macroactions;


public abstract class MacroAction {

    protected MacroActionType eventType;

    public abstract void fireAction();

    public MacroActionType getActionType() {
        return this.eventType;
    }

}
