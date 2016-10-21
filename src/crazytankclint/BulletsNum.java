package crazytankclint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Util.Contant;

public class BulletsNum {
	 int x;
	 int y;
	 int width;
	 int height;
	 CrazyTankClint ctc;
	//���������
		private static Random N = new Random();//˽�о�̬������̹���๲��
		int step = N.nextInt(10);
  

	 public BulletsNum( int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;				
		}		
		public BulletsNum( int x, int y, int width, int height, CrazyTankClint ctc) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.ctc = ctc;		
		}
		
		public Rectangle getRect(  ){				
			return new Rectangle (x, y,Contant.tank_W,Contant.tank_H);
		}
		
		public void drawBulletsNum(Graphics g){ //���Ѫ����δ���⣿
			
			Color c = g.getColor();
			g.setColor(Color.black);				
			g.drawRect( x, y, Contant.tank_W, Contant.tank_H);
			g.fillRect(x+10, y, 10, Contant.tank_H);
			g.fillRect(x, y+10, Contant.tank_W, 10);
			g.setColor(c);
			}	

}
