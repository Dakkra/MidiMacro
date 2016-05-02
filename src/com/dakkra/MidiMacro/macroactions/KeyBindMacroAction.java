package com.dakkra.MidiMacro.macroactions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyBindMacroAction extends MacroAction {

    public KeyBindMacroAction() {
        this.eventType = MacroActionType.KEYBIND;
    }

    @Override
    public void fireAction(byte data) {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_SHIFT);
            r.keyPress(KeyEvent.VK_C);
            r.keyRelease(KeyEvent.VK_C);
            r.keyRelease(KeyEvent.VK_SHIFT);
            r.keyPress(KeyEvent.VK_H);
            r.keyRelease(KeyEvent.VK_H);
            r.keyPress(KeyEvent.VK_R);
            r.keyRelease(KeyEvent.VK_R);
            r.keyPress(KeyEvent.VK_I);
            r.keyRelease(KeyEvent.VK_I);
            r.keyPress(KeyEvent.VK_S);
            r.keyRelease(KeyEvent.VK_S);
            r.keyPress(KeyEvent.VK_SPACE);
            r.keyRelease(KeyEvent.VK_SPACE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
