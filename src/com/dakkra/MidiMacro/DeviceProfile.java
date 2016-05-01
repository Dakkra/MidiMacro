package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.MacroEvents.MacroAction;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import java.util.HashMap;

public class DeviceProfile {

    private String deviceId;
    private MidiDevice midiDevice;
    private DeviceListener listenerDevice;
    private MidiDevice.Info deviceInfo;
    private Receiver receiver;
    private Transmitter transmitter;
    private Receiver listenerReceiver;
    private HashMap<MidiEvent, MacroAction> eventMap;
    private boolean isEnabled;

    public DeviceProfile(MidiDevice midiDevice) {
        this.midiDevice = midiDevice;
        this.deviceInfo = this.midiDevice.getDeviceInfo();
        deviceId = this.deviceInfo.getName();
        try {
            midiDevice.open();
            this.receiver = this.midiDevice.getReceiver();
            this.transmitter = this.midiDevice.getTransmitter();
            this.listenerDevice = new DeviceListener(midiDevice, eventMap);
            this.listenerReceiver = listenerDevice.getReceiver();
            this.eventMap = loadEventMap();
            transmitter.setReceiver(listenerReceiver);
            isEnabled = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.listenerDevice.close();
            System.err.println("Failed to open device: " + deviceId);
            isEnabled = false;
        }

    }

    public boolean getEnabled() {
        return isEnabled;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public HashMap<MidiEvent, MacroAction> loadEventMap() {
        return new HashMap<MidiEvent, MacroAction>();
    }

}
