package com.tmathmeyer.chan4v2;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class DLWindow extends JFrame{
	public DLWindow(){
		this.setPreferredSize(new Dimension(600, 600));
		this.setLayout(new GridLayout(3,3));
		this.pack();
		this.setVisible(true);
	}
	
	public void addThread(String threadnum, String board, String title){
		this.add(new ChanThread(threadnum, board, title));
	}
	
	public void addThread(String threadnum, String board){
		this.addThread(threadnum, board, threadnum);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args){
		DLWindow dw = new DLWindow();
		dw.add(new ChanThread("443954794", "b", "pr0n [nsfw]"));
		for(int i = 0; i < 1000000; ){
			i++;
		}
		dw.add(new ChanThread("443940734", "b", "cp [nsfw]"));
	}
}
