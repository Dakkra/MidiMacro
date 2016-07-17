package com.dakkra.MidiMacro.ui;

import com.dakkra.MidiMacro.MidiMacro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private JPanel contentPanel = new JPanel();
    private JButton disableButton = new JButton("Disable");
    private JLabel titleLabel = new JLabel("Midi Macro");

    public MainWindow() {
        setTitle("Midi Macro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TODO get dimensions from user screen size
        setSize(new Dimension(200, 200));
        setResizable(true);
        setLocationRelativeTo(null);

        disableButton.addActionListener(new DisableButtonEar(disableButton));

        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 30));

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(disableButton, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
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
