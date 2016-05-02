package com.dakkra.MidiMacro.macroactions;

import ilarkesto.media.Audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;

public class MasterVolumeMacroAction extends MacroAction {

    public MasterVolumeMacroAction() {
    }

    @Override
    public void fireAction(byte data) {
        if (data > 0) {
            float volume = (float) data / 127.0f;
            System.out.println("Volume: " + volume);
            Audio.setMasterOutputVolume(volume);
        } else if (data == 0) {
            Audio.setMasterOutputVolume(0f);
        }
    }
}
