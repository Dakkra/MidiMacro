package com.dakkra.MidiMacro.util.midi;

import javax.sound.midi.MidiMessage;

public class VerboseMessage extends MidiMessage {

    private MidiMessageType messageType;
    private byte messageStatus;
    private byte firstByte;
    private byte secondByte;

    public VerboseMessage(byte[] data) {
        super(data);
        messageStatus = data[0];
        firstByte = data[1];
        secondByte = data[2];
        messageType = MidiMessageType.getMessageType(messageStatus);
    }

    public MidiMessageType getMessageType() {
        return messageType;
    }

    public byte getMessageStatus() {
        return messageStatus;
    }

    public byte getFirstByte() {
        return firstByte;
    }

    public byte getSecondByte() {
        return secondByte;
    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public int hashCode() {
        int sumOfBytes = messageStatus + firstByte;
        return (messageType.hashCode() + sumOfBytes);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == hashCode();
    }
}
