package com.stud.trb;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Application {
	final static Logger logger = Logger.getLogger(Application.class);
	
    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TRB());
        } catch (TelegramApiException ex) {
            logger.error("Something went wrong - " + ex);
        }
    }
}