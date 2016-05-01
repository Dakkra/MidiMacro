package com.dakkra.MidiMacro;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class DeviceProfile {

    private String deviceId;
    private MidiDevice midiDevice;
    private DeviceListener listenerDevice;
    private MidiDevice.Info deviceInfo;
    private Receiver receiver;
    private Transmitter transmitter;
    private boolean isEnabled;

    public DeviceProfile(MidiDevice midiDevice) {
        this.midiDevice = midiDevice;
        this.deviceInfo = this.midiDevice.getDeviceInfo();
        deviceId = this.deviceInfo.getName();
        try {
            midiDevice.open();
            isEnabled = true;
            this.receiver = this.midiDevice.getReceiver();
            this.transmitter = this.midiDevice.getTransmitter();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to open/receive device: " + deviceId);
            isEnabled = false;
        }

    }

    public boolean getEnabled() {
        return isEnabled;
    }

    public String getDeviceId() {
        return deviceId;
    }

}
