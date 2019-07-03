package com.cafebaby.cafewechat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author 一巴掌
 * @Date 2019/6/3 22:36
 * @Description TODO
 * @Version 1.0
 **/
public class WSChannelIntinaizer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        //添加http有关的处理器
        pipeline.addLast(new HttpServerCodec());
        //对httpmessage进行聚合 聚合成httprequest 或者httprespon
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        //大数据处理
        pipeline.addLast(new ChunkedWriteHandler());


        //支持websocket并进行映射
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new ChatChannelHandler());
    }
}
