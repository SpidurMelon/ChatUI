package nl.networking.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import nl.base.main.ChatUIServer;

public class Connector extends Thread {
	
	private ServerSocket serversocket;
	public int port = 7777;
	public String nickname;
	
	public Connector() {
		try {
			serversocket = new ServerSocket(port);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void run() {
		ChatUIServer.frame.panel.show("Listening for clients...");
		while(true) {
			try {
				//System.out.println("Listening for client... (" + Server.clients.size() + ")");
				Socket client = serversocket.accept();
				DataInputStream in = new DataInputStream(client.getInputStream());
				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				
				try {
					nickname = in.readUTF();
					
					out.writeUTF("Welcome " + nickname + "!");
					Server.addConnection(nickname, client, in, out);
				} catch(Exception e) {
					ChatUIServer.frame.panel.show("Someone tried to connect but something went wrong.");
					client.close();
					in.close();
					out.close();
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public void stopServer() throws IOException {
		serversocket.close();
	}
}
