package nl.chat.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import nl.chat.main.Frame;
import nl.networking.client.Client;

public class DrawPanel extends Panel {
	
	private JTextField field;
	private JTextArea area;
	
	private Client client;
	private String nickname;
	
	private static int WIDTH = 700, HEIGHT = 600;
	
	public DrawPanel() {
		super(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		field = new JTextField();
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (client == null) {
					nickname = field.getText();
					client = new Client(nickname);
				} else {
					client.send(field.getText());
				}
			}
		});
		add(field, BorderLayout.SOUTH);
		
		area = new JTextArea();
		area.setEditable(false);
		JScrollPane scroll = new JScrollPane(area);
		add(scroll);
		
		show("What is your name?");
	}
	
	public void tick() {
		super.tick();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		g.setColor(Color.BLACK);
	}
	
	public void show(String message) {
		message+="\n";
		area.setText(area.getText()+message);
		field.setText("");
	}
}
