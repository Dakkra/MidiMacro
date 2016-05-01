package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.MacroEvents.MacroAction;

import javax.sound.midi.*;
import java.util.HashMap;
import java.util.List;

public class DeviceListener implements MidiDevice {

    private boolean active;
    private MidiDevice client;
    private MidiDevice.Info clientInfo;
    private DeviceListenerReceiver receiver;
    private Info listenerInfo;
    private HashMap<MidiEvent, MacroAction> eventMap;


    public DeviceListener(MidiDevice client, HashMap<MidiEvent, MacroAction> eventMap) {
        this.active = true;
        this.client = client;
        this.clientInfo = client.getDeviceInfo();
        this.listenerInfo = new DeviceListenerInfo(clientInfo.getName() + " LISTENER", "MidiMacro", "Device listener for " + clientInfo.getName(), "I");
        this.receiver = new DeviceListenerReceiver();
        this.eventMap = eventMap;
        System.out.println("Created DeviceListener for: " + clientInfo.getName());
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
//            if (isActive) {
//                assessEvent(message);
//            }
            assessEvent(message);
        }

        @Override
        public void close() {
            if (isActive) {
                isActive = false;
            }
        }
    }

    private void assessEvent(MidiMessage message) {
        System.out.println("Name: " + clientInfo.getName() + " Message: " + message.getMessage());

    }
}