package nl.base.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import nl.base.main.Frame;
import nl.networking.server.Server;

public class DrawPanel extends Panel {
	
	private JTextField field;
	private JTextArea area;
	
	private Server server;
	
	private static int WIDTH = 700, HEIGHT = 600;
	
	public DrawPanel() {
		super(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		field = new JTextField();
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				executeCommand(field.getText());
			}
		});
		add(field, BorderLayout.SOUTH);
		
		area = new JTextArea();
		area.setEditable(false);
		JScrollPane scroll = new JScrollPane(area);
		add(scroll);
		
		server = new Server();
	}
	
	public void tick() {
		super.tick();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		g.setColor(Color.BLACK);
	}
	
	private void executeCommand(String command) {
		switch(command) {
			case("Quit"):
				System.out.println("No.");
				break;
		}
	}
	
	public void show(String message) {
		message+="\n";
		area.setText(area.getText()+message);
		field.setText("");
	}
}
