package nl.base.panels;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import nl.base.main.Frame;

public class Panel extends JPanel {
	public ArrayList<Panel> children = new ArrayList<Panel>();
	
	public int width, height;
	
	public Panel(int width, int height) {
		this.width = width;
		this.height = height;
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
	
	public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
