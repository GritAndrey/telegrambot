package ru.gritandrey.tb.command;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import ru.gritandrey.tb.service.SendBotMessageService;

import static ru.gritandrey.tb.command.CommandName.*;

@Component
public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        unknownCommand = new UnknownCommand(sendBotMessageService);
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(CAT.getCommandName(), new CatCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();
    }

    public Command getCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
