package ru.gritandrey.tb.command;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.service.SendBotMessageService;

@RequiredArgsConstructor
public class StopCommand implements Command {
    private static final String STOP_MESSAGE = """
            Ok, ok. All processes stopped.
            """ + "\uD83D\uDE03";
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
