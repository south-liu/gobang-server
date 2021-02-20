package com.south.gobangserver.event;

import com.alibaba.fastjson.JSON;
import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.entity.Room;
import com.south.gobangserver.enums.EventCode;
import com.south.gobangserver.server.ServerContains;
import com.south.gobangserver.util.ChannelUtils;

public class ServerEventListener_CreateRoom implements ServerEventListener {
    @Override
    public void call(ClientSide client, String data) {
        Room room = new Room(ServerContains.getServerId());
        room.setHost(client);
        client.setRoomId(room.getId());

        ServerContains.addRoom(room);
        ChannelUtils.pushToClient(client.getChannel(), EventCode.CreateRoom, JSON.toJSONString(room));
        ChannelUtils.pushToClient(client.getChannel(), EventCode.EnterRoom, JSON.toJSONString(room));
    }
}
