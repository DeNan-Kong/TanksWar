package crazytankclint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import Util.Contant;
import Util.GameUtil;
import Util.Roadblock;
import Util.Direction;

/*
 * ̹��ģ����
 */

public class Tank {
	
	double x ;
	double y ;
	double spend ;
	boolean good ;//�������
	int bulletspeed = 90;
	public boolean isGood() {
		return good;
	}
	private boolean live = true;  //��������̹�˵�״̬���������
	public boolean isLive() {
		return live;
	}
	int life = Contant.Life;
	Blood blood = new Blood();//Ѫ������
	int bulletNum = Contant.bulletNum;
	int lifenum = 3;
	
	//get��set���췽��	
	public int getBulletNum() {
		return bulletNum;
	}
	public void setBulletNum(int bulletNum) {
		this.bulletNum = bulletNum;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}	
	public double getSpend() {
		return spend;
	}
	public void setSpend(double spend) {
		this.spend = spend;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public double getXoflastStap() {    
		return xoflastStap;
	}
	public void setXoflastStap(double xoflastStap) {
		this.xoflastStap = xoflastStap;
	}
	public double getYoflastStap() {
		return yoflastStap;
	}
	public void setYoflastStap(double yoflastStap) {
		this.yoflastStap = yoflastStap;
	}
	private double xoflastStap;  //��¼��һ����λ��
	private double yoflastStap;

	boolean bL,bR,bU,bD;
	Direction dir = Direction.Stop;//̹�˷���
	Direction dir_paotou =Direction.D;//��ͷ����
	//���������
	private static Random r = new Random();//˽�о�̬������̹���๲��
	int step = r.nextInt(10)*5 + 3;

	//���жԷ�������
	CrazyTankClint ctc;
	
	public Tank(double x, double y ) {// ����1	
		this.x = x;
		this.y = y;	
		this.xoflastStap = (int) x;
		this.yoflastStap = (int) y;
	}
	public Tank(double x, double y ,CrazyTankClint ctc) {	//����2
		this(x,y);
		this.ctc = ctc;
	}
	public Tank(double x, double y ,double spend,CrazyTankClint ctc,boolean good) {	//����3
		this(x,y,ctc);
		this.spend = spend;
		this.good = good;
	}
	public Tank(double x, double y ,double spend,CrazyTankClint ctc,boolean good,Direction dir) {	//����4
		this(x,y,spend,ctc,good);		
		this.dir =dir;
	}
	int P;//���P1 or P2
	public Tank(double x, double y ,double spend,CrazyTankClint ctc,boolean good,int P) {	//����5 ,�ж����
		this(x,y,spend,ctc,good);
		this.P = P;
	}
		
	public void Move( ){ 	
		//��¼�ƶ�ǰ��ÿһ�ε�λ��
		this.xoflastStap = x;
		this.yoflastStap = y;

			switch(dir){
			case U:
				if((int)y <= Contant.tank_H + 30){//Y��߽�
					dir = Direction.Stop;
			}else {				    						
				y -= spend;
			}	
				break;
			case D:
				if((int)y >= Contant.Game_H - Contant.tank_H -Contant.bH2){
					dir = Direction.Stop;
			}else {			
				y += spend;						
			}	
				break;
			case R:
				if((int)x >= Contant.Game_W - Contant.tank_W - Contant.bW ){//X��߽�
					dir = Direction.Stop;
				}else {						
				x += spend;
				}
				break;
			case L:
				if((int)x <= Contant.bW){
					dir = Direction.Stop;
				}else {				    						
				x -= spend;					
				}			
				break;
			case LU:
				if((int)x <= Contant.bW || (int)y <= Contant.tank_H + 30){
					dir = Direction.Stop;;
			}else {				    						
				x -= spend/Math.sqrt(2);
				y -= spend/Math.sqrt(2);
			}			
				break;
			case RU:
				if((int)x >= Contant.Game_W - Contant.tank_W - Contant.bW || (int)y <= Contant.tank_H + 30){
					dir = Direction.Stop;
			}else {				    						
				x += spend/Math.sqrt(2);
				y -= spend/Math.sqrt(2);
			}			
				break;
			case LD:
				if((int)x <=Contant.bW || (int)y >= Contant.Game_H - Contant.tank_H - Contant.bH2){
					dir = Direction.Stop;
			}else {				
				x -= spend/Math.sqrt(2);//�����ٶ�ͬ������
				y += spend/Math.sqrt(2);
			}			
				break;
			case RD:
				if((int)x >= Contant.Game_W - Contant.tank_W  -  Contant.bW || (int)y >= Contant.Game_H - Contant.tank_H - Contant.bH2){
					dir = Direction.Stop;
			}else {				    						
				x += spend/Math.sqrt(2);
				y += spend/Math.sqrt(2);
			}
				break;
			default:
				break;
			}
			if(dir != Direction.Stop){
				this.dir_paotou = this.dir;
			}
			if(!good){ //�з����·��
				Direction [] dirs = Direction.values();
				if(step == 0){
					for(int i = 0;i<dirs.length;i++){
						int randomNum = r.nextInt(dirs.length);
						dir = dirs [randomNum ];//��һ���������
					}
					step = r.nextInt(10)*5 +3;
				}
				step--;
				if(r.nextInt(100)>this.bulletspeed){
					ctc.bullets.add( fire() );//���Ƶз��ӵ�����Ŀ					
				}
			}
	}

	//������ͷ
	public void drawPaoTou(Graphics g){
	
		switch(dir_paotou){
		case U:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_U.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_U.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_U.gif"), (int)x, (int)y, null);
			}
			break;
		case D:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_D.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_D.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_D.gif"), (int)x, (int)y, null);
			}
			break;
		case R:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_R.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_R.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_R.gif"), (int)x, (int)y, null);
			}
			break;
		case L:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_L.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_L.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_L.gif"), (int)x, (int)y, null);
			}
			break;
		case LU:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_LU.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_LU.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_LU.gif"), (int)x, (int)y, null);
			}
			break;
		case RU:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_RU.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_RU.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_RU.gif"), (int)x, (int)y, null);
			}
			break;
		case LD:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_LD.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_LD.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_LD.gif"), (int)x, (int)y, null);
			}
			break;
		case RD:
			if( good&&P == 1){
				g.drawImage( GameUtil.getImage("images/enemy04_RD.png"), (int)x, (int)y, null);
			}
			if( good&&P == 2){
				g.drawImage( GameUtil.getImage("images/enemy03_RD.gif"), (int)x, (int)y, null);
			}
			if( !good ){
				g.drawImage( GameUtil.getImage("images/enemy01_RD.gif"), (int)x, (int)y, null);
			}
			break;
		default:
			break;
		    }
	}
	//�ж��Ƿ�״̬
	public void KeyPressed_P1 (KeyEvent e){
		int Key = e.getKeyCode();
		switch (Key) {
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;		
		case KeyEvent.VK_SPACE:		//�ո��	
			if( this.bulletNum != 0){
				GameUtil.getSound("D:/eclipse work/CrazyTank/src/sound/firebullet.wav");				
				fire();//��fire���ص��ӵ�b��������
			}
			break;
		case KeyEvent.VK_F3:		//F3����		
			if(this.lifenum>0){
				if(!live ){
					this.setLife(Contant.Life);
					this.setLive(true);
					this.setBulletNum(Contant.bulletNum);
					this.lifenum--;				
				}				
			}
			break;
		case KeyEvent.VK_F1:
			ctc.seclect1 =true;	
			ctc.seclect2 =false;	
			break;
		case KeyEvent.VK_F2:
			ctc.seclect2 =true;	
			ctc.seclect1 =false;	
			break;
			
		case KeyEvent.VK_ENTER:
			 if(ctc.P1_tank.lifenum==0&&ctc.P2_tank.lifenum==0){
				 ctc.start = false;
			 }
			 ctc.start= true;	
			break;	
		default:
			break;
		}
		UseDirection();
	}
	public void keyReleased_P1 (KeyEvent e){
		int Key = e.getKeyCode();
		switch (Key) {
		case KeyEvent.VK_W:
			bU = false ;
			break;
		case KeyEvent.VK_A:
			bL =  false ;
			break;
		case KeyEvent.VK_D:
			bR =  false ;
			break;
		case KeyEvent.VK_S:
			bD =  false ;				
			break;	
		default:
			break;
		}
		UseDirection();//���÷����ж�
	}
	public void KeyPressed_P2 (KeyEvent e){
		int Key = e.getKeyCode();
		switch (Key) {
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;		
		case KeyEvent.VK_SLASH :		//  "/"����	
			if( this.bulletNum != 0){
				GameUtil.getSound("D:/eclipse work/CrazyTank/src/sound/firebullet.wav");				
				fire();//��fire���ص��ӵ�b��������
			}
			break;
		case KeyEvent.VK_F3:		//F3����
			if(this.lifenum>0){
				if(!live ){
					this.setLife(Contant.Life);
					this.setLive(true);
					this.setBulletNum(Contant.bulletNum);
					this.lifenum--;				
				}				
			}
			break;
		default:
			break;
		}
		UseDirection();
	}
	public void keyReleased_P2 (KeyEvent e){
		int Key = e.getKeyCode();
		switch (Key) {
		case KeyEvent.VK_UP:
			bU = false ;
			break;
		case KeyEvent.VK_LEFT:
			bL =  false ;
			break;
		case KeyEvent.VK_RIGHT:
			bR =  false ;
			break;
		case KeyEvent.VK_DOWN:
			bD =  false ;				
			break;			
		default:
			break;
		}
		UseDirection();//���÷����ж�
	}
	
	//direction �ж�
	public void UseDirection( ){
		if (!bD && !bL && !bR && bU) dir = Direction.U;
		else if (!bD && bL && !bR && bU) dir = Direction.LU;
		else if (!bD && !bL && bR && bU) dir = Direction.RU;
		else if (bD && !bL && !bR && !bU) dir = Direction.D;
		else if (bD && bL && !bR && !bU) dir = Direction.LD;
		else if (bD && !bL && bR && !bU) dir = Direction.RD;
		else if (!bD && bL && !bR && !bU) dir = Direction.L;
		else if (!bD && !bL && bR && !bU) dir = Direction.R;
		else   dir = Direction.Stop;	
	}
	//��ȡ̹�����ھ��Σ���ײ��⣩
	public Rectangle getRect(  ){
		Rectangle r = new Rectangle((int)x,(int)y,Contant.tank_W,Contant.tank_H);
		return r;
	}
	public boolean collideswithtank(List<Tank> tanks ){ //�Ӵ��Լ�����
		for( int i =0; i < tanks.size(); i++){
			Tank tank = tanks.get(i);
			if( this !=tank){
					if(this.live && this.getRect().intersects(tank.getRect()) ){  //�ཻ
						x = this.xoflastStap;  //���ײ�ϣ��ص���һ��
						y = this.yoflastStap;
						return true ;				
				}	
			}				
		}
		return false ;
	}
	public boolean collideswithwall(Roadblock wall){//�Ӵ�ǽ����
		if( this.live &&  this.getRect().intersects(wall.getRect()) ){  //�ཻ
			 x = this.xoflastStap;  //���ײ�ϣ��ص���һ��
			 y = this.yoflastStap;
			return true ;				
		}	
		return false ;
	}
	public boolean collideswithgrass(Roadblock grass){//�Ӵ��ݵط���		
		if(good){//���̹�˼���
			if(this.live && this.getRect().intersects(grass.getRect()) ){  //�ཻ
				System.out.println("���٣���");
				this.spend = Contant.P_spend/2;
//				System.out.println(spend);
				return true ;				
			}	
			this.spend = Contant.P_spend;
			return false ;			
		}else{ //����з�̹�˼���
			if(this.live && this.getRect().intersects(grass.getRect()) ){  //�ཻ
				this.spend = Contant.spend/2;
				return true ;				
			}	
			this.spend = Contant.spend;
			return false ;			
		}			
	}
	
	public boolean collideswithIce(Roadblock ice){//�Ӵ�ice�ط���		
		if(!good){ //����з�̹�˼���
			if( this.live && this.getRect().intersects(ice.getRect()) ){  //�ཻ				
				this.spend = Contant.spend*2;
				return true ;				
			}
			for(  ;this.spend >= Contant.spend;  ) this.spend -= 0.1 ;				
			return false ;			
		}
		//���̹�˼���
		if( this.live && this.getRect().intersects(ice.getRect()) ){  //�ཻ
			System.out.println("�Լ����٣���");
			this.spend = Contant.P_spend*2;
			return true ;				
		}
		for(  ;this.spend >= Contant.P_spend;  ) this.spend -= 0.1 ;	
		return false ;	
	}	
	
	public boolean eatblood(crazytankclint.Blood blood){//�Ӵ�blood�ط���		
		if(good){ 
			if(this.live &&  this.getRect().intersects(blood.getRect()) ){  //?
				if(this.life<Contant.Life ){
					System.out.println( "��Ѫ");
					this.setLife(this.life+20) ;
					return true;
				}if( this.life >= Contant.Life){
					this.setLife(Contant.Life);
					return true ;				
				}
			}						
		}		
		return false ;			
	}
	public boolean eatbulletNum(crazytankclint.BulletsNum bulletsNum){	
		if(good){ 
			if(this.live &&  this.getRect().intersects(bulletsNum.getRect()) ){  //?
				if(this. bulletNum<Contant.bulletNum ){
					System.out.println( "�ӵ���10");
					this.setBulletNum(bulletNum + 10) ;					
				}if( this.bulletNum >= Contant.bulletNum ){
					this.bulletNum = Contant.bulletNum;
				}
				return true ;				
			}						
		}		
		return false ;			
	}

	public void drawTank (Graphics g){ // ����̹��
		if( !live){
			if(ctc.enemy_tanks.size()>0){
				ctc.enemy_tanks.remove(this);//�������				
			}
			this.setBulletNum(0);
			return; 
		}
		if(ctc.enemy_tanks.size() == 0){
			g.drawString("��ϲ������һ�أ�" , 480, 60);
			ctc.guanka +=1;
			for( int i = 0;i <Contant.enemt_tank; i ++){			
				ctc.enemy_tanks.add(new Tank( 60+ 80*i , 600 ,Contant.spend, ctc,false,Util.Direction.Stop));   //����������ӵз�̹��	
				if(this.bulletspeed >0){
					this.bulletspeed -=10;//�����Ѷ�			
				}
				for(int n = 0; n< ctc.enemy_tanks.size();n++){
					ctc.enemy_tanks.get(n).setSpend(Contant.spend+n*5);
				}
			}
		}
		if(good == true){
			if(P == 1){
				blood.drawBlood(g);//�����ڲ��෽��
			}else if (P ==2) {
				blood.drawBlood(g);//�����ڲ��෽��
			} 									
			}else{			
		}
		if(bulletNum == 0){
			g.drawString("����ӵ�ҩ���� ",(int) x , (int)y + 40);
		}
		blood.drawBlood(g);
		drawPaoTou(g);
		Move( );
	}	
	
	//���𷽷�
	public Bullet fire ( ){
		
		if( !live){
			return  null; 
		}	
//		if(this.getBulletNum()<= 0 ){ return null; }
		double x, y;
		x = this.x + Contant.tank_W/2 - Contant.bullet_W/2; //�ڵ���λ��Ϊ����
		y = this.y + Contant.tank_H/2 - Contant.bullet_H/2;
		Bullet b = new Bullet(x , y , dir_paotou, ctc, good);//������ͷ����ȷ���ӵ�����.����������,���ӵ�����good
		ctc.bullets.add( b);
		bulletNum --;
		return b;		
	}

	private class Blood{//�ڲ���
		public void drawBlood(Graphics g){	 //��Ѫ��
			Color c = g.getColor();
			g.setColor(new Color(255,20,20));
			g.drawRect( (int)x  , (int)y -5 ,  Contant.tank_W, 5);
			g.fillRect( (int)x , (int)y - 5, (Contant.tank_W * Tank.this.life/100) ,6);
			g.setColor(c);
		}			
	}

}
