package ru.gritandrey.tb.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.service.SendBotMessageService;

@RequiredArgsConstructor
public class UnknownCommand implements Command {
    private static final String UNKNOWN_MESSAGE = """
            Unknown command.
            """;
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
