package com.tmathmeyer.chan4.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DLBar extends JPanel{
	private int imageCount;
	private int current;
	private int[] successes;//0=null, 1=success, 2=fail
	
	public DLBar(int size){
		this.imageCount = size;
		this.successes = new int[size];
	}
	
	public void setNextImage(boolean success){
		successes[current] = success?1:2;
		this.repaint();
		current++;
	}
	
	public void paintComponent(Graphics g){
		double t = imageCount;
		double tSize = this.getWidth()/t;
		
		for(int i = 0; i < imageCount; i++){
			if (successes[i]==0)g.setColor(Color.white);
			if (successes[i]==1)g.setColor(Color.green);
			if (successes[i]==2)g.setColor(Color.red);
			g.fillRect((int)(i*tSize), 0, (int)(tSize), this.getHeight());
			g.setColor(Color.black);
			g.drawRect((int)(i*tSize), 0, (int)(tSize), this.getHeight());
		}
	}
}
