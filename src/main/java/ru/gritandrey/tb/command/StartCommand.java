package ru.gritandrey.tb.command;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.service.SendBotMessageService;

@RequiredArgsConstructor
public class StartCommand implements Command {
    private static final String START_MESSAGE = """
            This bot was created just for fun and learning purposes.
            """ + "\uD83D\uDE03";
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
