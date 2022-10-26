package com.mispi.mispibot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.mispi.mispibot.botapi.MispiBot;




//сюда прилетают запросы с сервера телеграм в виде json

@RestController
public class WebHookController {
    private final MispiBot telegramBot;
    
    public WebHookController(MispiBot telegramBot) {
        this.telegramBot = telegramBot;
        
    }

    @RequestMapping(value="/bots/index.php", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }

    @GetMapping("/")
    public void index() {
        System.out.println(ResponseEntity.ok().build().toString());
    }

}
