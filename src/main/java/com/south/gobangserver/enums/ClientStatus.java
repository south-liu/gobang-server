package com.south.gobangserver.enums;

/**
 * @author liulinbo
 */

public enum ClientStatus {

    TO_CHOOSE(-1),

    NO_READY(0),

    READY(1),

    WAIT(2),

    PLAYING(3);

    private int i;

    ClientStatus(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
