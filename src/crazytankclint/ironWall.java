package crazytankclint;
import java.awt.Graphics;
import java.awt.Image;

import Util.GameUtil;
import Util.Roadblock;
/*
 * ��ǽ
 */
	public class ironWall extends Roadblock {				
		CrazyTankClint ctc;
		private static Image  img;
		boolean live = true; //ǽ��״̬		
		
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
			ironWall.img = img;
		}
	public ironWall(int x, int y, int width, int height, CrazyTankClint ctc) {		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;
	}
	public ironWall(int x, int y, int width, int height, CrazyTankClint ctc, String imgPath) {		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ctc = ctc;
		setImg(GameUtil.getImage(imgPath));
	}
		

	public void draw(Graphics g){
				if( !live){
					ctc.brickwalls.remove(this);//�������					
					return; 
				}
				if( this.live ){
					g.drawImage(getImg(), x, y, null);			
				}		
		}	
	
	}
