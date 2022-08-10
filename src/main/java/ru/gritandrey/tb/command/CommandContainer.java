package ru.gritandrey.tb.command;

import org.springframework.stereotype.Component;
import ru.gritandrey.tb.service.SendBotMessageService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.gritandrey.tb.command.CommandName.CAT;
import static ru.gritandrey.tb.command.CommandName.START;

@Component
public class CommandContainer {
    private final Map<String, Command> commandMap = new ConcurrentHashMap<>();
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(CAT.getCommandName(), new CatCommand(sendBotMessageService));
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command getCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
