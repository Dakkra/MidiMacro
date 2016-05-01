package com.dakkra.MidiMacro;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import java.util.List;

public class DeviceListener implements MidiDevice {

    private MidiDevice client;
    private MidiDevice.Info clientInfo;
    private Info listenerInfo;


    public DeviceListener(MidiDevice client) {
        this.client = client;
        this.clientInfo = client.getDeviceInfo();
        this.listenerInfo = new DeviceListenerInfo(clientInfo.getName() + " LISTENER", "MidiMacro", "Device listener for " + clientInfo.getName(), "I");
    }

    @Override
    public Info getDeviceInfo() {
        return this.listenerInfo;
    }

    @Override
    public void open() throws MidiUnavailableException {
        //open listener
    }

    @Override
    public void close() {
        //close listener
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public long getMicrosecondPosition() {
        return 0;
    }

    @Override
    public int getMaxReceivers() {
        return 1;
    }

    @Override
    public int getMaxTransmitters() {
        return 0;
    }

    @Override
    public Receiver getReceiver() throws MidiUnavailableException {
        return null;
    }

    @Override
    public List<Receiver> getReceivers() {
        return null;
    }

    @Override
    public Transmitter getTransmitter() throws MidiUnavailableException {
        return null;
    }

    @Override
    public List<Transmitter> getTransmitters() {
        return null;
    }

    class DeviceListenerInfo extends MidiDevice.Info {
        public DeviceListenerInfo(String name, String vendor, String description, String version) {
            super(name, vendor, description, version);
        }
    }
}