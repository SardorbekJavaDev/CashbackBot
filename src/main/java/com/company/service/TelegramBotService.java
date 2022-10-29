package com.company.service;


import com.company.config.ComponentContainer;
import com.company.util.KeyboardButtonUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TelegramBotService {


    public void handleText(Update update) {

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        long chatId = update.getMessage().getChatId();
        sendMessage.setChatId(String.valueOf(chatId));

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messagetext = update.getMessage().getText();
            String name = update.getMessage().getChat().getFirstName();

            if (messagetext.equals("/start") || messagetext.equals("\uD83D\uDD04 Qayta boshlash")) {
                startCommandReceived(chatId, name);
            } else if (messagetext.equals("\uD83D\uDCE8 Izoh qoldirish")) {
                sendMessage.setText("Taklif va shikoyatlar qoldirishingiz mumkin.\nHabarni bir martada yozishga harakat qiling !\nHabaringiz: \"Izoh\" kalit so'zi bilan boshlanishi lozim.");
                ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
            } else if (messagetext.startsWith("Izoh")) {
                sendToAdmin(message);
                sendMessage.setText("Xabar muvaffaqiyatli yuborildi !");
                ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
            } else if (messagetext.startsWith("+998") && messagetext.length() == 13) {
                ComponentContainer.USER_SERVICE.getByFireStoreDB(messagetext, chatId);
                sendMessage.setText("Kuting...");
                ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
                System.out.println("test---------------------" + messagetext);
            } else {
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setText("Mavjud emas !");
                ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
            }
        } else if (message.hasContact()) {
            Contact contact = message.getContact();
            String number = "+" + contact.getPhoneNumber();
            ComponentContainer.USER_SERVICE.getByFireStoreDB(number, chatId);
            sendMessage.setText("Kuting...");
            ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
            System.out.println("test---------------------" + number);
        }
    }

    private void startCommandReceived(long chatId, String name) {
        String answer = "Salom " + name + ". \"Kontakt ulashish\" tugmasini bosish yoki namunadagidek raqamni yuborish orqali cashbacklaringizni bilib oling ! \nNamuna: +998991112233";
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);
        sendMessage.setReplyMarkup(KeyboardButtonUtil.shareContactUZ());
        ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
    }


    private void sendToAdmin(Message message) {
        User user = message.getFrom();
        SimpleDateFormat format4 = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(ComponentContainer.ADMIN);
        sendMessage.setText("Sizga " + "[" + user.getFirstName() + "](tg://user?id=" + user.getId() + ")" + " dan yangi habar qoldirildi.\nHabar qoldirilgan vaqt: "
                + format4.format(new Date()) + "\nHabar matni: " + message.getText().substring(4)
        );
        sendMessage.enableMarkdown(true);
        ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
    }

    public void sendResult(String value, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        System.out.println("test test test test test " + value);
        sendMessage.setText("Marhamat sizning cashbackingiz !\nCashback - " + value + "  \uD83D\uDCB0");
        ComponentContainer.MY_TELEGRAM_BOT.send(sendMessage);
    }
}
