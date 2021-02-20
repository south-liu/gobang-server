package com.south.gobangserver.event;

import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.enums.EventCode;
import com.south.gobangserver.server.ServerContains;
import com.south.gobangserver.util.ChannelUtils;
import com.south.gobangserver.util.DateUtils;
import com.south.gobangserver.util.MapHelper;

import java.util.Date;
import java.util.Map;

public class ServerEventListener_PlayerRename implements ServerEventListener {
    @Override
    public void call(ClientSide client, String data) {
        client.setNickname(data);

        Map<Integer, ClientSide> clientSideMap = ServerContains.CLIENT_SIDE_MAP;
        for (Map.Entry<Integer, ClientSide> entry : clientSideMap.entrySet()) {
            ChannelUtils.pushToClient(entry.getValue().getChannel(), EventCode.HallChat, MapHelper.newInstance()
                    .put("time", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN))
                    .put("from", "系统消息")
                    .put("content", client.getNickname() + " 加入服务器!")
                    .json());
        }
    }
}
