package com.tmathmeyer.chan4.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tmathmeyer.chan4.ChanImage;
import com.tmathmeyer.chan4.ImageDownloader;
import com.tmathmeyer.chan4.WebScanner;

public class ChanGUI extends JPanel implements ActionListener, ItemListener{
	JTextField threadNumber;
	JCheckBox op;
	JComboBox boards;
	JButton checkExists;
	JButton download;
	public DLBar dl;
	
	int size = 100;
	
	boolean OP = false;
	String[] boardList = {"a","b","c","d","e","f","g","gif","h","hr","k","m","o","p","r","s","t","u","v","vg","w","wg","i","ic","r9k","cm","hm","y","3","adv","an","cgl","ck","co","diy","fa","fit","hc","int","jp","lit","mlp","mu","n","po","pol","sci","soc","sp","tg","toy","trv","tv","vp","wsg","x"};
	
	public ChanGUI(){
		this.threadNumber = new JTextField(9);
		this.op = new JCheckBox("op images only?");
		boards = new JComboBox(boardList);
		boards.setSelectedIndex(1);
		checkExists = new JButton("check 404");
		download = new JButton("download images");
		dl = new DLBar(1);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(threadNumber);
		this.add(boards);
		this.add(op);
		this.add(download);
		//this.add(dl);
		this.setPreferredSize(new Dimension(600,size));
		
		op.addItemListener(this);
		download.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (download.equals(source)){
			String html = WebScanner.readHTML(this.threadNumber.getText(), (String)boards.getSelectedItem());
			ArrayList<ChanImage> images = WebScanner.addImagesToStack(html);
			dl = new DLBar(images.size());
			this.size+=10;
			this.setPreferredSize(new Dimension(600,size));
			this.add(dl);
			repaint();
			Thread th = new Thread(new ImageDownloader(images, OP, this));
			th.start();
			this.setVisible(true);
			Test.f.pack();
			Test.f.setVisible(true);
		}
	}
	
	public void itemStateChanged(ItemEvent e){
		OP = !(e.getStateChange() == ItemEvent.DESELECTED);
	}
	
	public void setNextImage(boolean b){
		this.dl.setNextImage(b);
		repaint();
	}
}
