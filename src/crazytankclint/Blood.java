package crazytankclint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Util.Contant;

public class Blood {
	 int x;
	 int y;
	 int width;
	 int height;
	 CrazyTankClint ctc;
	//随机生成数
		private static Random N = new Random();//私有静态，所有坦克类共享
		int step = N.nextInt(10);
  

	 public Blood( int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;				
		}		
		public Blood( int x, int y, int width, int height, CrazyTankClint ctc) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.ctc = ctc;		
		}
		
		public Rectangle getRect(  ){				
			return new Rectangle (x, y,Contant.tank_W,Contant.tank_H);
		}
		
		public void drawBloods(Graphics g){ //随机血包，未决解？
			
			Color c = g.getColor();
			g.setColor(new Color(247,89,89));				
			g.drawRect( x, y, Contant.tank_W, Contant.tank_H);
			g.fillRect(x+10, y, 10, Contant.tank_H);
			g.fillRect(x, y+10, Contant.tank_W, 10);
			g.setColor(c);
			}	

}
