package com.company.config;

import com.company.TelegramBot;
import com.company.service.TelegramBotService;
import com.company.service.UserService;
import lombok.Data;

@Data
public class ComponentContainer {

    public static final String NAME = "your bots username";
    public static final String TOKEN = "your token";
    public static final String ADMIN = "your telegram id";
    public static final String PATH = "src/main/resources/serviceAccountKey.json"; // this is for firebase

    public static TelegramBot MY_TELEGRAM_BOT;

    public final static UserService USER_SERVICE = new UserService();
    public final static TelegramBotService TELEGRAM_BOT_SERVICE = new TelegramBotService();


}
