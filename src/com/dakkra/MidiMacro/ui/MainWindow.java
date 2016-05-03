package com.dakkra.MidiMacro.ui;

import com.dakkra.MidiMacro.MidiMacro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Midi Macro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 500));
        setResizable(false);
        setLocationRelativeTo(null);

        JButton disableButton = new JButton("Disable");
        disableButton.addActionListener(new DisableButtonEar(disableButton));

        add(disableButton, BorderLayout.CENTER);
    }

    class DisableButtonEar implements ActionListener {

        JButton button;

        public DisableButtonEar(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.getText().toLowerCase().equals("disable")) {
                MidiMacro.closeProfiles();
                button.setText("Enable");
            } else {
                MidiMacro.openProfiles();
                button.setText("Disable");
            }
        }
    }

}
