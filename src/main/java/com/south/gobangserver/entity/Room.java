package com.south.gobangserver.entity;

import com.south.gobangserver.enums.RoomStatus;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Room {

    private int id;

    private String roomOwner;

    private RoomStatus status;

    private Map<Integer, ClientSide> clientSideMap;

    private LinkedList<ClientSide> clientSideList;

    private long lastFlushTime;

    private long createTime;

    public Room() {
    }

    public Room(int id) {
        this.id = id;
        this.clientSideMap = new ConcurrentSkipListMap<>();
        this.clientSideList = new LinkedList<>();
        this.status = RoomStatus.BLANK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(String roomOwner) {
        this.roomOwner = roomOwner;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Map<Integer, ClientSide> getClientSideMap() {
        return clientSideMap;
    }

    public void setClientSideMap(Map<Integer, ClientSide> clientSideMap) {
        this.clientSideMap = clientSideMap;
    }

    public LinkedList<ClientSide> getClientSideList() {
        return clientSideList;
    }

    public void setClientSideList(LinkedList<ClientSide> clientSideList) {
        this.clientSideList = clientSideList;
    }

    public long getLastFlushTime() {
        return lastFlushTime;
    }

    public void setLastFlushTime(long lastFlushTime) {
        this.lastFlushTime = lastFlushTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
