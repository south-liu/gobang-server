package com.south.gobangserver.util;

import com.south.gobangserver.enums.EventCode;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChannelUtils {

	public static void pushToClient(Channel channel, EventCode code, String data) {
		pushToClient(channel, code, data, null);
	}

	public static void pushToClient(Channel channel, EventCode code, String data, String info) {
		if (channel != null) {
			String json = MapHelper.newInstance()
					.put("code", code.getCode())
					.put("data", data)
					.json();
			log.info("发送给客户端 {}", json);
			channel.writeAndFlush(new TextWebSocketFrame(json));
		}
	}

}
