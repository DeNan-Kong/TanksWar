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
	boolean live =true;//��������״̬���������
	Direction dir ;
	//���жԷ�������
	CrazyTankClint ctc;
	boolean  good ;//���ӵ������Լ�

	
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
			live = false;//Խ��
		}
	}
	//��ȡ�ӵ����ھ��Σ���ײ��⣩
		public Rectangle getRect(  ){			
			return new Rectangle((int)x,(int)y,Contant.bullet_W,Contant.bullet_H);
		}
		//�ж��ӵ�����
		public boolean HitTank( Tank tank){
			if( this.live &&tank.isLive() && this.getRect().intersects(tank.getRect()) && this.good != tank.isGood()){	//�����Ա� 
				if(tank.isGood()){
					tank.setLife(tank.getLife() - 10);
							
					if(tank.getLife() <= 0 ){
						Explode e = new Explode(x,y,ctc);
						ctc.explodes.add(e);
						tank.setLive(false) ;
						this.live = false;
					}
				}else{
					Explode e = new Explode(x,y,ctc); //ÿ���ཻ����һ����ը
					ctc.explodes.add(e);//��ӱ�ը����					
					tank.setLive(false) ;  // ̹������
					this.live = false;					
				}				
				return true ;				//�ཻ
			}	
			return false ;
		}


		public boolean HitTanks( List<Tank> tanks){
			for( int i = 0;i<tanks.size();  i++ ){
				if( HitTank( tanks.get(i))){	//�������	
					System.out.println("���ˣ���");
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
//				Explode e = new Explode(x,y,ctc); //ÿ���ཻ����һ����ը				
//				ctc.explodes.add(e);//��ӱ�ը����		
				GameUtil.getSound("D:/eclipse work/CrazyTank/src/sound/hit.wav");
				return true ;				//�ཻ
			}	
			return false ;
		}
		public boolean Hitironwall( ironWall ironwall ){ //�ӵ�����͸�ϰ���
			if( this.live && this.getRect().intersects( ironwall.getRect()) ){		
				this.live = false;				
				return true ;				//�ཻ
			}	
			return false ;
		}

	public void drawBullet (Graphics g){ //�����ӵ�
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
