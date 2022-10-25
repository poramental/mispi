package com.mispi.mispibot.botapi;

import java.util.*;



import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import com.mispi.mispibot.commands.BotStartCommand;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
public class MispiBot extends SpringWebhookBot  {
    
    //bots config( bot token, bot name)
    private String botToken;
    private String botName;
    private String botPath;

    //list of commands ну тут нечего сказать больше.
    private List<BotCommand> commandsList = new ArrayList<>();

    // !!COMANDS!! //
    private BotStartCommand startCommand = new BotStartCommand();
    // ... //

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



    private void registerCommands(BotCommand command){
        this.commandsList.add(command);

    }


    private void executeMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("execute message exception" + e.getMessage());
        }
    }


    @Override
    public String getBotUsername() {
        
        return this.botName;
    }
    
 

    
}
