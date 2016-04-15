package com.dakkra.MidiMacro

import com.dakkra.MidiMacro.ui.MainWindow
import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiSystem
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    println("MidiMacro v.1")
    getDeviceInfo();
    SwingUtilities.invokeLater { startSwing() }
}

fun startSwing() {
    var mw = MainWindow();
}

fun getDeviceInfo(): Array<MidiDevice.Info> {
    var devicesInfo = MidiSystem.getMidiDeviceInfo();

    for (info: MidiDevice.Info in devicesInfo) {
        println("_______________")
        println(info.name)
        println(info.description)
        println("----------------")
    }
    return devicesInfo;
}