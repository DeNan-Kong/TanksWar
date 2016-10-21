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
 * 坦克模型类
 */

public class Tank {
	
	double x ;
	double y ;
	double spend ;
	boolean good ;//定义敌我
	int bulletspeed = 90;
	public boolean isGood() {
		return good;
	}
	private boolean live = true;  //用来定义坦克的状态，活或者死
	public boolean isLive() {
		return live;
	}
	int life = Contant.Life;
	Blood blood = new Blood();//血条对象
	int bulletNum = Contant.bulletNum;
	int lifenum = 3;
	
	//get，set构造方法	
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
	private double xoflastStap;  //记录上一步的位置
	private double yoflastStap;

	boolean bL,bR,bU,bD;
	Direction dir = Direction.Stop;//坦克方向
	Direction dir_paotou =Direction.D;//炮头方向
	//随机生成数
	private static Random r = new Random();//私有静态，所有坦克类共享
	int step = r.nextInt(10)*5 + 3;

	//持有对方的引用
	CrazyTankClint ctc;
	
	public Tank(double x, double y ) {// 重载1	
		this.x = x;
		this.y = y;	
		this.xoflastStap = (int) x;
		this.yoflastStap = (int) y;
	}
	public Tank(double x, double y ,CrazyTankClint ctc) {	//重载2
		this(x,y);
		this.ctc = ctc;
	}
	public Tank(double x, double y ,double spend,CrazyTankClint ctc,boolean good) {	//重载3
		this(x,y,ctc);
		this.spend = spend;
		this.good = good;
	}
	public Tank(double x, double y ,double spend,CrazyTankClint ctc,boolean good,Direction dir) {	//重载4
		this(x,y,spend,ctc,good);		
		this.dir =dir;
	}
	int P;//玩家P1 or P2
	public Tank(double x, double y ,double spend,CrazyTankClint ctc,boolean good,int P) {	//重载5 ,判断玩家
		this(x,y,spend,ctc,good);
		this.P = P;
	}
		
	public void Move( ){ 	
		//记录移动前，每一次的位置
		this.xoflastStap = x;
		this.yoflastStap = y;

			switch(dir){
			case U:
				if((int)y <= Contant.tank_H + 30){//Y轴边界
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
				if((int)x >= Contant.Game_W - Contant.tank_W - Contant.bW ){//X轴边界
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
				x -= spend/Math.sqrt(2);//决解速度同步问题
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
			if(!good){ //敌方随机路径
				Direction [] dirs = Direction.values();
				if(step == 0){
					for(int i = 0;i<dirs.length;i++){
						int randomNum = r.nextInt(dirs.length);
						dir = dirs [randomNum ];//给一个随机方向
					}
					step = r.nextInt(10)*5 +3;
				}
				step--;
				if(r.nextInt(100)>this.bulletspeed){
					ctc.bullets.add( fire() );//控制敌方子弹的数目					
				}
			}
	}

	//绘制炮头
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
	//判断是否状态
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
		case KeyEvent.VK_SPACE:		//空格键	
			if( this.bulletNum != 0){
				GameUtil.getSound("D:/eclipse work/CrazyTank/src/sound/firebullet.wav");				
				fire();//将fire返回的子弹b加入容器
			}
			break;
		case KeyEvent.VK_F3:		//F3复活		
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
		UseDirection();//调用方向判定
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
		case KeyEvent.VK_SLASH :		//  "/"按键	
			if( this.bulletNum != 0){
				GameUtil.getSound("D:/eclipse work/CrazyTank/src/sound/firebullet.wav");				
				fire();//将fire返回的子弹b加入容器
			}
			break;
		case KeyEvent.VK_F3:		//F3复活
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
		UseDirection();//调用方向判定
	}
	
	//direction 判断
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
	//获取坦克所在矩形（碰撞检测）
	public Rectangle getRect(  ){
		Rectangle r = new Rectangle((int)x,(int)y,Contant.tank_W,Contant.tank_H);
		return r;
	}
	public boolean collideswithtank(List<Tank> tanks ){ //接触自己方法
		for( int i =0; i < tanks.size(); i++){
			Tank tank = tanks.get(i);
			if( this !=tank){
					if(this.live && this.getRect().intersects(tank.getRect()) ){  //相交
						x = this.xoflastStap;  //如果撞上，回到上一步
						y = this.yoflastStap;
						return true ;				
				}	
			}				
		}
		return false ;
	}
	public boolean collideswithwall(Roadblock wall){//接触墙方法
		if( this.live &&  this.getRect().intersects(wall.getRect()) ){  //相交
			 x = this.xoflastStap;  //如果撞上，回到上一步
			 y = this.yoflastStap;
			return true ;				
		}	
		return false ;
	}
	public boolean collideswithgrass(Roadblock grass){//接触草地方法		
		if(good){//玩家坦克减速
			if(this.live && this.getRect().intersects(grass.getRect()) ){  //相交
				System.out.println("减速！！");
				this.spend = Contant.P_spend/2;
//				System.out.println(spend);
				return true ;				
			}	
			this.spend = Contant.P_spend;
			return false ;			
		}else{ //如果敌方坦克减速
			if(this.live && this.getRect().intersects(grass.getRect()) ){  //相交
				this.spend = Contant.spend/2;
				return true ;				
			}	
			this.spend = Contant.spend;
			return false ;			
		}			
	}
	
	public boolean collideswithIce(Roadblock ice){//接触ice地方法		
		if(!good){ //如果敌方坦克加速
			if( this.live && this.getRect().intersects(ice.getRect()) ){  //相交				
				this.spend = Contant.spend*2;
				return true ;				
			}
			for(  ;this.spend >= Contant.spend;  ) this.spend -= 0.1 ;				
			return false ;			
		}
		//玩家坦克加速
		if( this.live && this.getRect().intersects(ice.getRect()) ){  //相交
			System.out.println("自己加速！！");
			this.spend = Contant.P_spend*2;
			return true ;				
		}
		for(  ;this.spend >= Contant.P_spend;  ) this.spend -= 0.1 ;	
		return false ;	
	}	
	
	public boolean eatblood(crazytankclint.Blood blood){//接触blood地方法		
		if(good){ 
			if(this.live &&  this.getRect().intersects(blood.getRect()) ){  //?
				if(this.life<Contant.Life ){
					System.out.println( "加血");
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
					System.out.println( "子弹加10");
					this.setBulletNum(bulletNum + 10) ;					
				}if( this.bulletNum >= Contant.bulletNum ){
					this.bulletNum = Contant.bulletNum;
				}
				return true ;				
			}						
		}		
		return false ;			
	}

	public void drawTank (Graphics g){ // 绘制坦克
		if( !live){
			if(ctc.enemy_tanks.size()>0){
				ctc.enemy_tanks.remove(this);//清除容器				
			}
			this.setBulletNum(0);
			return; 
		}
		if(ctc.enemy_tanks.size() == 0){
			g.drawString("恭喜您到下一关！" , 480, 60);
			ctc.guanka +=1;
			for( int i = 0;i <Contant.enemt_tank; i ++){			
				ctc.enemy_tanks.add(new Tank( 60+ 80*i , 600 ,Contant.spend, ctc,false,Util.Direction.Stop));   //在容器中添加敌方坦克	
				if(this.bulletspeed >0){
					this.bulletspeed -=10;//增加难度			
				}
				for(int n = 0; n< ctc.enemy_tanks.size();n++){
					ctc.enemy_tanks.get(n).setSpend(Contant.spend+n*5);
				}
			}
		}
		if(good == true){
			if(P == 1){
				blood.drawBlood(g);//调用内部类方法
			}else if (P ==2) {
				blood.drawBlood(g);//调用内部类方法
			} 									
			}else{			
		}
		if(bulletNum == 0){
			g.drawString("请添加弹药！！ ",(int) x , (int)y + 40);
		}
		blood.drawBlood(g);
		drawPaoTou(g);
		Move( );
	}	
	
	//开火方法
	public Bullet fire ( ){
		
		if( !live){
			return  null; 
		}	
//		if(this.getBulletNum()<= 0 ){ return null; }
		double x, y;
		x = this.x + Contant.tank_W/2 - Contant.bullet_W/2; //炮弹的位置为中心
		y = this.y + Contant.tank_H/2 - Contant.bullet_H/2;
		Bullet b = new Bullet(x , y , dir_paotou, ctc, good);//利用炮头方向确定子弹方向.调用主窗口,给子弹属性good
		ctc.bullets.add( b);
		bulletNum --;
		return b;		
	}

	private class Blood{//内部类
		public void drawBlood(Graphics g){	 //画血条
			Color c = g.getColor();
			g.setColor(new Color(255,20,20));
			g.drawRect( (int)x  , (int)y -5 ,  Contant.tank_W, 5);
			g.fillRect( (int)x , (int)y - 5, (Contant.tank_W * Tank.this.life/100) ,6);
			g.setColor(c);
		}			
	}

}
