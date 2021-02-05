package com.south.gobangserver.server;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {

    public void init(){
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
    }
}
