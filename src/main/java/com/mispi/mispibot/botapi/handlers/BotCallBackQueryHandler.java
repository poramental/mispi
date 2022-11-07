package com.mispi.mispibot.botapi.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.google.common.base.Objects;
import com.mispi.mispibot.botapi.state.BotState;
import com.mispi.mispibot.botapi.state.BotStateCache;
import com.mispi.mispibot.service.DAO.UserDAO;

import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class BotCallBackQueryHandler {
    @Autowired
    UserDAO userDao;
    
    public SendMessage callBackQuaeryHandler(Update update, BotStateCache stateCache){
        SendMessage sendMessage = new SendMessage();
        if(update.hasCallbackQuery()){
                    
            if(Objects.equal(update.getCallbackQuery().getData(),"CallYesQuaeryPlace") && stateCache.getStateById(update.getCallbackQuery().getFrom().getId()) == BotState.PLACEQUARY )  {
                stateCache.getUserById(update.getCallbackQuery().getFrom().getId()).setState(BotState.PLACE);
                sendMessage.setText("введите место");
                sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getFrom().getId()));
                return sendMessage;
            }
            if(Objects.equal(update.getCallbackQuery().getData(),"CallNoQuaeryPlace") && stateCache.getStateById(update.getCallbackQuery().getFrom().getId()) == BotState.PLACEQUARY) {
                saveUserInDb(stateCache, update);
                sendMessage.setText("удачно");
                stateCache.delete(update.getCallbackQuery().getFrom().getId());
                sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getFrom().getId()));
                return sendMessage;
            }
        }

        return null;
    }
    private void saveUserInDb(BotStateCache stateCache,Update update){
        stateCache.getUserById(update.getCallbackQuery().getFrom().getId()).setFirstName(update.getCallbackQuery().getFrom().getFirstName());
        stateCache.getUserById(update.getCallbackQuery().getFrom().getId()).setLastName(update.getCallbackQuery().getFrom().getLastName());
        stateCache.getUserById(update.getCallbackQuery().getFrom().getId()).setState(BotState.DEFAULT);
        userDao.createUser(stateCache.getUserById(update.getCallbackQuery().getFrom().getId()));
    }
    
}
