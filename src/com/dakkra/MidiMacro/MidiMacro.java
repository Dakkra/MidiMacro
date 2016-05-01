package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.ui.MainWindow;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.swing.*;
import java.util.ArrayList;

public class MidiMacro {

    private static ArrayList<MidiDevice.Info> midiDevices;
    private static ArrayList<DeviceProfile> profiles;

    public static void main(String[] args) {
        System.out.println("MidiMacro v.1");
        midiDevices = getUniqueMidiDeviceInfo();
        profiles = new ArrayList<>();
        SwingUtilities.invokeLater(MidiMacro::makeFrame);

        //Create all profiles
        for (MidiDevice.Info device : midiDevices) {
            profiles.add(new DeviceProfile(device));
        }

        //Open all profiles
        for (DeviceProfile profile : profiles) {
            profile.setEnabled(true);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(MidiMacro::shutDown));
    }

    private static void makeFrame() {
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
    }

    private static ArrayList<MidiDevice.Info> getUniqueMidiDeviceInfo() {
        MidiDevice.Info info[] = MidiSystem.getMidiDeviceInfo();
        ArrayList<MidiDevice.Info> uniqueInfo = new ArrayList<>();

        System.out.println("----------");
        System.out.println("::Getting unique Midi devices::");

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
            System.out.println("|-" + uniqueInfo.get(i).getName() + " from " + uniqueInfo.get(i).getVendor());
        }

        System.out.println("::Done getting unique Midi devices::");
        System.out.println("----------");
        return uniqueInfo;
    }

    private static void shutDown() {
        System.out.println("Shutting down...");
        System.out.println("Closing profiles...");
        for (DeviceProfile profile : profiles) {
            profile.setEnabled(false);
        }
        //TODO write out maps to file
        System.out.println("Finished shutdown process");
    }

}
