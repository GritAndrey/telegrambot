package ru.gritandrey.tb.command;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gritandrey.tb.service.SendBotMessageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

@RequiredArgsConstructor
public class CatCommand implements Command {
    public static final String CATS_URL = "https://cataas.com/cat/says/%D0%A2%D1%80%D1%8F%D0%BC!";
    private final SendBotMessageService sendBotMessageService;

    @SneakyThrows
    @Override
    public void execute(Update update) {
        URL url = new URL(CATS_URL);
        BufferedImage img = ImageIO.read(url);
        File imgFile = new File("_cat_temp.jpg");
        ImageIO.write(img, "jpg", imgFile);
        sendBotMessageService.sendImage(update.getMessage().getChatId().toString(),imgFile);
    }
}
