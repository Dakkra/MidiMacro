package com.dakkra.MidiMacro.util.midi;

import javax.sound.midi.MidiMessage;

public class VerboseMessage extends MidiMessage {

    private MidiMessageType messageType;
    private byte messageStatus;
    private byte targetByte;
    private byte valueByte;

    public VerboseMessage(byte[] data) {
        super(data);
        messageStatus = data[0];
        targetByte = data[1];
        valueByte = data[2];
        messageType = MidiMessageType.getMessageType(messageStatus);
    }

    public MidiMessageType getMessageType() {
        return messageType;
    }

    public byte getMessageStatus() {
        return messageStatus;
    }

    public byte getTargetByte() {
        return targetByte;
    }

    public byte getValueByte() {
        return valueByte;
    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public int hashCode() {
        int sumOfBytes = messageStatus + targetByte;
        return (messageType.hashCode() + sumOfBytes);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == hashCode();
    }
}
