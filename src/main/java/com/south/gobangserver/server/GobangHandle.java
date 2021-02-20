package com.south.gobangserver.server;

import com.alibaba.fastjson.JSON;
import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.entity.Request;
import com.south.gobangserver.enums.ClientStatus;
import com.south.gobangserver.enums.EventCode;
import com.south.gobangserver.event.ServerEventListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GobangHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content = msg.text();
        log.info("服务器接收到的消息：{}", content);
        Request request = JSON.parseObject(content, Request.class);
        EventCode event = EventCode.getEnum(request.getCode());
        ClientSide side = ServerContains.CLIENT_SIDE_MAP.get(getId(ctx.channel()));
        ServerEventListener.get(event).call(side, request.getData());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ClientSide side = new ClientSide(getId(channel), ClientStatus.leisure, channel);
        side.setName("gobang-" + side.getId());
        log.info("{} 连接成功", side.getId());
        ServerContains.CLIENT_SIDE_MAP.put(side.getId(), side);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        int id = getId(channel);
        ServerContains.CLIENT_SIDE_MAP.remove(id);
    }

    private int getId(Channel channel) {
        String longId = channel.id().asLongText();
        Integer clientId = ServerContains.CHANNEL_ID_MAP.get(longId);
        if (null == clientId) {
            clientId = ServerContains.getClientId();
            ServerContains.CHANNEL_ID_MAP.put(longId, clientId);
        }
        return clientId;
    }
}
