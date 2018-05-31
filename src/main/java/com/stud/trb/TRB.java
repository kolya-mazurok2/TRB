package com.stud.trb;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TRB extends TelegramLongPollingBot {
	final static Logger logger = Logger.getLogger(TRB.class);
	private boolean isRunning = false;
	String sendTo;
	
	@Override
    public void onUpdateReceived(Update update) {
		sendTo = "";
		User user = update.getMessage().getFrom();
		StringBuilder builder = new StringBuilder();
		if(user.getUserName() != null) {
			sendTo = builder.append("@").append(user.getUserName()).toString();
		}
		else if(user.getFirstName() != null) {
			sendTo = builder.append(user.getFirstName()).toString();
		}
		else {
			sendTo = builder.append(messageGenerator.generateName()).toString();
		}
		if(!isRunning) {
			Runnable runnable = new Runnable() {
				public void run() {
					isRunning = true;
					String messageText = sendTo + "! " + messageGenerator.generateMessage();
				    long chat_id = update.getMessage().getChatId();
	
				    SendMessage message = new SendMessage()
				            .setChatId(chat_id)
				            .setText(messageText);
				    try {
				        execute(message);
				    } catch (TelegramApiException ex) {
				        logger.error("Something went wrong - " + ex);
				        isRunning = false;
				    }
				}
			};
			
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(runnable, 0, 4, TimeUnit.HOURS);
		}
    }

    @Override
    public String getBotUsername() {
        return "TRB";
    }

    @Override
    public String getBotToken() {
        return "580091433:AAGRpy_LrOuX2GP-R40AB1--Xk80mR2LULY";
    }
}
