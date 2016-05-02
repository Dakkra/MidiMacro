package com.dakkra.MidiMacro.macroactions;

public class SysCallMacroAction extends MacroAction {

    private String command;

    public SysCallMacroAction() {
        this.eventType = MacroActionType.SYSCALL;
        command = "";
    }
    
    public SysCallMacroAction(String command) {
        this();
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void fireAction() {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
