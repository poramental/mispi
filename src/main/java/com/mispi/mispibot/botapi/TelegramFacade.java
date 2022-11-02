package com.mispi.mispibot.botapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;


import com.google.common.base.Objects;

import com.mispi.mispibot.botapi.state.BotState;
import com.mispi.mispibot.botapi.state.BotStateCache;
import com.mispi.mispibot.commands.BotRegMispiCommand;
import com.mispi.mispibot.commands.BotStartCommand;
import com.mispi.mispibot.models.Mispi;
import com.mispi.mispibot.models.AppUser;
import com.mispi.mispibot.service.DAO.MispiDAO;
import com.mispi.mispibot.service.DAO.UserDAO;
import com.mispi.mispibot.service.DAO.Repositories.MispiRepository;
import com.mispi.mispibot.service.DAO.Repositories.UserRepository;





@Component
public class TelegramFacade {

    BotStateCache stateCache = new BotStateCache();

    @Autowired
    UserDAO userDao;
    // @Autowired
    // MispiDAO mispiDao;
    BotStartCommand BotStartCommand = new BotStartCommand();
    BotRegMispiCommand BotRegMispiCommand = new BotRegMispiCommand();
    
    
    
    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage sendMessage = new SendMessage();
        CallbackQuery callbackQuery = new CallbackQuery();
        
        
        
        if (update.hasMessage()){
            long userId = update.getMessage().getChatId();
            var messageFromUser = update.getMessage();
            if(messageFromUser.hasText()){
                AppUser user = new AppUser();
                user.setId(userId);
                user.setName(messageFromUser.getFrom().getFirstName());
                System.out.println(user.toString());

                System.out.println(messageFromUser.getText());
                if(Objects.equal(messageFromUser.getText(),BotStartCommand.getCommandName())){
                    
                    sendMessage.setText(BotStartCommand.getCommandTextForUnRegisteredUser(messageFromUser.getFrom().getFirstName()));
                    sendMessage.setChatId(String.valueOf(userId));
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
                        System.out.print(messageFromUser.getText());
                        user.setState(BotState.DEFAULT);
                        
                        userDao.createUser(user);
                        // stateCache.delete(userId);
                        // Mispi mispi = new Mispi(Long.parseLong(messageFromUser.getText()),user);
                        // mispiRepository.save(mispi);
                        // user.addMispi(mispi);
                        // System.out.print("mispi add mispi end\n");
                        // userRepository.save(user);
                    }catch(NumberFormatException e){
                        sendMessage.setText("Номер MISPI должен быть числом.");
                        sendMessage.setChatId(String.valueOf(userId));
                        return sendMessage;

                    }catch(Exception e ){
                        System.out.println(e.getMessage());
                    }
                }
            }
            
        }
        return null;
        
    }

}