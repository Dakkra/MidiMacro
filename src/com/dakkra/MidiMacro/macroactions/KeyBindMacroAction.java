package com.dakkra.MidiMacro.macroactions;

import com.dakkra.MidiMacro.util.keyboard.KeySequence;

import java.awt.*;

public class KeyBindMacroAction extends MacroAction {

    KeySequence seq;

    public KeyBindMacroAction(KeySequence seq) {
        this.eventType = MacroActionType.KEYBIND;
        this.seq = seq;
    }

    @Override
    public void fireAction(byte data) {
        try {
            Robot r = new Robot();
            seq.RunKeys(r);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
