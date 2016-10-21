package crazytankclint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import Util.Direction;
import Util.Contant;
import Util.GameUtil;


public class Bullet {	
	double x;
	double y;
	double spend = 20;
	boolean live =true;//用来定义状态，活或者死
	Direction dir ;
	//持有对方的引用
	CrazyTankClint ctc;
	boolean  good ;//让子弹不打自己

	
	public double getSpend() {
		return spend;
	}
	public void setSpend(double spend) {
		this.spend = spend;
	}
	public boolean isLive() {
		return live;
	}
	public Bullet(double x, double y, Direction dir) {		
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public Bullet(double x, double y, Direction dir,CrazyTankClint ctc) {		
		this(x,y,dir);		
		this.ctc =ctc;				
	}
public Bullet(double x, double y, Direction dir,CrazyTankClint ctc,boolean  good) {		
	    this(x,y,dir,ctc);		
		this.good =good;
	}
	public void Move(){
		switch(dir){
		case U:
			y -= spend;
			break;
		case D:
			y += spend;	
			break;
		case R:
			x += spend;
			break;
		case L:
			x -= spend;		
			break;
		case LU:
			x -= spend;
			y -= spend;		
			break;
		case RU:
			x += spend;
			y -= spend;		
			break;
		case LD:
			x -= spend;
			y += spend;			
			break;
		case RD:
			x += spend;
			y += spend;		
			break;
		default:
			break;
		    }
		if(x<=Contant.bW-5 || x>=Contant.Game_W  - Contant.bW|| y<=Contant.tank_H + 25 || y>= Contant.Game_H  -Contant.bH2){
			live = false;//越界
		}
	}
	//获取子弹所在矩形（碰撞检测）
		public Rectangle getRect(  ){			
			return new Rectangle((int)x,(int)y,Contant.bullet_W,Contant.bullet_H);
		}
		//判断子弹打中
		public boolean HitTank( Tank tank){
			if( this.live &&tank.isLive() && this.getRect().intersects(tank.getRect()) && this.good != tank.isGood()){	//决解自爆 
				if(tank.isGood()){
					tank.setLife(tank.getLife() - 10);
							
					if(tank.getLife() <= 0 ){
						Explode e = new Explode(x,y,ctc);
						ctc.explodes.add(e);
						tank.setLive(false) ;
						this.live = false;
					}
				}else{
					Explode e = new Explode(x,y,ctc); //每次相交生成一个爆炸
					ctc.explodes.add(e);//添加爆炸容器					
					tank.setLive(false) ;  // 坦克死亡
					this.live = false;					
				}				
				return true ;				//相交
			}	
			return false ;
		}


		public boolean HitTanks( List<Tank> tanks){
			for( int i = 0;i<tanks.size();  i++ ){
				if( HitTank( tanks.get(i))){	//如果碰到	
					System.out.println("打到了！！");
					ctc.score += 10;		
				    return true ;				
				}
			}
			return false ;
		}
		public boolean Hitbrickwall( brickWall brickwall ){
			if( this.live && this.getRect().intersects(brickwall.getRect()) ){	 					
				brickwall.setLive(false);
				this.live = false;		
//				Explode e = new Explode(x,y,ctc); //每次相交生成一个爆炸				
//				ctc.explodes.add(e);//添加爆炸容器		
				GameUtil.getSound("D:/eclipse work/CrazyTank/src/sound/hit.wav");
				return true ;				//相交
			}	
			return false ;
		}
		public boolean Hitironwall( ironWall ironwall ){ //子弹不穿透障碍物
			if( this.live && this.getRect().intersects( ironwall.getRect()) ){		
				this.live = false;				
				return true ;				//相交
			}	
			return false ;
		}

	public void drawBullet (Graphics g){ //绘制子弹
		if(!live){
			if(ctc.bullets.size()>0){
				ctc.bullets.remove(this);				
			}
			return;//
		}		
		Color c = g.getColor();
		if(!good){
			g.setColor(new Color(239,23,113));		
		}else{
			g.setColor(new Color(35,128,221));			
		}
		g.fillOval((int)x, (int)y, Contant.bullet_W, Contant.bullet_H);
		g.setColor(c);
		Move();
	}

}
