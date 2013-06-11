package com.tmathmeyer.chan4v2;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ChanGUI extends JPanel implements ActionListener, ItemListener{
	JTextField threadNumber;
	JTextField threadName;
	JCheckBox op;
	JComboBox<String> boards;
	JButton checkExists;
	JButton download;
	
	DLWindow dl = new DLWindow();
	
	int size = 100;
	
	boolean OP = false;
	String[] boardList = {"a","b","c","d","e","f","g","gif","h","hr","k","m","o","p","r","s","t","u","v","vg","w","wg","i","ic","r9k","cm","hm","y","3","adv","an","cgl","ck","co","diy","fa","fit","hc","int","jp","lit","mlp","mu","n","po","pol","sci","soc","sp","tg","toy","trv","tv","vp","wsg","x"};
	
	public ChanGUI(){
		this.threadNumber = new JTextField(9);
		this.threadName = new JTextField("faggotry");
		this.op = new JCheckBox("op images only?");
		boards = new JComboBox<String>(boardList);
		boards.setSelectedIndex(1);
		checkExists = new JButton("check 404");
		download = new JButton("download images");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(threadNumber);
		this.add(threadName);
		this.add(boards);
		this.add(op);
		this.add(download);
		this.setPreferredSize(new Dimension(600,size));
		
		op.addItemListener(this);
		download.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (download.equals(source)){
			dl.addThread(this.threadNumber.getText(), boards.getSelectedItem().toString(), this.threadName.getText());
		}
	}
	
	public void itemStateChanged(ItemEvent e){
		OP = !(e.getStateChange() == ItemEvent.DESELECTED);
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(1,1));
		f.add(new ChanGUI());
		f.pack();
		f.setVisible(true);
	}
}
