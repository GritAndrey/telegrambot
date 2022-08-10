package ru.gritandrey.tb.command;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.service.SendBotMessageService;

@RequiredArgsConstructor
public class NoCommand implements Command {

    private static final String NO_MESSAGE = """
            "I support commands starting with a slash(/)."
             To view a list of commands, type /help
            """;
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
