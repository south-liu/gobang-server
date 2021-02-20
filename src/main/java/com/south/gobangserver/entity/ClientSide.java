package com.south.gobangserver.entity;

import com.south.gobangserver.enums.ClientStatus;
import io.netty.channel.Channel;

/**
 * @author liulinbo
 */
public class ClientSide {

	private int id;

	private int roomId;

	private String name;

	private ClientStatus status;


	private transient Channel channel;

	public ClientSide() {
	}

	public ClientSide(int id, ClientStatus status, Channel channel) {
		this.id = id;
		this.status = status;
		this.channel = channel;
		this.status = ClientStatus.leisure;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientStatus getStatus() {
		return status;
	}

	public void setStatus(ClientStatus status) {
		this.status = status;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
