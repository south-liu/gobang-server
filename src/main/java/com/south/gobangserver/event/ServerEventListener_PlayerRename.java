package com.south.gobangserver.event;

import com.alibaba.fastjson.JSON;
import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.enums.EventCode;
import com.south.gobangserver.server.ServerContains;
import com.south.gobangserver.util.ChannelUtils;
import com.south.gobangserver.util.DateUtils;
import com.south.gobangserver.util.MapHelper;

import java.util.*;

public class ServerEventListener_PlayerRename implements ServerEventListener {
    @Override
    public void call(ClientSide client, String data) {
        client.setName(data);

        ChannelUtils.pushToClient(client.getChannel(), EventCode.GetPlayer, MapHelper.newInstance()
                .put("name", client.getName())
                .put("status", client.getStatus())
                .json());

        List<Map<String, Object>> list = new ArrayList<>();
        Map<Integer, ClientSide> clientSideMap = ServerContains.CLIENT_SIDE_MAP;
        for (Map.Entry<Integer, ClientSide> entry : clientSideMap.entrySet()) {
            ChannelUtils.pushToClient(entry.getValue().getChannel(), EventCode.HallChat, MapHelper.newInstance()
                    .put("time", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN))
                    .put("from", "系统消息")
                    .put("content", client.getName() + " 加入服务器!")
                    .json());
            Map<String, Object> map = new HashMap<>();
            map.put("name", entry.getValue().getName());
            map.put("status", entry.getValue().getStatus());
            list.add(map);
        }

        for (Map.Entry<Integer, ClientSide> entry : clientSideMap.entrySet()) {
            ChannelUtils.pushToClient(entry.getValue().getChannel(), EventCode.GetPlayers, JSON.toJSONString(list));
        }

    }
}
