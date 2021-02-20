package com.south.gobangserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {

    private Integer port = 6688;

    private int bossLen = 1;

    private int workLen = 5;

    private ServerBootstrap bootstrap;

    public Server() {
        log.info("实例化server对象，【参数】[port=" + port + " , bossLen=" + bossLen + " , workLen=" + workLen + "]");
        init();
    }

    public Server(Integer port, int bossLen, int workLen) {
        this.port = port;
        this.bossLen = bossLen;
        this.workLen = workLen;
        log.info("实例化server对象，【参数】[port=" + port + " , bossLen=" + bossLen + " , workLen=" + workLen + "]");
        init();
    }

    private void init() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossLen);
        EventLoopGroup workGroup = new NioEventLoopGroup(workLen);

        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        log.info("正在给连接对象添加处理器");
                        ch.pipeline().addLast(new HttpServerCodec());
                        ch.pipeline().addLast(new ChunkedWriteHandler());
                        ch.pipeline().addLast(new HttpObjectAggregator(1024 * 64));
                        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                        ch.pipeline().addLast(new GobangHandle());
                    }
                });
    }

    public void start() {
        ChannelFuture channelFuture;
        try {
            channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
            log.info("启动成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
