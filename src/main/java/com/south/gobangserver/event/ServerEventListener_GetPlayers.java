package com.south.gobangserver.event;

import com.alibaba.fastjson.JSON;
import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.enums.EventCode;
import com.south.gobangserver.server.ServerContains;
import com.south.gobangserver.util.ChannelUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerEventListener_GetPlayers implements ServerEventListener {
    @Override
    public void call(ClientSide client, String data) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<Integer, ClientSide> clientSideMap = ServerContains.CLIENT_SIDE_MAP;
        for (Map.Entry<Integer, ClientSide> entry : clientSideMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", entry.getValue().getName());
            map.put("status", entry.getValue().getStatus());
            list.add(map);
        }
        ChannelUtils.pushToClient(client.getChannel(), EventCode.GetPlayers, JSON.toJSONString(list));
    }
}
