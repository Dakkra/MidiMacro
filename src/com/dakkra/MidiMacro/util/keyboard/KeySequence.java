package com.dakkra.MidiMacro.util.keyboard;

import java.awt.*;
import java.util.ArrayList;

public class KeySequence {

    private ArrayList<VKContainer> keys;

    public KeySequence() {
        keys = new ArrayList<>();
    }

    public KeySequence(ArrayList<VKContainer> keys) {
        this.keys = keys;
    }

    public KeySequence(String charSequence) {
        this.keys = KeySequenceUtil.vkListFromCharString(charSequence);
    }

    public void addKey(VKContainer key) {
        keys.add(key);
    }

    public void removeKey(int index) {
        keys.remove(index);
    }

    public void RunKeys(Robot r) {
        for (VKContainer key : keys) {
            if (key.isKeyPressed()) {
                r.keyPress(key.getKey());
            } else {
                r.keyRelease(key.getKey());
            }
        }
    }

}
