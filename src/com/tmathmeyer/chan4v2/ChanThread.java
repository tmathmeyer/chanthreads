package com.tmathmeyer.chan4v2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tmathmeyer.chan4v2.WebScanner;
import com.tmathmeyer.chan4v2.queue.EmptyQueue;
import com.tmathmeyer.chan4v2.queue.IQueue;

public class ChanThread extends JPanel implements Runnable{
	public String threadTitle;
	public String board;
	public String threadNumber;
	public int percent;
	public int downloaded;
	public int refreshSeconds = 30;
	public IQueue<ChanImage> images = new EmptyQueue<ChanImage>();
	public ArrayList<String> imageNames = new ArrayList<String>();
	
	
	
	public void run(){
		while(true){
			if (!(images.isEmpty())){
				ChanImage ci = images.peek();
				try{
					ci.downloadImage(threadTitle);
					downloaded+=1;
					updateInfo();
					Thread.sleep(20);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "goddamnit");
					e.printStackTrace();
				}
				images = images.pop();
			}
			else{
				try{
					System.out.println("images empty, waiting one minute");
					Thread.sleep(this.refreshSeconds * 1000);
					this.updateThread();
				}
				catch(Exception e){}
			}
		}
	}
	
	
	private void updateInfo() {
		percent = (downloaded * 100) / imageNames.size();
		System.out.println(this.threadTitle +": "+percent+"%");
		this.repaint();
	}


	public void updateThread(){
		String HTML = WebScanner.readHTML(threadNumber, board);
		ArrayList<ChanImage> cis = WebScanner.addImagesToStack(HTML);
		System.out.println(cis.size());
		this.addImages(cis);
	}
	
	public ChanThread(String num, String board){
		this(num, board, num);
	}
	
	public ChanThread(String num, String board, String title){
		this.board = board;
		this.threadNumber = num;
		this.threadTitle = title;
		this.updateThread();
		this.setPreferredSize(new Dimension(200, 200));
		new Thread(this).start();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		
		g.setColor(Color.green);
		g.fillRect(0, 180, percent*2, 20);
		
		g.setColor(Color.BLACK);
		g.drawString(this.board+"/"+this.threadNumber+":   "+this.threadTitle, 0, 12);
	}
	
	
	public void addImages(ArrayList<ChanImage> cis){
		for(ChanImage ci : cis){
			this.addImage(ci);
		}
	}
	
	public void addImage(ChanImage ci){
		if (!(imageNames.contains(ci.imageURL))){
			images = images.add(ci);
			imageNames.add(ci.imageURL);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
