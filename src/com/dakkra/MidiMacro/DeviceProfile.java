package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.util.MidiMessageStatus;

import javax.sound.midi.*;

public class DeviceProfile {

    private boolean enabled = false;
    private MidiDevice midiDevice;
    private MidiDevice.Info midiDeviceInfo;
    private Transmitter midiDeviceTransmitter;
    private MessageEar messageMonitor;

    public DeviceProfile(MidiDevice.Info midiDeviceInfo) {
        this.midiDeviceInfo = midiDeviceInfo;
        messageMonitor = new MessageEar();
        try {
            this.midiDevice = MidiSystem.getMidiDevice(this.midiDeviceInfo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to get MidiDevice: " + midiDeviceInfo.getName());
        }

        try {
            this.midiDeviceTransmitter = midiDevice.getTransmitter();
            this.midiDeviceTransmitter.setReceiver(messageMonitor);
        } catch (Exception e) {
            System.out.println("Could not get transmitter for " + midiDeviceInfo.getName());
        }

    }

    public boolean isEnabled() {
        return enabled;
    }

    public MidiDevice.Info getInfo() {
        return midiDeviceInfo;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            try {
                if (!midiDevice.isOpen()) {
                    midiDevice.open();
                    System.out.println("Opened Device " + midiDeviceInfo.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (midiDevice.isOpen()) {
                    midiDevice.close();
                    System.out.println("Closed Device " + midiDeviceInfo.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.enabled = enabled;
    }

    private void assessMessage(MidiMessage message) {
        String messageType;
        byte data[] = message.getMessage();

        switch (data[0]) {
            case MidiMessageStatus.NOTE_ON: {
                //Note on events here
                messageType = "Note On";
                break;
            }
            default: {
                messageType = "" + data[0];
            }
        }

        System.out.println("Device::" + midiDeviceInfo.getName() + " Message Type::" + messageType + " Data1::" + data[1] + " Data2::" + data[2]);
    }


    private class MessageEar implements Receiver {

        @Override
        public void send(MidiMessage message, long timeStamp) {
            assessMessage(message);
        }

        @Override
        public void close() {
        }
    }
}
