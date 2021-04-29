package nl.networking.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import nl.chat.main.ChatUIClient;
import nl.chat.panels.DrawPanel;

public class Client {
	private String nickname;
	private Socket connection;
	private DataOutputStream out;
	private DataInputStream in;
	
	public String messageReceived;
	
	public Client(String nickname){
		this.nickname = nickname;
		try {
			ChatUIClient.frame.panel.show("Establishing connection...");
			connection = new Socket("82.161.237.86", 7777);
			out = new DataOutputStream(connection.getOutputStream());
			in = new DataInputStream(connection.getInputStream());
			out.writeUTF(nickname);
			ChatUIClient.frame.panel.show("Connected!");
			
			messageReceived = in.readUTF();
			System.out.println(messageReceived);
			if (messageReceived.equals("Welcome " + nickname + "!")) {
				ChatUIClient.frame.panel.show("Welcome " + nickname + "!");
				incoming.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			terminate();
		}
	}
	
	public void send(String message) {
		try {
			out.writeUTF(message);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void terminate() {
		try {
			connection.close();
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Closed client");
	}
	
	private Thread incoming = new Thread(new Runnable(){
		public void run() {
			while(true) {
				try {
					messageReceived = in.readUTF();
					ChatUIClient.frame.panel.show(messageReceived);
				} catch (IOException e) {
					System.out.println("Server broke the connection, shutting down.");
					break;
				}
			}
		}
	});

}
