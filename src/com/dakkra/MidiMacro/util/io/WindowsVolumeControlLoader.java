package com.dakkra.MidiMacro.util.io;

import com.dakkra.MidiMacro.MidiMacro;

import java.net.URL;

public class WindowsVolumeControlLoader {

    public static String getVolumeControlURI() {
        String s = MidiMacro.class.getResource("blah").getPath();
        System.out.println(s);
        return null;
    }

}
