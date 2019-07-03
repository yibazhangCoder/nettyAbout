package com.cafebaby.bootstrap;

import com.cafebaby.cafewechat.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author 一巴掌
 * @Date 2019/6/4 20:28
 * @Description TODO
 * @Version 1.0
 **/
@Component
public class NettyBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            try {
                WSServer.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
