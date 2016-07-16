package com.dakkra.MidiMacro.util.io;

import java.io.File;
import java.nio.file.Files;

public class StartupUtil {

    private static String homeDirPath = System.getProperty("user.home");
    private static String saveDirPath = homeDirPath + "/.MidiMacro";
    private static File saveDir = new File(saveDirPath);

    public static void fileSysCheck() {
        System.out.println("::Performing save file check");
        if (!Files.exists(saveDir.toPath())) {
            System.out.println("|--Couldn't find save directory");
            saveDir.mkdir();
            System.out.println("|--Creating save directory in " + saveDirPath);
        } else {
            System.out.println("|--Found save directory");
            return;
        }
        if (!Files.exists(saveDir.toPath()))
            System.err.println("|--Error, save directory not created!");
    }

}
