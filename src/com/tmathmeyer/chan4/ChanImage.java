package com.tmathmeyer.chan4;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class ChanImage {
	public String posterID;
	public String imageURL;
	
	public ChanImage(String a, String b){
		this.posterID = a;
		this.imageURL = b;
	}
	
	public void downloadImage(String postID) throws Exception{
		BufferedImage image = null;
		File f = new File(postID+"/"+imageURL.split("/")[3]);
		if (f.exists())return;
		URL url = new URL("http://"+imageURL);
		java.net.URLConnection c = url.openConnection();
	    c.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		InputStream in = new BufferedInputStream(c.getInputStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		   out.write(buf, 0, n);
		out.close();
		in.close();
		byte[] response = out.toByteArray();
		File fol = new File(postID+"");
		fol.mkdir();
		FileOutputStream fos = new FileOutputStream(postID+"/"+imageURL.split("/")[3]);
	    fos.write(response);
	    fos.close();
	}
	
	
	public String toString(){
		return this.posterID+"   "+this.imageURL;
	}
	
	public boolean isOP(String opCode){
		return this.posterID.equals(opCode);
	}
}
