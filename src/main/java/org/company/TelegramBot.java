package org.company;


import org.company.config.ComponentContainer;
import org.company.service.TelegramBotService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
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
        System.out.println("log --->>> " + update.getMessage().getText());
        ComponentContainer.TELEGRAM_BOT_SERVICE.handleText(update);
    }

    public void send(Object object) {
        try {
            if (object instanceof SendMessage) {
                execute((SendMessage) object);
            } else if (object instanceof SendContact) {
                execute((SendContact) object);
            } else if (object instanceof EditMessageText) {
                execute((EditMessageText) object);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
