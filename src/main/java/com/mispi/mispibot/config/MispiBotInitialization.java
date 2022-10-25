package com.mispi.mispibot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.mispi.mispibot.service.MispiBot;

import lombok.extern.slf4j.Slf4j;



// инициализация бота аннотация @Slf4j для логов @Component для создания бина
@Slf4j
@Component
public class MispiBotInitialization{

    // подстановка бина бота из spring context 
    @Autowired
    MispiBot bot;


    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException{
        TelegramBotsApi APIbot = new TelegramBotsApi(DefaultBotSession.class);

        try{
            APIbot.registerBot(this.bot);

        }
        catch(TelegramApiException e){
            log.error("Telegram api exception" + e.getMessage());
        }
    }


    
    
}
