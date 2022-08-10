package ru.gritandrey.tb.service;

public interface MessageSender {
    void sendMessage(String chatId, String message);
}
