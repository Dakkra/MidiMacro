package com.dakkra.MidiMacro.macroactions;

import ilarkesto.media.Audio;

public class MasterVolumeMacroAction extends MacroAction {

    private boolean onWindows = System.getProperty("os.name").toLowerCase().contains("windows");


    public MasterVolumeMacroAction() {

    }

    @Override
    public void fireAction(byte data) {
        float volume = (float) data / 127.0f;

        if (onWindows) {
            try {
                Runtime.getRuntime().exec("C:\\Users\\amazo\\Bin\\VolumeSetterNative.exe -f " + volume);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (data > 0) {
                Audio.setMasterOutputVolume(volume);
            } else if (data == 0) {
                Audio.setMasterOutputVolume(0f);
            }
        }
    }
}
