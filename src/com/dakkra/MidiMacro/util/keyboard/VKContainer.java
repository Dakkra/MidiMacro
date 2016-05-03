package com.dakkra.MidiMacro.util.keyboard;

public class VKContainer {

    private final int key;
    private final boolean isKeyPressed;

    public VKContainer(int key, boolean isKeyPressed) {
        this.key = key;
        this.isKeyPressed = isKeyPressed;
    }

    public int getKey() {
        return key;
    }

    public boolean isKeyPressed() {
        return isKeyPressed;
    }

}
