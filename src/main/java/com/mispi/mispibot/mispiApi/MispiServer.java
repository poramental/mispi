package com.mispi.mispibot.mispiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.mispi.mispibot.botapi.MispiBot;
import com.mispi.mispibot.service.DAO.MispiDAO;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Component
public class MispiServer {
	@Autowired
    private MispiBot mispibot;
    @Autowired
    private MispiDAO mispiDAO;

    public void sendFireAlert(long mispiNum, long mispiErrorCode){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(mispiDAO.findByMispiId(mispiNum).getUserChatId()));
        sendMessage.setText("fire fire");
        try{
            mispibot.sendMessage(sendMessage);
        }catch(TelegramApiException e){
            System.out.println(e.getMessage());
        }
        
    }

    
    
	
}