package com.dakkra.MidiMacro.ui

import com.dakkra.MidiMacro.getDeviceInfo
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import javax.swing.*

class MainWindow : JFrame {

    var screen_size: Dimension;

    constructor() {
        screen_size = getScreenDimensions()
        initWindow()
        addStatusBar()
        addMenuBar()
//        addLeftPane()
    }

    fun initWindow() {
        this.size = Dimension((screen_size.width / 1.25).toInt(), (screen_size.height / 1.25).toInt())
        this.title = "MidiMacro"
        this.layout = BorderLayout();
        this.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE;
        this.setLocationRelativeTo(null)
        this.isVisible = true;
        adjustSize();
    }

    fun adjustSize() {
    }

    fun addStatusBar() {
        var panel = JPanel();
        panel.add(JLabel("Welcome to MidiMacro"))
        panel.background = Color(170, 170, 170)
        this.add(panel, BorderLayout.SOUTH)
    }

    fun addMenuBar() {
        var menuBar = JMenuBar();
        menuBar.add(JMenu("Test"))
        this.jMenuBar = menuBar
    }

    fun addLeftPane() {
        var lPane = JPanel();
        lPane.preferredSize = Dimension(this.size.width / 5, this.size.height)
        lPane.background = Color(120, 120, 120)
        lPane.addComponentListener(ResizerListener(lPane))

        lPane.layout = FlowLayout(FlowLayout.CENTER)

        for (device in getDeviceInfo()) {
            lPane.add(JButton(device.name))
        }

        this.add(lPane, BorderLayout.WEST)
    }

    class ResizerListener : ComponentListener {

        var component: JComponent;

        constructor(component: JComponent) {
            this.component = component;
        }

        override fun componentMoved(e: ComponentEvent?) {
        }

        override fun componentResized(e: ComponentEvent?) {
            component.preferredSize = Dimension(component.parent.size.width / 5, component.parent.height)
        }

        override fun componentShown(e: ComponentEvent?) {
        }

        override fun componentHidden(e: ComponentEvent?) {
        }

    }
}