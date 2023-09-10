package com.jwlioahd.Evnets;

import com.jwlioahd.Evnets.Bot.Bot;
import com.jwlioahd.Evnets.Group.GroupMessage;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListenerHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Events {

    @Autowired
    Bot bot;

    @Autowired
    GroupMessage groupMessage;


    @Bean(name = "Ahbot")
    @EventHandler
    public List<ListenerHost> getEvents(){
        List<ListenerHost> event = new ArrayList<>();
        event.add(bot);
        event.add(groupMessage);

        return event;
    }
}
