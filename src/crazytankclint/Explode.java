package crazytankclint;

import java.awt.Graphics;
import java.awt.Image;

import Util.GameUtil;

/*
 * 爆炸类
 */
public class Explode {
	double x;
	double y;
	int step = 0;//步骤变量
	boolean live =true;
	static Image img1 = GameUtil.getImage("images/1.png");	
	static Image img2 = GameUtil.getImage("images/2.png");
	static Image img3 = GameUtil.getImage("images/3.png");
	static Image img4 = GameUtil.getImage("images/4.png");
	static Image img5 = GameUtil.getImage("images/5.png");
	static Image img6 = GameUtil.getImage("images/6.png");
	static Image img7 = GameUtil.getImage("images/7.png");
	static Image img8 = GameUtil.getImage("images/8.png");

	static Image[ ] Images ={ img1, img2,img3,img4,img5,img6,img7,img8};
	
	public Explode(double x, double y) {		//构造1
		this.x = x;
		this.y = y;		
	}
	CrazyTankClint ctc;
	public Explode(double x, double y,CrazyTankClint ctc) {		//构造2
		this.x = x;
		this.y = y;		
		this. ctc = ctc;
	}
	//绘制爆炸

	public void drawExplode(Graphics g){
		if(!live) {
			ctc.explodes.remove(this);//释放爆炸容器
			return;
		}
		g.drawImage( Images[step], (int)x, (int)y, null);
		GameUtil.getSound( "D:/eclipse work/CrazyTank/src/sound/baozha.wav");
		step ++;			
		if( step > Images.length - 1){
			step = 0;
			live = false;
			return;
		}
		
	}

}
