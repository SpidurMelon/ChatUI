package nl.chat.main;

import nl.chat.util.Tick;

public class ChatUIClient {
	public static Frame frame = new Frame();
	public static void main(String[] args) {
		frame.setVisible(true);
		Tick.start();
	}
}
