package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.MacroEvents.MacroAction;

import javax.sound.midi.*;
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
            this.eventMap = loadEventMap();
            this.listenerDevice = new DeviceListener(midiDevice, eventMap);
            this.listenerReceiver = listenerDevice.getReceiver();
            if (this.midiDevice.getMaxReceivers() > 0) {
                this.receiver = this.midiDevice.getReceiver();
            }
            if (this.midiDevice.getMaxTransmitters() > 0) {
                this.transmitter = this.midiDevice.getTransmitter();
                transmitter.setReceiver(listenerReceiver);
                ShortMessage x = new ShortMessage();
                x.setMessage(ShortMessage.NOTE_ON, 0, 60, 92);
            }
            isEnabled = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.listenerDevice.close();
            System.err.println("Failed to open device: " + deviceId);
            isEnabled = false;
        }

        System.out.println("Created profile for: " + deviceInfo.getName());
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
