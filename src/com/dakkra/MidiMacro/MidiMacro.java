package com.dakkra.MidiMacro;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MidiMacro {
    public static void main(String[] args) {
        System.out.println("MidiMacro v.1");
        SwingUtilities.invokeLater(MidiMacro::makeFrame);
        getUniqueMidiDeviceInfo();
    }

    private static void makeFrame() {
        JFrame frame = new JFrame("Midi Macro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private static ArrayList<MidiDevice.Info> getUniqueMidiDeviceInfo() {
        MidiDevice.Info info[] = MidiSystem.getMidiDeviceInfo();
        ArrayList<MidiDevice.Info> uniqueInfo = new ArrayList<>();

        System.out.println("Getting unique Midi devices");

        for (MidiDevice.Info current : info) {
            //If there's no data yet, add the first entry
            if (uniqueInfo.size() == 0) {
                uniqueInfo.add(current);
            } else {
                //if there is data, compare it to make sure it's unique
                boolean shouldAdd = true;
                for (int i = 0; i < uniqueInfo.size(); i++) {
                    if (uniqueInfo.get(i).getName().equals(current.getName())) {
                        shouldAdd = false;
                    }
                }
                if (shouldAdd)
                    uniqueInfo.add(current);
            }
        }

        for (int i = 0; i < uniqueInfo.size(); i++) {
            System.out.println(uniqueInfo.get(i).getDescription());
        }

        System.out.println("Done getting unique Midi devices");
        return uniqueInfo;
    }

}
