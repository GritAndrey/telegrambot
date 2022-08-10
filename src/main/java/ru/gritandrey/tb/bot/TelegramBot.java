package ru.gritandrey.tb.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.command.CommandContainer;
import ru.gritandrey.tb.service.SendBotMessageService;

import static ru.gritandrey.tb.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;

    public TelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageService(this));
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            final var message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.getCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.getCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
