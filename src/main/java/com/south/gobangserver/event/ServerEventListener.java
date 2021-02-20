package com.south.gobangserver.event;

import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.enums.EventCode;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liulinbo
 */
public interface ServerEventListener {
    void call(ClientSide client, String data);

    Map<EventCode, ServerEventListener> LISTENER_MAP = new HashMap<>();

    String LISTENER_PREFIX = "com.south.gobangserver.event.ServerEventListener_";

    @SuppressWarnings("unchecked")
    static ServerEventListener get(EventCode code) {
        ServerEventListener listener = null;
        try {
            if (ServerEventListener.LISTENER_MAP.containsKey(code)) {
                listener = ServerEventListener.LISTENER_MAP.get(code);
            } else {
                String eventListener = LISTENER_PREFIX + code.name();
                Class<ServerEventListener> listenerClass = (Class<ServerEventListener>) Class.forName(eventListener);
                try {
                    listener = listenerClass.getDeclaredConstructor().newInstance();
                } catch (InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                ServerEventListener.LISTENER_MAP.put(code, listener);
            }
            return listener;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return listener;
    }
}
