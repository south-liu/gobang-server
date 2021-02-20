package com.south.gobangserver.entity;

import com.south.gobangserver.enums.ClientStatus;
import io.netty.channel.Channel;

/**
 * @author liulinbo
 */
public class ClientSide {

	private int id;

	private int roomId;

	private String nickname;

	private ClientStatus status;

	private transient Channel channel;

	public ClientSide() {
	}

	public ClientSide(int id, ClientStatus status, Channel channel) {
		this.id = id;
		this.status = status;
		this.channel = channel;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
