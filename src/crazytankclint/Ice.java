package crazytankclint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import Util.GameUtil;
import Util.Roadblock;

public class Ice extends Roadblock{
	private  Image  img;
	CrazyTankClint ctc;
	public Ice( int x, int y, int width, int height, CrazyTankClint ctc) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;		
	}
	public Ice( int x, int y, int width, int height, CrazyTankClint ctc, String  imgPath) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;	
		this.img = (GameUtil.getImage(imgPath));
	}
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(new Color(178,255,255));
		g.fillRect(x, y, width, height);	
		g.setColor(c);
		g.drawImage(img, x, y, null);
		}
	
}
