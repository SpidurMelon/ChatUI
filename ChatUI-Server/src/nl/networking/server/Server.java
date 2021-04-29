package nl.networking.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import nl.base.main.ChatUIServer;

public class Server {
	public static Connector connector = new Connector();
	public static ArrayList<Connection> clients = new ArrayList<Connection>();
	
	public Server() {
		Thread connect = new Thread(new Runnable(){
			public void run() {
				connector.run();
			}
		});
		connect.start();
	}
	
	public static void Announce(String message) {
		ChatUIServer.frame.panel.show(message);
		sendToAll(message);
	}
	
	public static void sendToAll(String message) {
		for (int i = 0; i < clients.size(); i++) {
			sendTo(message, clients.get(i));
		}
	}
	
	public static void sendTo(String message, Connection client) {
		client.send(message);
	}
	
	public static int getDupeNames(String nickname) {
		int result = 0;
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).nickname.equals(nickname)) {
				result++;
			}
		}
		return result;
	}
	
	public static void addConnection(String nickname, Socket client, DataInputStream in, DataOutputStream out) {
		
		if (getDupeNames(nickname) != 0) {
			nickname = nickname + "(" + (getDupeNames(nickname)+1) + ")";
		}
		
		Connection connection = new Connection(nickname, client, in, out);
		connection.start();
		Announce(nickname + " has connected!");
		clients.add(connection);
	}
	
	public static void removeConnection(Connection client) {
		clients.remove(client);
		Announce(client.nickname + " has disconnected!");
	}
}
