package com.dakkra.MidiMacro.util.midi;


public class MidiMessageStatus {
    public static final byte NOTE_OFF = (byte) 0x80;
    public static final byte NOTE_ON = (byte) 0x90;
    public static final byte AFTER_TOUCH = (byte) 0xA0;
    public static final byte CONTROL_CHANGE = (byte) 0xB0;
    public static final byte PROGRAM_CHANGE = (byte) 0xC0;
    public static final byte CHANNEL_PRESSURE = (byte) 0xD0;
    public static final byte PITCH_BEND = (byte) 0xE0;

}
