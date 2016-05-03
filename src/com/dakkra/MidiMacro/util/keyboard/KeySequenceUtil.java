package com.dakkra.MidiMacro.util.keyboard;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeySequenceUtil {

    public static ArrayList<VKContainer> vkListFromCharString(String charString) {
        char characters[] = charString.toCharArray();
        ArrayList<VKContainer> vkList = new ArrayList<>();

        for (char c : characters) {
            //Press key
            vkList.add(new VKContainer(KeyEvent.getExtendedKeyCodeForChar(c), true));
            //Release key
            vkList.add(new VKContainer(KeyEvent.getExtendedKeyCodeForChar(c), false));
        }

        return vkList;
    }

}
