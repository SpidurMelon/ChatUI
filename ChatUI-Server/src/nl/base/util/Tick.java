package nl.base.util;

import nl.base.main.ChatUIServer;

public class Tick {
	public static int tickDelay = 30;
	public static boolean running = false;
	public static boolean execute = true;
	
	public static Thread tick = new Thread(new Runnable(){
		public void run() {
			while(running) {
				try {
					Thread.sleep(tickDelay);
				} catch (InterruptedException e) {e.printStackTrace();}
				if (execute) {
					ChatUIServer.frame.tick();
				}
			}
			return;
		}
	});
	
	public static void start() {
		running = true;
		unPause();
		tick.start();
	}
	public static void pause() {
		execute = false;
	}
	public static void unPause() {
		execute = true;
	}
	public static void stop() {
		running = false;
	}
}
