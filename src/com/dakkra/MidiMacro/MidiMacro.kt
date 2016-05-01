package com.dakkra.MidiMacro

import com.dakkra.MidiMacro.ui.MainWindow
import java.util.*
import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiSystem
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    println("MidiMacro v.1")
    getDeviceInfo();
    SwingUtilities.invokeLater { startSwing() }
    var devices = getDeviceInfo();
    var profiles: ArrayList<DeviceProfile> = ArrayList();

    for (device in devices) {
        profiles.add(DeviceProfile(MidiSystem.getMidiDevice(device)));
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