package com.dakkra.MidiMacro.macroactions;

import java.util.ArrayList;

public class HybridMacroAction extends MacroAction {

    private ArrayList<MacroAction> actions;

    public HybridMacroAction() {
        this(new ArrayList<>());
    }

    public HybridMacroAction(ArrayList<MacroAction> actions) {
        this.actions = actions;
    }

    public void addAction(MacroAction action) {
        this.eventType = MacroActionType.HYBRID;
        actions.add(action);
    }

    @Override
    public void fireAction(byte data) {

        for (MacroAction a : actions) {
            a.fireAction(data);
        }

    }
}
