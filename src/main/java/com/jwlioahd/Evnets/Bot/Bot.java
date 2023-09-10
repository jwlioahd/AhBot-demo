package com.jwlioahd.Evnets.Bot;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Bot extends SimpleListenerHost {

    @EventHandler
    public ListeningStatus login(@NotNull BotOnlineEvent bot){
        Objects.requireNonNull(bot.getBot().getFriend(2252149540L)).sendMessage("我上来了");
        return ListeningStatus.STOPPED;
    }
}
