package com.jwlioahd.Evnets.Group;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GroupMessage extends SimpleListenerHost {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupMessage.class);

    @EventHandler
    public ListeningStatus groupMessage(@NotNull GroupMessageEvent message){

        boolean contains = message.getMessage().serializeToMiraiCode().contains("机器人");
        if (contains){
            message.getSubject().sendMessage("我在");
        }
        return ListeningStatus.LISTENING;
    }
}
