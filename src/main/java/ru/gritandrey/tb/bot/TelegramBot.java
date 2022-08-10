package ru.gritandrey.tb.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.command.CommandContainer;
import ru.gritandrey.tb.service.SendBotMessageService;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    private final SendBotMessageService sendBotMessageService;
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;

    public TelegramBot() {
        final var sendBotMessageService = new SendBotMessageService(this);
        this.sendBotMessageService = sendBotMessageService;
        this.commandContainer = new CommandContainer(sendBotMessageService);
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
            final var chatId = update.getMessage().getChatId().toString();
            final var userName = update.getMessage().getChat().getUserName();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.getCommand(commandIdentifier).execute(update);
            } else {
                sendBotMessageService.sendMessage(chatId, userName + ":" + message);
            }

        }
    }
}
