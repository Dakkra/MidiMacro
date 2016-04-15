package com.dakkra.MidiMacro.ui

import java.awt.Dimension
import java.awt.GraphicsEnvironment
import javax.swing.UIManager

fun getScreenDimensions(): Dimension {
    var screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
    return Dimension(screen.width, screen.height);
}

fun getLaf(): Array<UIManager.LookAndFeelInfo> {
    return UIManager.getInstalledLookAndFeels()
}