package ru.gritandrey.tb.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gritandrey.tb.bot.TelegramBot;

import java.io.File;

@RequiredArgsConstructor
@Service
public class SendBotMessageService implements MessageSender {

    private final TelegramBot telegramBot;

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO: 10.08.2022 log
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void sendImage(String chatId, File image) {
        var sendPhoto = new SendPhoto(String.valueOf(chatId), new InputFile(image));
        telegramBot.execute(sendPhoto);
    }
}
