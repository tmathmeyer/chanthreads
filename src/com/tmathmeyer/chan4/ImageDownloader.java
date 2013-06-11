package com.tmathmeyer.chan4;

import java.util.ArrayList;

import com.tmathmeyer.chan4.ui.ChanGUI;
import com.tmathmeyer.chan4.ui.DLBar;

public class ImageDownloader implements Runnable {

	ArrayList<ChanImage> ci;
	boolean type;
	ChanGUI cgi;
	
	public ImageDownloader(ArrayList<ChanImage> ci, boolean type, ChanGUI cgi){
		this.ci = ci;
		this.type = type;
		this.cgi = cgi;
	}
	
	public void run() {
		this.dlImages(ci, cgi);
	}

	
	public void dlImages(ArrayList<ChanImage> imagelist, ChanGUI cgi){
		try{
			for(ChanImage ci : imagelist){
				System.out.println(ci);
				if (!(this.type==WebScanner.op && !ci.isOP(WebScanner.OPID)))
					try{
						ci.downloadImage(WebScanner.postID);
						cgi.setNextImage(true);
					}
					catch(Exception e){cgi.setNextImage(false);}
			}
		}
		catch(Exception e){e.printStackTrace();}
	}
}
