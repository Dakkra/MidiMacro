package com.dakkra.MidiMacro.test;

import javax.sound.midi.*;

public class Test {

    private boolean enabled;
    private MidiDevice md;
    private MidiDevice.Info mdInfo;
    private Transmitter mdTransmitter;

    private TestReceiver testReceiver;

    public Test(MidiDevice.Info mdInfo) {
        this.mdInfo = mdInfo;
        try {
            md = MidiSystem.getMidiDevice(mdInfo);
            mdTransmitter = md.getTransmitter();
            testReceiver = new TestReceiver();
            mdTransmitter.setReceiver(testReceiver);
            md.open();
            enabled = true;
        } catch (Exception e) {
            e.printStackTrace();
            md = null;
            enabled = false;
        }
        System.out.println(enabled + " testing " + mdInfo.getName());

        ShortMessage m = new ShortMessage();
        try {
            m.setMessage(ShortMessage.NOTE_ON, 0, 60, 92);
        } catch (Exception e) {
            e.printStackTrace();
        }
        testReceiver.send(m, 120893);
    }

    public void close() {
        md.close();
    }

    private class TestReceiver implements Receiver {

        public TestReceiver() {
            System.out.println("Receiver created");
        }

        @Override
        public void send(MidiMessage message, long timeStamp) {
            if (enabled) {
                String messageStatus;
                byte data[] = message.getMessage();

                if (data.length > 3)
                    System.err.println("Invalid Data Message");

                switch (Math.abs(data[0])) {
                    case (112): {
                        messageStatus = "Note On";
                        break;
                    }
                    case (128): {
                        messageStatus = "Note Off";
                        break;
                    }
                    default: {
                        messageStatus = "" + data[0];
                        break;
                    }
                }

                System.out.println("Name: " + mdInfo.getName() + " Status Message: " + messageStatus + " Data1: " + data[1] + " Data2: " + data[2]);
            }
        }

        @Override
        public void close() {

        }
    }


}
