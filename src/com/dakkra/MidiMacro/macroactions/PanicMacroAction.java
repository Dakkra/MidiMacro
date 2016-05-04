package com.dakkra.MidiMacro.macroactions;

import ilarkesto.media.Audio;

public class PanicMacroAction extends MacroAction {

    private boolean onWindows = System.getProperty("os.name").toLowerCase().contains("windows");

    @Override
    public void fireAction(byte data) {
        if (onWindows) {
            try {
                Runtime.getRuntime().exec("C:\\Users\\amazo\\Bin\\VolumeSetterNative.exe -f " + 0f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Audio.setMasterOutputVolume(0f);
        }
    }
}
