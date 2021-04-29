package nl.networking.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import nl.base.main.ChatUIServer;

public class Connection extends Thread {
	public String nickname;
	private Socket client;
	private DataInputStream in;
	private DataOutputStream out;
	private String input;
	
	public Connection(String nickname, Socket client, DataInputStream in, DataOutputStream out) {
		this.nickname = nickname;
		this.client = client;
		this.in = in;
		this.out = out;
	}
	
	public void run() {
		try {
			//System.out.println("Listening for messages from " + nickname);
			while(true) {
				try {
					input = in.readUTF();
					//TODO Handle the command/message here:
					ChatUIServer.frame.panel.show(nickname + ": " + input);
					Server.sendToAll(nickname + ": " + input);
				} catch (Exception e) {
					//System.out.println("Lost connection with " + nickname + ". Terminating.");
					disconnect();
					break;
				}
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void send(String message) {
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			ChatUIServer.frame.panel.show("Couldn't send message to " + nickname);
		}
	}
	
	public void disconnect() throws IOException {
		client.close();
		in.close();
		out.close();
		Server.removeConnection(this);
	}
}
