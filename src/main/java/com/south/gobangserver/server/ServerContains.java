package com.south.gobangserver.server;

import com.south.gobangserver.entity.ClientSide;
import com.south.gobangserver.entity.Room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerContains {

	/**
	 * Server port
	 */
	public static int port = 1024;

	/**
	 * The map of server side
	 */
	private final static Map<Integer, Room> ROOM_MAP = new ConcurrentSkipListMap<>();

	/**
	 * The list of client side
	 */
	public final static Map<Integer, ClientSide> CLIENT_SIDE_MAP = new ConcurrentSkipListMap<>();

	public final static Map<String, Integer> CHANNEL_ID_MAP = new ConcurrentHashMap<>();


	private final static AtomicInteger CLIENT_ATOMIC_ID = new AtomicInteger(1);

	private final static AtomicInteger SERVER_ATOMIC_ID = new AtomicInteger(1);

	public static int getClientId() {
		return CLIENT_ATOMIC_ID.getAndIncrement();
	}

	public static int getServerId() {
		return SERVER_ATOMIC_ID.getAndIncrement();
	}

	/**
	 * Get room by id, with flush time
	 *
	 * @param id room id
	 */
	public static Room getRoom(int id) {
		Room room = ROOM_MAP.get(id);
		if (room != null) {
			room.setLastFlushTime(System.currentTimeMillis());
		}
		return room;
	}

	public static Map<Integer, Room> getRoomMap() {
		return ROOM_MAP;
	}

	public static Room removeRoom(int id) {
		return ROOM_MAP.remove(id);
	}

	public static Room addRoom(Room room) {
		return ROOM_MAP.put(room.getId(), room);
	}
}
