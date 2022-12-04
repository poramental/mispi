package com.mispi.mispibot.botapi;


import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;


import com.mispi.mispibot.commands.BotRegMispiCommand;
import com.mispi.mispibot.commands.BotStartCommand;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;


@Getter
@Setter
public class MispiBot extends SpringWebhookBot  {
    
    //bots config( bot token, bot name)
    private String botToken;
    private String botName;
    private String botPath;
    private List<BotCommand> listOfCommands = new ArrayList<>();

    private TelegramFacade telegramFacade;

    
    public MispiBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
        super(setWebhook);
        this.listOfCommands.add(new BotRegMispiCommand().getBotCommand());
        this.listOfCommands.add(new BotStartCommand().getBotCommand());
        this.telegramFacade = telegramFacade;
        try{
            execute(new SetMyCommands(this.listOfCommands, new BotCommandScopeDefault(),null));
            
        }catch(TelegramApiException e){
            System.out.println(e.getMessage());
        }
        
    }



    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update req) {
        
        return telegramFacade.handleUpdate(req);
        
    }

    public void sendMessage(SendMessage message) throws TelegramApiException{
        execute(message);
    }

    @Override
    public String getBotUsername() {
        
        return this.botName;
    }
    
 

    
}
