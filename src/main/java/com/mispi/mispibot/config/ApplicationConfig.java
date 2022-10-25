
package com.mispi.mispibot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import com.mispi.mispibot.botapi.MispiBot;
import com.mispi.mispibot.botapi.TelegramFacade;

@Configuration
public class ApplicationConfig {
    private final MispiBotConfig botConfig;

    public ApplicationConfig(MispiBotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botConfig.getBotPath()).build();
    }

    @Bean
    public MispiBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
        MispiBot bot = new MispiBot(setWebhook, telegramFacade);
        bot.setBotToken(botConfig.getBotToken());
        bot.setBotName(botConfig.getBotName());
        bot.setBotPath(botConfig.getBotPath());

        return bot;
    }
    }
