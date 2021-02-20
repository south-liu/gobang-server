package com.south.gobangserver.event;

import com.alibaba.fastjson.JSON;
import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.entity.Room;
import com.south.gobangserver.enums.EventCode;
import com.south.gobangserver.server.ServerContains;
import com.south.gobangserver.util.ChannelUtils;
import com.south.gobangserver.util.MapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerEventListener_GetRooms implements ServerEventListener {
    @Override
    public void call(ClientSide client, String data) {
        List<String> list = new ArrayList<>();
        Map<Integer, Room> roomMap = ServerContains.getRoomMap();
        for (Map.Entry<Integer, Room> entry : roomMap.entrySet()) {
            Room room = entry.getValue();
            list.add(MapHelper.newInstance()
                    .put("id", room.getId())
                    .put("roomOwner", room.getRoomOwner())
                    .put("status", room.getStatus().getMsg())
                    .json());
        }
        ChannelUtils.pushToClient(client.getChannel(), EventCode.GetRooms, JSON.toJSONString(list));
    }
}
