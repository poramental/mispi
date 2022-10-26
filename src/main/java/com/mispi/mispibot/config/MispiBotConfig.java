package com.mispi.mispibot.config;


import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

// конфиг бота
//@PropertySource для получения из файла bot.name и bot.token 
//@Component для создания бина 
@Component
@PropertySource("application.properties")
public class MispiBotConfig {
    
    @Value("${bot.token}")
    private String botToken;
    
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.path}")
    private String botPath;

    public String getToken(){
        return getBotToken();
    }

    public String getBotToken(){
        return this.botToken;
    }
   
    public String getBotName(){
        return this.botToken;
    }

    public String getBotPath(){
        return this.botPath;
    }
}
