package com.company.config;

import com.company.service.TelegramBotService;
import lombok.Data;
import com.company.TelegramBot;
import com.company.service.UserService;

@Data
public class ComponentContainer {

    public static final String NAME = "panda_cashbot";
    public static final String TOKEN = "5774652487:AAFPup9Dg-ihLoIZOAI564wgoJOc1IR30sc";
    public static final String ADMIN = "816267587";
    public static final String PATH = "/src/main/resources/serviceAccountKey.json";

    public static TelegramBot MY_TELEGRAM_BOT;

    public final static UserService USER_SERVICE = new UserService();
    public final static TelegramBotService TELEGRAM_BOT_SERVICE = new TelegramBotService();


}
