package com.south.gobangserver.entity;

import com.south.gobangserver.enums.RoomStatus;

public class Room {

    private int id;

    private ClientSide host;
    private ClientSide challenger;
    private RoomStatus status;


    public Room() {
    }

    public Room(int id) {
        this.id = id;
        this.status = RoomStatus.WAIT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientSide getHost() {
        return host;
    }

    public void setHost(ClientSide host) {
        this.host = host;
    }

    public ClientSide getChallenger() {
        return challenger;
    }

    public void setChallenger(ClientSide challenger) {
        this.challenger = challenger;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
