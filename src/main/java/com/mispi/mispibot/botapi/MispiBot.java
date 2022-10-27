package com.mispi.mispibot.botapi;


import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
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

    // main func answer to messages from users
    // req это вся инфа по чату по пользователю и что он написал
    // req прилетает пост запросом на сервер в виде json
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update req) {
        
        return telegramFacade.handleUpdate(req);
        
    }



    @Override
    public String getBotUsername() {
        
        return this.botName;
    }
    
 

    
}
