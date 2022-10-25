package com.mispi.mispibot.botapi;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
public class TelegramFacade {

    public SendMessage handleUpdate(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText("Hello world");
        if (update.hasCallbackQuery()) {
            System.out.print("\ndasdada\n\n");
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return null;
        } else {

            
            
            if (true) {
                System.out.print("dadadadad");
                sendMessage.setText("Hello world");
                return sendMessage;
            }
        }
        return sendMessage;
    }

}