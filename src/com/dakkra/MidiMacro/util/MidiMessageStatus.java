package com.dakkra.MidiMacro.util;


public enum MidiMessageStatus {
    NOTE_OFF("Note off", (byte) 0x80),
    NOTE_ONE("Note on", (byte) 0x90),
    AFTER_TOUCH("After touch", (byte) 0xA0),
    CONTROL_CHANGE("Control Change", (byte) 0xB0),
    PROGRAM_CHANGE("Program Change", (byte) 0xC0),
    CHANNEL_PRESSURE("Channel Pressure", (byte) 0xD0),
    PITCH_BEND("Pitch Bend", (byte) 0xE0);

    String name;
    byte value;

    MidiMessageStatus(String name, byte value) {
        this.name = name;
        this.value = value;
    }
}
