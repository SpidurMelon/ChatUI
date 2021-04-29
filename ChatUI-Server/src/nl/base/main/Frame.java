package nl.base.main;

import java.util.ArrayList;

import javax.swing.JFrame;

import nl.base.panels.DrawPanel;
import nl.base.panels.Panel;

public class Frame extends JFrame {
	public ArrayList<Panel> children = new ArrayList<Panel>();
	public DrawPanel panel = new DrawPanel();
	public Frame() {
		super("Server");
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
