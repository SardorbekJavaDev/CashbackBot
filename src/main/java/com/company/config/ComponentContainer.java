package com.company.config;

import com.company.service.TelegramBotService;
import lombok.Data;
import com.company.TelegramBot;
import com.company.service.UserService;

@Data
public class ComponentContainer {

    public static final String NAME = "KundalikFikrlarMaqsadlarBot";
    public static final String TOKEN = "5559421571:AAGrJU4DKp6AM5fxhKcO3-B3EGGPsoi-WvM";
    public static final String ADMIN = "1438229631";
    public static final String PATH = "src/main/resources/serviceAccountKey.json";

    public static TelegramBot MY_TELEGRAM_BOT;

    public final static UserService USER_SERVICE = new UserService();
    public final static TelegramBotService TELEGRAM_BOT_SERVICE = new TelegramBotService();


}
