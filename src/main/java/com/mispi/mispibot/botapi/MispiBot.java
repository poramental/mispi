package com.mispi.mispibot.botapi;


import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class MispiBot extends SpringWebhookBot  {
    
    //bots config( bot token, bot name)
    private String botToken;
    private String botName;
    private String botPath;


    private TelegramFacade telegramFacade;

    
    public MispiBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
        super(setWebhook);
        this.telegramFacade = telegramFacade;
        
    }

    public MispiBot(TelegramFacade telegramFacade, SetWebhook setWebhook) {
        super(setWebhook);
        this.telegramFacade = telegramFacade;
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
