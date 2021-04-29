package nl.chat.main;

import java.util.ArrayList;

import javax.swing.JFrame;

import nl.chat.panels.DrawPanel;
import nl.chat.panels.Panel;

public class Frame extends JFrame {
	public ArrayList<Panel> children = new ArrayList<Panel>();
	public DrawPanel panel = new DrawPanel();
	public Frame() {
		super("Client");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addPanel(panel);
		pack();
	}
	
	public void tick() {
		for (Panel p:children) {
			p.tick();
		}
	}
	
	public void addPanel(Panel p) {
		add(p);
		children.add(p);
	}
}
