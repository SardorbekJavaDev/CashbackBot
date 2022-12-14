package com.company;

import com.company.config.ComponentContainer;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Main {


    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot bot = new TelegramBot();
            ComponentContainer.MY_TELEGRAM_BOT = bot;
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//        try (ServerSocket serverSocket = new ServerSocket(Integer.valueOf(PORT))) {
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(ComponentContainer.PATH);
        File file = new File(ComponentContainer.PATH);
        System.out.println(file.getPath());
        System.out.println("absolute path "+file.getAbsolutePath());
        FileInputStream serviceAccount = null;
        FirebaseOptions options;
        try {
            serviceAccount = new FileInputStream(file);
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://kashback-b3a23-default-rtdb.firebaseio.com")
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FirebaseApp.initializeApp(options);

        System.out.println("Application started successfully !");
    }
}