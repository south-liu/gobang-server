package com.south.gobangserver.enums;

/**
 * @author liulinbo
 */

public enum RoomStatus {

    BLANK("空闲"),

    WAIT("等待"),

    STARTING("开始");

    private String msg;

    private RoomStatus(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
