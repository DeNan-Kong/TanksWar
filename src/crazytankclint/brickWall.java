package crazytankclint;
import java.awt.Graphics;
import java.awt.Image;

import Util.GameUtil;
import Util.Roadblock;
/*
 * ×©Ç½
 */
	public class brickWall extends Roadblock {				
		CrazyTankClint ctc;
		private static Image  img;
		boolean live = true; //Ç½µÄ×´Ì¬
		
		public boolean isLive() {
			return live;
		}
		public void setLive(boolean live) {
			this.live = live;
		}
		public static Image getImg() {
			return img;
		}
		public static void setImg(Image img) {
			brickWall.img = img;
		}
	public brickWall(int x, int y, int width, int height, CrazyTankClint ctc) {		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;
	}
	public brickWall(int x, int y, int width, int height, CrazyTankClint ctc, String imgPath) {		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;
		setImg(GameUtil.getImage(imgPath));
	}
		

	public void draw(Graphics g){
				if( !live){
					if(ctc.brickwalls.size()>0){
						ctc.brickwalls.remove(this);//Çå³ýÈÝÆ÷	
						
					}
					return; 
				}
				if( this.live ){
					g.drawImage(getImg(), x, y, null);			
				}		
		}	
	
	}
