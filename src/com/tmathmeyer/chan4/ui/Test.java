package com.tmathmeyer.chan4.ui;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Test {
	public static JFrame f;
	public static void main(String[] args){
		f = new JFrame();
		f.setLayout(new GridLayout(1,1));
		f.add(new ChanGUI());
		f.pack();
		f.setVisible(true);
	}
}
