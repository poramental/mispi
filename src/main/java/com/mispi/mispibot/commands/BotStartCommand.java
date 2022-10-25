package com.mispi.mispibot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import lombok.extern.slf4j.Slf4j;

// в этой папке делаем классы команд по примеру этого todo разделить типы команд на информационные и работающие с базой данных (service, info)
// команда /start 
@Slf4j
@Component
public class BotStartCommand extends BotCommand  implements Commands{

    private final String commandName = "/start";
    
    private final String description = "hello";


    

    public String getCommandName() {
        return this.commandName;
    }


    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "{" +
            " command='" + getCommand() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    public String getCommandTextAnswer(String name) {

        String answer = "Hi, " + name + ", nice to meet you!";
        log.info("Replied to user " + name);
        return answer;
    }
    
}
