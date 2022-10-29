package org.company.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;

public class KeyboardButtonUtil {

    public static ReplyKeyboardMarkup shareContactUZ() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardButton btn1 = new KeyboardButton("\uD83D\uDCF1 Kontakt ulashish");
        btn1.setRequestContact(true);
        KeyboardButton btn2 = new KeyboardButton("\uD83D\uDD04 Qayta boshlash");
        KeyboardButton btn3 = new KeyboardButton("\uD83D\uDCE8 Izoh qoldirish");
        KeyboardRow row1 = new KeyboardRow();
        row1.add(btn1);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(btn2);
        row2.add(btn3);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(Arrays.asList(row1, row2));
        return replyKeyboardMarkup;
    }

}
