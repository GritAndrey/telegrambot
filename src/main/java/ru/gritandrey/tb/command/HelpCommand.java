package ru.gritandrey.tb.command;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.service.SendBotMessageService;

import static ru.gritandrey.tb.command.CommandName.*;

@RequiredArgsConstructor
public class HelpCommand implements Command {
    private static final String HELP_MESSAGE = """
            ✨<b>Available Commands:</b>✨
            %s
            %s
            %s
            %s
            """.formatted(
            START.getCommandName(),
            STOP.getCommandName(),
            CAT.getCommandName(),
            HELP.getCommandName());
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
