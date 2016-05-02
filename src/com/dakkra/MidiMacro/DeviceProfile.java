package com.dakkra.MidiMacro;

import com.dakkra.MidiMacro.macroactions.KeyBindMacroAction;
import com.dakkra.MidiMacro.macroactions.MacroAction;
import com.dakkra.MidiMacro.macroactions.MasterVolumeMacroAction;
import com.dakkra.MidiMacro.macroactions.SysCallMacroAction;
import com.dakkra.MidiMacro.util.midi.MidiMessageStatus;
import com.dakkra.MidiMacro.util.midi.MidiMessageType;
import com.dakkra.MidiMacro.util.midi.VerboseMessage;

import javax.sound.midi.*;
import java.util.HashMap;

public class DeviceProfile {

    private boolean enabled = false;
    private MidiDevice midiDevice;
    private MidiDevice.Info midiDeviceInfo;
    private Transmitter midiDeviceTransmitter;
    private MessageEar messageMonitor;
    private HashMap<VerboseMessage, MacroAction> actionMap = new HashMap<>();

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

        //Test hash map for use in mapping Messages and Actions
        actionMap.put(new VerboseMessage(new byte[]{MidiMessageStatus.NOTE_ON, (byte) 60, 127}), new SysCallMacroAction("mousepad"));
        actionMap.put(new VerboseMessage(new byte[]{MidiMessageStatus.NOTE_ON, (byte) 61, 127}), new KeyBindMacroAction());
        actionMap.put(new VerboseMessage(new byte[]{MidiMessageStatus.CONTROL_CHANGE, (byte) 0, 127}), new MasterVolumeMacroAction());
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

    private void doHashAction(VerboseMessage message) {
        MacroAction action = actionMap.get(message);
        if (action != null)
            action.fireAction(message.getValueByte());
    }

    private void assessMessage(VerboseMessage message) {
        MidiMessageType type = message.getMessageType();
        switch (type) {
            case NOTE_ON: {
                doHashAction(message);
                break;
            }
            case NOTE_OFF: {
                doHashAction(message);
                break;
            }
            case PERCENT: {
                doHashAction(message);
                break;
            }
            case PITCHBEND: {
                doHashAction(message);
                break;
            }
        }

        System.out.println("Device::" + midiDeviceInfo.getName() + " MessageType::" + type.getTypeName() + " " + type.getFirstByteType() + " " + message.getTargetByte() + " " + type.getSecondByteType() + " " + message.getValueByte());
    }


    private class MessageEar implements Receiver {

        @Override
        public void send(MidiMessage message, long timeStamp) {
            assessMessage(new VerboseMessage(message.getMessage()));
        }

        @Override
        public void close() {
        }
    }
}
