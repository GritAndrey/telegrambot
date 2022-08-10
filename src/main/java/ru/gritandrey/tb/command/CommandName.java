package ru.gritandrey.tb.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    CAT("/cat"),
    HELP("/help"),
    NO("");
    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
