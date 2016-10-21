package crazytankclint;

import java.awt.Graphics;
import java.awt.Image;

import Util.GameUtil;
import Util.Roadblock;

public class Grass extends Roadblock{
	private  Image  img;
	CrazyTankClint ctc;
	
		public Grass( int x, int y, int width, int height, CrazyTankClint ctc ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;		
	}
	public Grass( int x, int y, int width, int height, CrazyTankClint ctc ,String imgPath) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;		
		this.img = GameUtil.getImage(imgPath);
	}

	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}

}
