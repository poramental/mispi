package com.mispi.mispibot.botapi;



import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
public class TelegramFacade {

    public SendMessage handleUpdate(Update update) {
        SendMessage sendMessage = new SendMessage();
        System.out.println("dsada");
        CallbackQuery callbackQuery = new CallbackQuery();
        long userId = update.getMessage().getChatId();
        if (update.hasMessage()){
            var messageFromUser = update.getMessage();
            if(messageFromUser.hasText()){
                sendMessage.setText(messageFromUser.getText());
                sendMessage.setChatId(String.valueOf(userId));
                return  sendMessage;
            }
            
        }
        return null;
        
    }

}