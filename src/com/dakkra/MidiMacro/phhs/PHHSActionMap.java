package com.dakkra.MidiMacro.phhs;

import com.dakkra.MidiMacro.macroactions.*;
import com.dakkra.MidiMacro.util.keyboard.KeySequence;
import com.dakkra.MidiMacro.util.midi.MidiMessageStatus;
import com.dakkra.MidiMacro.util.midi.VerboseMessage;

import java.util.HashMap;

public class PHHSActionMap {

    public static HashMap<VerboseMessage, MacroAction> getActionMap() {
        HashMap<VerboseMessage, MacroAction> map = new HashMap<>();

        map.put(new VerboseMessage(new byte[]{MidiMessageStatus.CONTROL_CHANGE, (byte) 0, 127}), new MasterVolumeMacroAction());
        map.put(new VerboseMessage(new byte[]{MidiMessageStatus.CONTROL_CHANGE, (byte) 48, 127}), new PanicMacroAction());
        map.put(new VerboseMessage(new byte[]{MidiMessageStatus.NOTE_ON, (byte) 60, 127}), new SysCallMacroAction("notepad"));
        map.put(new VerboseMessage(new byte[]{MidiMessageStatus.NOTE_ON, (byte) 62, 127}), new SysCallMacroAction("vlc"));
        map.put(new VerboseMessage(new byte[]{MidiMessageStatus.NOTE_ON, (byte) 61, 127}), new KeyBindMacroAction(new KeySequence("phhs theatre production ")));

        return map;
    }

}
