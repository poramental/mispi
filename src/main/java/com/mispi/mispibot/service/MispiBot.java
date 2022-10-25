package com.mispi.mispibot.service;

import java.util.*;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.mispi.mispibot.commands.BotStartCommand;
import com.mispi.mispibot.config.MispiBotConfig;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class MispiBot extends TelegramLongPollingBot{
    
    //bots config( bot token, bot name)
    public final MispiBotConfig config ;

    //list of commands ну тут нечего сказать больше.
    private List<BotCommand> commandsList = new ArrayList<>();

    // !!COMANDS!! //
    private BotStartCommand startCommand = new BotStartCommand();
    // ... //


    // bots main contructor starts immediately after application (MispiBotApplication.java {main})
    public MispiBot(MispiBotConfig config){
        this.config = config;
        
        registerCommands(startCommand);
        try{
            this.execute(new SetMyCommands(this.commandsList, new BotCommandScopeDefault(), null));
        }
        catch(TelegramApiException e){
            log.error("execute commands exception." + e.getMessage());
        }

    }

    // main func answer to messages from users
    // req это вся инфа по чату по пользователю и что он написал
    @Override
    public void onUpdateReceived(Update req) {
        if(req.hasMessage() && req.getMessage().hasText()){
            long chatId = req.getMessage().getChatId();
            String messageText = req.getMessage().getText();
             switch (messageText) {
                    case "/start":

                        
                        sendMessage(chatId ,this.startCommand.getCommandTextAnswer(req.getMessage().getChat().getFirstName()));
                        break;

                    default:
                        prepareAndSendMessage(chatId, "Sorry, command was not recognized");
            }
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



    private void executeMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("execute message exception" + e.getMessage());
        }
    }


    // send message without keyboard 
    private void prepareAndSendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        executeMessage(message);
    }

    

    // send message with keyboard 
    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("example button");
        

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        message.setReplyMarkup(keyboardMarkup);

        executeMessage(message);
    }
    

    

    
}
