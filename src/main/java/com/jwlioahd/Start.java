package com.jwlioahd;

import com.jwlioahd.util.Mylog;
import lombok.Getter;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.mrxiaom.qsign.QSignService;

import java.io.File;
import java.util.List;

@Component
public class Start implements ApplicationRunner {

    @Value("${account}")
    private Long account;

    @Value("${passwd}")
    private String passwd;

    @Getter
    private static Bot mirai;

    @Autowired
    @Qualifier("Ahbot")
    List<ListenerHost> events;


    @Override
    public void run(ApplicationArguments args) {
        signingServer();
    }

    /**
     * <span style="font-size: 20px;">手表扫码</span><br/>
     * <ul>
     *     <li>
     *         <span style="font-size: 15px;">可以不使用<font color="#d0a8ff">密码</font>登录</span>
     *     </li>
     *     <li>
     *         <span style="font-size: 15px;">需要<font color="#5dd8ff">扫码</font>这么麻烦</span>
     *     </li>
     * </ul>
     *
     * @author 阿黄
     * @邮箱 jwlioahd@qq.com
     */
    public void Wristwatch() {
        mirai = BotFactory.INSTANCE.newBot(account, BotAuthorization.byQRCode(), config -> {
            config.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH);
        });
        mirai.login();
        new Thread(mirai::join).start();
    }

    /**
     * <span style="font-size: 20px;">签名服务器</span><br/>
     * <ul>
     *     <li>
     *         <span style="font-size: 15px;">可以使用<font color="#fc5fa3">账号</font>-<font color="#d0a8ff">密码</font>登录</span>
     *     </li>
     *     <li>
     *         <span style="font-size: 15px;">不用<font color="#5dd8ff">扫码</font>这么麻烦</span>
     *     </li>
     * </ul>
     *
     * @author 阿黄
     * @邮箱 jwlioahd@qq.com
     */
    public void signingServer() {
        File file = new File("D:\\XDMU\\Bot\\jwlioahd\\AhBot-demo\\src\\main\\resources\\txlib\\8.9.63");
        QSignService.Factory.init(file); // 读取协议
        QSignService.Factory.loadProtocols(null);// 默认null，不需要配置自定义的加载协议
        QSignService.Factory.register();// 注册签名服务器

        mirai = BotFactory.INSTANCE.newBot(account, passwd, config -> {
            config.noNetworkLog();// 不显示网络层日志
            config.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PAD);// PAD协议登录
            config.fileBasedDeviceInfo(); // 文件存储设备信息，就是咱们项目中的device.json，不用管
            config.setBotLoggerSupplier(bot -> new Mylog());
        });
        for (ListenerHost event : events) {
            mirai.getEventChannel().registerListenerHost(event);
        }

        mirai.login();
        new Thread(mirai::join).start();
    }

}
