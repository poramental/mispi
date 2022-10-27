package com.mispi.mispibot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import lombok.extern.slf4j.Slf4j;

// в этой папке делаем классы команд по примеру этого todo разделить типы команд на информационные и работающие с базой данных (service, info)
// команда /start 
@Slf4j
@Component
public class BotStartCommand extends BotCommand  {

    private final String commandName = "/start";
    
    private final String description = "hello";

    

    
    

    public String getCommandName() {
        return this.commandName;
    }


    public String getDescription() {
        return this.description;
    }

    public String getCommandTextForUnRegisteredUser(String name){
        final String answer = "Здравствуйте! Вы еще не зарегистрированы, что бы зарегистрироваться отправьте /regMISPI.";
        return answer;
    }

    public String getCommandTextForRegisteredUser(String name){
     
        final String answer = "Здравствуйте,"+name+"! Вы уже были зарегистрированы.\n"
                        +"Список команд:\n"
                        +"/help - для получения списка команд с их описанием,\n"
                        +"/зарегистрировать MISPI - для регистрации модуля на сервере.";

        log.info("Replied to user " + name);
        return answer;
    }

    @Override
    public String toString() {
        return "{" +
            " command='" + getCommand() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
  



    
}
