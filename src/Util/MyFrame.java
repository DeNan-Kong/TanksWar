package Util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void launchFrame(){
		this.setSize(Contant.Game_W, Contant.Game_H);
		
		this.setBackground(new Color(255,220,220));
		this.setLocation(150, 20);
		this.setVisible(true);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e ){
				System.exit(0);
			}
		});
	}  
	//双缓冲方法决解图片闪烁
		Image backImg = null;	
		public void update(Graphics g) {
			if(backImg == null){
				backImg = createImage(Contant.Game_W,Contant.Game_H);
			}
			Graphics back = backImg.getGraphics();
			Color c = back.getColor();
			back.setColor(new Color(255,220,220));
			back.fillRect(0, 0,Contant.Game_W, Contant.Game_H);
			back.setColor(c); //back背景画板
			paint(back);
			g.drawImage(backImg, 0, 0, null); //backImg一次性画板	
		}
		//实现多线程实现重复调用
		public class MyThread extends Thread{
			public void run(){
				while(true){
					repaint();				
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {					
						e.printStackTrace();
					}
				}			
			}
		}

}
