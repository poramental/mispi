package com.mispi.mispibot.botapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.mispi.mispibot.botapi.handlers.BotCallBackQueryHandler;
import com.mispi.mispibot.botapi.handlers.BotMessageHandlerText;

import com.mispi.mispibot.botapi.state.BotStateCache;








@Component
public class TelegramFacade {

    BotStateCache stateCache = new BotStateCache();
    @Autowired
    BotMessageHandlerText botMessageHandlerText;
    @Autowired
    BotCallBackQueryHandler callBackQueryHandler;
    
    public BotApiMethod<?> handleUpdate(Update update) {
        if (update.hasMessage()){
            var messageFromUser = update.getMessage();
            if(messageFromUser.hasText()){
                return botMessageHandlerText.messageHandler(update,stateCache);
            }
            
            
           
        }
        if(update.hasCallbackQuery()){
            return callBackQueryHandler.callBackQuaeryHandler(update, stateCache);
        }
        return null;
            
}
}