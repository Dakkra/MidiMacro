package com.dakkra.MidiMacro.util;

import com.dakkra.MidiMacro.MidiMacro;

public class MainArgumentsProcessor {

    public static void processArguments(String[] args) {
        if (args.length == 0) {
            MidiMacro.startProgram();
            return;
        }
        for (String argument : args) {
            switch (argument) {
                case "--logging": {
                    MidiMacro.setLogging(true);
                    MidiMacro.startProgram();
                    break;
                }
                case "-l": {
                    MidiMacro.setLogging(true);
                    MidiMacro.startProgram();
                    break;
                }
                case "--help": {
                    printHelpList();
                    break;
                }
                case "-h": {
                    printHelpList();
                    break;
                }
                case "--version": {
                    printVersion();
                    break;
                }
                case "-v": {
                    printVersion();
                    break;
                }
                default: {
                    MidiMacro.startProgram();
                    break;
                }
            }
        }
    }

    private static void printHelpList() {
        System.out.println("Midi Macro Help List");
        System.out.println("|");
        System.out.println("--help    || -h    Prints help list");
        System.out.println("--logging || -l    Verbose Logging");
        System.out.println("--version || -v    Prints version");
    }

    private static void printVersion() {
        System.out.println(MidiMacro.getVersionString());
    }

}
