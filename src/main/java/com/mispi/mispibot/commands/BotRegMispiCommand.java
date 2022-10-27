package com.mispi.mispibot.commands;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public class BotRegMispiCommand extends BotCommand   {

    public final String commandName = "/regMISPI";

    private final String description = "Регистрация вашего модуля.";
    


    public String getDescription(){
        return this.description;
    }

    public String getCommandName() {
        return this.commandName;    
        
    }

    
    public String getCommandTextProcessLogin() {
        final String answer = "Отправьте мне номер вашего модуля.";
        return answer;
    }
}


