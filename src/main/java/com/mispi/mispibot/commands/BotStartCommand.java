package com.mispi.mispibot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;


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

    
}
