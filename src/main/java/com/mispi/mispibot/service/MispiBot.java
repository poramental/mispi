package com.mispi.mispibot.service;

import java.util.*;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.mispi.mispibot.commands.BotStartCommand;
import com.mispi.mispibot.config.MispiBotConfig;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class MispiBot extends TelegramLongPollingBot{
    
    public final MispiBotConfig config ;

    private List<BotCommand> commandsList = new ArrayList<>();

    public MispiBot(MispiBotConfig config){
        this.config = config;

        
        BotStartCommand command = new BotStartCommand();

        registerCommands(command);
        try{
            this.execute(new SetMyCommands(this.commandsList, new BotCommandScopeDefault(), null));
        }
        catch(TelegramApiException e){
            log.error("execute commands exception." + e.getMessage());
        }

    }

    @Override
    public void onUpdateReceived(Update message) {
        if(message.hasMessage() && message.getMessage().hasText()){
                setAnswer(message.getChatMember().getChat().getId(), message.getChatMember().getChat().getUserName(), "geee");
        }

        
    }
    private void registerCommands(BotCommand command){
        this.commandsList.add(command);

    }
    @Override
    public String getBotToken() {
        
        return config.getBotToken();
    }

    @Override
    public String getBotUsername() {
       
       return config.getBotName();
    }
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
           log.error("send answer exception." + e.getMessage()); 
           
        }
    }
    

    

    
}
