package com.tmathmeyer.chan4v2;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class WebScanner {
	
	public static final boolean op = true;
	public static final boolean all = false; 
	public static String OPID = "";
	public static String postID = "";
	
	
	public static ArrayList<ChanImage> addImagesToStack(String HTML){
		HTML = HTML.split("<div class=\"thread\"")[1];
		String[] posts = HTML.split("<span class=\"nameBlock\">");
		ArrayList<ChanImage> imagelist = new ArrayList<ChanImage>();
		
		for(int i = 0; i < posts.length; i++){
			String curr = posts[i];
			if (i==0){
				try{OPID = curr.split("id_")[1].split("\"")[0];}catch(Exception e){OPID = "op";}
			}
			else{
				try{
					String posterID = "op";
					try{
						posterID = curr.split("Highlight posts by this ID\">")[1];
					}
					catch(Exception e){}
					posterID = posterID.split("</span>")[0];
					
					String usefulText = curr.split("a href=\"//")[1];
					String imageURL = usefulText.split("\"")[0];
					
					if (!imageURL.contains("quantserve"))imagelist.add(new ChanImage(posterID, imageURL));
				}
				catch(Exception e){}
			}
		}
		System.out.println("done parsing thread");
		return imagelist;
	}
	
	
	public static String readHTML(String postID, String board){
		String html = "";
		String line = "";
		WebScanner.postID = postID;
		try {
			String u = "https://boards.4chan.org/"+board+"/res/"+postID;
			System.out.println(u);
		    URL url = new URL(u);
		    java.net.URLConnection c = url.openConnection();
		    c.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		    InputStream is = c.getInputStream();
		    DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
		    while ((line = dis.readLine()) != null){
		        html+=line;
		    }
		}
		catch (Exception mue) {mue.printStackTrace();}
		System.out.println("done downloading thread");
		return html;
	}
	
	
	public static ArrayList<ChanImage> addImagesToStack(String postID, String board){
		return addImagesToStack(readHTML(postID, board));
	}
}

