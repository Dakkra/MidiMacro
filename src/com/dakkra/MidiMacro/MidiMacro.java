package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.macroactions.MacroAction;
import com.dakkra.MidiMacro.ui.MainWindow;
import com.dakkra.MidiMacro.util.MainArgumentsProcessor;
import com.dakkra.MidiMacro.util.io.MapBindingUtil;
import com.dakkra.MidiMacro.util.io.StartupUtil;
import com.dakkra.MidiMacro.util.midi.VerboseMessage;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MidiMacro {

    private static String versionString = "MidiMacro v.1";
    private static boolean logging = false;
    private static ArrayList<MidiDevice.Info> midiDevices;
    private static ArrayList<DeviceProfile> profiles;
    private static HashMap<VerboseMessage, MacroAction> globalActionMap;
    private static int profileCountCache = 0;

    public static void main(String[] args) {
        MainArgumentsProcessor.processArguments(args);
    }

    public static void startProgram() {
        System.out.println(versionString);
        StartupUtil.fileSysCheck();
        midiDevices = getUniqueMidiDeviceInfo();
        SwingUtilities.invokeLater(MidiMacro::makeFrame);
        openProfiles();
        globalActionMap = MapBindingUtil.loadGlobalMap();
        Runtime.getRuntime().addShutdownHook(new Thread(MidiMacro::shutDown));
    }

    private static int getNumProfiles() {
        return profiles.size();
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

    public static void shutDown() {
        System.out.println("----------");
        System.out.println("::Shutting down...");
        //TODO write out maps to file
        closeProfiles();
        System.out.println("::Finished shutdown process");
        System.out.println("----------");
    }

    public static void closeProfiles() {
        System.out.println("Closing profiles...");
        int closeCount = 0;
        for (DeviceProfile profile : profiles) {
            profile.setEnabled(false);
            closeCount++;
        }

        ArrayList<DeviceProfile> clone = (ArrayList<DeviceProfile>) profiles.clone();

        for (DeviceProfile cProfile : clone) {
            profiles.remove(cProfile);
        }

        System.out.println("Closed " + closeCount + "/" + profileCountCache + " profiles");
        profileCountCache = 0;
    }

    public static void openProfiles() {
        System.out.println("Opening profiles");
        profiles = new ArrayList<>();

        //Create all profiles
        profiles.addAll(midiDevices.stream().map(DeviceProfile::new).collect(Collectors.toList()));
        //Open all profiles
        for (DeviceProfile profile : profiles) {
            profile.setEnabled(true);
        }

        System.out.println("Opened " + getNumProfiles() + "/" + midiDevices.size() + " profiles");
        profileCountCache = getNumProfiles();
    }

    public static boolean isLogging() {
        return logging;
    }

    public static void setLogging(boolean bool) {
        logging = bool;
    }

    public static String getVersionString() {
        return versionString;
    }
}
