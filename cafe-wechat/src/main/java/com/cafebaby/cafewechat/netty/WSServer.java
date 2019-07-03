package com.cafebaby.cafewechat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author 一巴掌
 * @Date 2019/6/4 20:29
 * @Description TODO
 * @Version 1.0
 **/
@Component
public class WSServer {

    Logger logger = LoggerFactory.getLogger(WSServer.class);

    private static class SingleWSServer{
        static WSServer instance = new WSServer();
    }

    public static WSServer getInstance(){
        return SingleWSServer.instance;
    }

    private EventLoopGroup parentGroup;
    private EventLoopGroup childGroup;
    private ServerBootstrap bootstrap;
    private ChannelFuture future;

    public WSServer(){
        parentGroup = new NioEventLoopGroup();
        childGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(parentGroup,childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSChannelIntinaizer());
    }

    public void start(){
        this.future = bootstrap.bind(8088);
        logger.info("Netty Server has Started。。。。");
    }
}
