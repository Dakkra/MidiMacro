package com.dakkra.MidiMacro.phhs;

import com.dakkra.MidiMacro.macroactions.MacroAction;

public class SelectWindowWithNameMacroAction extends MacroAction {

    String nameSubStr;

    public SelectWindowWithNameMacroAction(String nameSubStr) {
        this.nameSubStr = nameSubStr;
    }

    @Override
    public void fireAction(byte data) {

    }
}
