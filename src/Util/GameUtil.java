package Util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtil { //获取图片路径
	public static Image getImage(String imgPath){
		URL u = GameUtil.class.getClassLoader().getResource(imgPath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	
	@SuppressWarnings("deprecation")
	public static void getSound(String soundPath){
		File f =new File(soundPath);  //引用括号内的绝对路径,音乐
		try {
			URL cb = f.toURL();
			AudioClip aa = Applet.newAudioClip(cb);
			aa.play();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
