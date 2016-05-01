package com.dakkra.MidiMacro

import com.dakkra.MidiMacro.test.Test
import com.dakkra.MidiMacro.ui.MainWindow
import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiSystem
import javax.swing.SwingUtilities

var test: Test? = null;

fun main(args: Array<String>) {
    println("MidiMacro v.1")
    SwingUtilities.invokeLater { startSwing() }
    var devices = getDeviceInfo();

    var beDone = false;
    for (device in devices) {
        if (device.name.contains("nanoPAD2") && !beDone) {
            test = Test(device)
            beDone = true;
        }
    }

}

fun startSwing() {
    var mw = MainWindow();
}

fun getDeviceInfo(): Array<MidiDevice.Info> {
    var devicesInfo = MidiSystem.getMidiDeviceInfo();

    println("Getting Midi Devices: ")
    for (info: MidiDevice.Info in devicesInfo) {
        println("_______________")
        println(info.name)
        println(info.description)
        println("----------------")
    }
    println("Done getting devices")
    return devicesInfo;
}