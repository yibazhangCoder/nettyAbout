package com.cafebaby.cafewechat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Author 一巴掌
 * @Date 2019/6/3 22:41
 * @Description TODO
 * @Version 1.0
 **/
public class ChatChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //声明一个管道组用来管理客户端接收消息
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String content = textWebSocketFrame.text();
        System.out.println("接收到的数据："+content);

        for (Channel channel :
                clients) {
            channel.writeAndFlush(new TextWebSocketFrame("[服务器在]"+ LocalDateTime.now()+"，接收到的消息为："+content));
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        clients.remove(ctx.channel());
    }
}
