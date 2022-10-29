package com.company;


import com.company.config.ComponentContainer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return ComponentContainer.NAME;
    }

    @Override
    public String getBotToken() {
        return ComponentContainer.TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("log --->>> " + update.getMessage().getText() + update.getMessage().getChatId());
        ComponentContainer.TELEGRAM_BOT_SERVICE.handleText(update);
    }

    public void send(Object object) {
        try {
            if (object instanceof SendMessage) {
                execute((SendMessage) object);
            } else if (object instanceof SendContact) {
                execute((SendContact) object);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
