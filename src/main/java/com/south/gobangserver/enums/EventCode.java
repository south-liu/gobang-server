package com.south.gobangserver.enums;

import java.io.Serializable;

/**
 * @author liulinbo
 */

public enum EventCode implements Serializable {

    Fail(0),
    Success(1),
    HallChat(2),
    GetHallDialog(3),
    GetRooms(4),
    CreateRoom(5),
    EnterRoom(6),
    LeaveRoom(7),
    DelRoom(8),
    RoomChat(9),
    GetPlayer(10),
    GetPlayers(11),
    PlayerRename(12),
    SetPlayerStatus(13),
    SetReady(14),
    MakeStep(15),
    RetractStep(16),
    Surrender(17),
    AskDraw(18),
    GameOver(19);

    private int code;

    EventCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static EventCode getEnum(int code) {
        for (EventCode value : EventCode.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
