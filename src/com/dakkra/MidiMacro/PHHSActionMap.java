package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.macroactions.MacroAction;
import com.dakkra.MidiMacro.macroactions.MasterVolumeMacroAction;
import com.dakkra.MidiMacro.util.midi.MidiMessageStatus;
import com.dakkra.MidiMacro.util.midi.VerboseMessage;

import java.util.HashMap;

public class PHHSActionMap {

    public static HashMap<VerboseMessage, MacroAction> getActionMap() {
        HashMap<VerboseMessage, MacroAction> map = new HashMap<>();

        map.put(new VerboseMessage(new byte[]{MidiMessageStatus.CONTROL_CHANGE, (byte) 0, 127}), new MasterVolumeMacroAction());

        return map;
    }

}
