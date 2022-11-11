package com.mispi.mispibot.botapi.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.google.common.base.Objects;
import com.mispi.mispibot.botapi.state.BotState;
import com.mispi.mispibot.botapi.state.BotStateCache;
import com.mispi.mispibot.models.AppUser;
import com.mispi.mispibot.models.Mispi;
import com.mispi.mispibot.service.DAO.MispiDAO;
import com.mispi.mispibot.service.DAO.UserDAO;
import com.mispi.mispibot.commands.BotRegMispiCommand;
import com.mispi.mispibot.commands.BotStartCommand;



@Component
public class BotMessageHandlerText {

    

    @Autowired
    UserDAO userDao;
    @Autowired
    MispiDAO mispiDao;
    BotStartCommand BotStartCommand = new BotStartCommand();
    BotRegMispiCommand BotRegMispiCommand = new BotRegMispiCommand();
    

    public BotApiMethod<?> messageHandler(Update update, BotStateCache stateCache){
        SendMessage sendMessage = new SendMessage();
        AppUser user = new AppUser();
        long userId = update.getMessage().getChatId();
        var messageFromUser = update.getMessage();
        user.setId(userId);
        
        System.out.println(user.toString());

        System.out.println(messageFromUser.getText());
        if(Objects.equal(messageFromUser.getText(),BotStartCommand.getCommandName())){
            if(userDao.findByUserId(userId) == null){
                sendMessage.setText(BotStartCommand.getCommandTextForUnRegisteredUser(messageFromUser.getFrom().getFirstName()));
            }
            else{sendMessage.setText(BotStartCommand.getCommandTextForRegisteredUser(messageFromUser.getFrom().getFirstName()));
                sendMessage.setChatId(String.valueOf(userId));}
            return sendMessage;

        }else if(Objects.equal(messageFromUser.getText(), BotRegMispiCommand.getCommandName())){
            
            user.setState(BotState.MISPINUM);
            stateCache.add(userId,user);
            System.out.println(user.toString());
            sendMessage.setText(BotRegMispiCommand.getCommandTextProcessLogin());
            sendMessage.setChatId(String.valueOf(userId));
            return sendMessage;

        }else if(stateCache.getStateById(userId) == BotState.MISPINUM){
            try{
                Mispi mispi = new Mispi();
                mispi.setUserChatId(userId);
                mispi.setId(Long.parseLong(messageFromUser.getText()));
                stateCache.getUserById(messageFromUser.getChatId()).setMispi(mispi);
                sendMessage.setText("хотите место? ");
                InlineKeyboardButton inlineKeyboardButtonYes = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButtonNo = new InlineKeyboardButton();
                inlineKeyboardButtonYes.setText("Да");
                inlineKeyboardButtonYes.setCallbackData("CallYesQuaeryPlace");
                inlineKeyboardButtonNo.setCallbackData("CallNoQuaeryPlace");
                inlineKeyboardButtonNo.setText("Нет");
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<InlineKeyboardButton> rowKeyboard = new ArrayList<>();
                rowKeyboard.add(inlineKeyboardButtonNo);
                rowKeyboard.add(inlineKeyboardButtonYes);
                List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
                rowList.add(rowKeyboard);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                sendMessage.setChatId(String.valueOf(userId));
                stateCache.getUserById(messageFromUser.getChatId()).setState(BotState.PLACEQUARY);
                return sendMessage;
            }catch(NumberFormatException e){
                sendMessage.setText("Номер MISPI должен быть числом.");
                sendMessage.setChatId(String.valueOf(userId));
                return sendMessage;

            }catch(Exception e ){
                System.out.println(e.getMessage());
            }
        
        }else if(stateCache.getStateById(userId) == BotState.PLACE){
            
            try{
                saveUserInDb(stateCache, update);
                sendMessage.setText("удачно");
                stateCache.delete(userId);
                sendMessage.setChatId(String.valueOf(userId));
                return sendMessage;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
        sendMessage.setText("Я не понимаю :(.");
        sendMessage.setChatId(String.valueOf(userId));
        return sendMessage;

    }

    private void saveUserInDb(BotStateCache stateCache,Update update){
        stateCache.getUserById(update.getMessage().getChatId()).setFirstName(update.getMessage().getFrom().getFirstName());
        stateCache.getUserById(update.getMessage().getChatId()).setLastName(update.getMessage().getFrom().getLastName());
        stateCache.getUserById(update.getMessage().getChatId()).getMispies().get(0).setPlace(update.getMessage().getText());
        stateCache.getUserById(update.getMessage().getChatId()).setState(BotState.DEFAULT);
        userDao.createUser(stateCache.getUserById(update.getMessage().getChatId()));
    }
    
        
}
    
    

