package com.dakkra.MidiMacro;

import javax.sound.midi.*;
import java.util.List;

public class DeviceListener implements MidiDevice {

    private boolean active;
    private MidiDevice client;
    private MidiDevice.Info clientInfo;
    private DeviceListenerReceiver receiver;
    private Info listenerInfo;


    public DeviceListener(MidiDevice client) {
        this.active = true;
        this.client = client;
        this.clientInfo = client.getDeviceInfo();
        this.listenerInfo = new DeviceListenerInfo(clientInfo.getName() + " LISTENER", "MidiMacro", "Device listener for " + clientInfo.getName(), "I");
        this.receiver = new DeviceListenerReceiver();
    }

    @Override
    public Info getDeviceInfo() {
        return this.listenerInfo;
    }

    @Override
    public void open() throws MidiUnavailableException {
        //TODO open listener
        this.active = true;
    }

    @Override
    public void close() {
        //TODO close listener
        receiver.close();
        this.active = false;
    }

    @Override
    public boolean isOpen() {
        return this.active;
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

    class DeviceListenerReceiver implements Receiver {

        private boolean isActive = true;

        @Override
        public void send(MidiMessage message, long timeStamp) {
        }

        @Override
        public void close() {
            if (isActive) {
                isActive = false;
            }
        }
    }
}