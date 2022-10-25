package com.mispi.mispibot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;



@Configuration
@PropertySource("application.properties")
public class MispiBotConfig {
    
    @Value("${bot.token}")
    private String botToken;
    
    @Value("${bot.name}")
    private String botName;

    public String getToken(){
        return getBotToken();
    }

    public String getBotToken(){
        return this.botToken;
    }
   
    public String getBotName(){
        return this.botToken;
    }
}
