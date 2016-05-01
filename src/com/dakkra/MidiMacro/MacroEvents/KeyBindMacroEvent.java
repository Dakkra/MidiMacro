package com.dakkra.MidiMacro.MacroEvents;

public class KeyBindMacroEvent extends MacroEvent {

    public KeyBindMacroEvent() {
        this.eventType = MacroEventType.KEYBIND;
    }

    @Override
    public void fireEvent() {

    }
}
