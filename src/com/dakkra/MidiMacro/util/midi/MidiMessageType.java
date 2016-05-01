package com.dakkra.MidiMacro.util.midi;


public enum MidiMessageType {
    PERCENT("Percent", new byte[]{(byte) 0xB0}, "ParameterID", "Value"),
    NOTE_OFF("Note Off", new byte[]{(byte) 0x90}, "NoteID", "Velocity"),
    NOTE_ON("Note On", new byte[]{(byte) 0x90}, "NoteID", "Velocity"),
    PITCHBEND("Pitchbend", new byte[]{(byte) 0xE0}, "LSB", "MSB");

    final String typeName;
    final String firstByteType;
    final String secondByteType;
    final byte typeVal[];

    MidiMessageType(String typeName, byte typeVal[], String firstByteType, String secondByteType) {
        this.typeName = typeName;
        this.typeVal = typeVal;
        this.firstByteType = firstByteType;
        this.secondByteType = secondByteType;
    }

    public static MidiMessageType getMessageType(byte val) {
        switch (val) {
            case MidiMessageStatus.NOTE_ON: {
                return MidiMessageType.NOTE_ON;
            }
            case MidiMessageStatus.NOTE_OFF: {
                return MidiMessageType.NOTE_OFF;
            }
            case MidiMessageStatus.PITCH_BEND: {
                return MidiMessageType.PITCHBEND;
            }
            case MidiMessageStatus.CONTROL_CHANGE: {
                return MidiMessageType.PERCENT;
            }
            default: {
                return null;
            }
        }
    }

    public String getFirstByteType() {
        return firstByteType;
    }

    public String getSecondByteType() {
        return secondByteType;
    }

    public String getTypeName() {
        return typeName;
    }

    public byte[] getTypeVal() {
        return typeVal;
    }

    public boolean hasValue(byte value) {
        for (byte val : typeVal) {
            if (val == value) {
                return true;
            }
        }
        return false;
    }


}
