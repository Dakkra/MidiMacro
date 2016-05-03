package com.dakkra.MidiMacro.macroactions;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import ilarkesto.media.Audio;

import javax.sound.sampled.*;
import java.util.ArrayList;

public class MasterVolumeMacroAction extends MacroAction {

    private ArrayList<FloatControl> c = null;

    public MasterVolumeMacroAction() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            try {
                Mixer.Info mixerInfo[] = AudioSystem.getMixerInfo();
                Mixer mixer;
                Line l;

                for (Mixer.Info m : mixerInfo) {
                    if (m.getName().toLowerCase().contains("speaker")) {
                        System.out.println(m.getName());
                        mixer = AudioSystem.getMixer(m);
                        c = new ArrayList<>();
                        for (Line.Info li : mixer.getTargetLineInfo()) {
                            System.out.println(li.toString());
                            l = AudioSystem.getLine(li);
                            l.open();
                            c.add(Audio.getVolumeControl(l));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void fireAction(byte data) {
        float volume = (float) data / 127.0f;

        if (c != null) {
            for (FloatControl control : c) {
                control.setValue(volume);
            }
            System.out.println("C value set to " + volume);
        } else {
            if (data > 0) {
                Audio.setMasterOutputVolume(volume);
            } else if (data == 0) {
                Audio.setMasterOutputVolume(0f);
            }
        }
    }
}
