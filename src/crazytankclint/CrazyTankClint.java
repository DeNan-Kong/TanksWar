package crazytankclint;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Util.Contant;
import Util.GameUtil;
import Util.MyFrame;
import Util.Roadblock;


/**
 *  ���ͻ���
 */

public class CrazyTankClint extends MyFrame{	
	private static final long serialVersionUID = 1L;
	Bullet b = null;
	Tank P1_tank= new Tank( 200, 100, Contant.P_spend, this , true, 1); //���P1
	Tank P2_tank= new Tank( 720, 100, Contant. P_spend, this , true, 2); //���P2   ���޸�P2��ҵ���ɫδ�����	
	//�����ӵ�����
	List<Bullet> bullets = new ArrayList<Bullet>();	
	//�����з�̹������
	List<Tank > enemy_tanks = new ArrayList<Tank>();
	//������ը����
	List<Explode > explodes = new ArrayList<Explode>();
	//��������
	List<Grass > grasses = new ArrayList<Grass>();	
	//��������
	List<brickWall > brickwalls = new ArrayList<brickWall >();
	//��������
	List<ironWall > ironwalls = new ArrayList<ironWall>();
	//��������
	List<Ice> ices = new ArrayList<Ice>();
		
	//���������
	Random r = new Random();
	int step = r.nextInt(20);	
	
	public static void main(String[] args) {
		new CrazyTankClint().launchFrame();	
		
	}	
	// ���봰��
	
	public void launchFrame(){ 			
		
			super.launchFrame();
			new MyThread().start();		
			this.addKeyListener(new KeyMonitor());
			
			for( int i = 0;i <Contant.enemt_tank; i ++){			
				enemy_tanks.add(new Tank( 60+ 80*i , 600 ,Contant.spend, this,false,Util.Direction.Stop));   //����������ӵз�̹��			
			}		
			
			//��ӽ�����	
						
			ironwalls.add(new ironWall(350,200,60,60,this,"images/ironwall04.png"));
			ironwalls.add(new ironWall(560,200,60,60,this,"images/ironwall04.png"));
			ironwalls.add(new ironWall(350,430,60,60,this,"images/ironwall04.png"));
			ironwalls.add(new ironWall(560,430,60,60,this,"images/ironwall04.png"));
			
			
					
			grasses.add(new Grass(47,45,60,60,this,"images/grass1.png"));
			grasses.add(new Grass(47,560,60,60,this,"images/grass1.png"));
			grasses.add(new Grass(823,45,60,60,this,"images/grass1.png"));
			grasses.add(new Grass(823,560,60,60,this,"images/grass1.png"));	
			grasses.add(new Grass(377,287,30,60,this,"images/grass4.png"));
			grasses.add(new Grass(347,317,60,120,this,"images/grass3.png"));
			grasses.add(new Grass(587,317,30,60,this,"images/grass3.png"));
			grasses.add(new Grass(527,287,60,120,this,"images/grass4.png"));	
			
			ices.add(new Ice(137,135,686,45,this ,"images/ice1.png" ));
			ices.add(new Ice(137,517,686,45,this  ,"images/ice1.png"));
			ices.add( new Ice(137,135,45,425,this ,"images/ice2.png"));
			ices.add(new Ice(778,135,45,425,this  ,"images/ice2.png"));		
		
			for(int k=0; k<2; k++){
				brickwalls.add(new brickWall(317+30*k,287,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(587+30*k,287,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(287+30*k,317,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(617+30*k,317,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(287+30*k,347,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(617+30*k,347,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(317+30*k,377,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(587+30*k,377,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(137+30*k,105,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(763+30*k,105,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(137+30*k,555,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(763+30*k,555,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(167+30*k,195,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(737+30*k,195,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(167+30*k,465,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(737+30*k,465,30,30,this,"images/brickwall01.png"));
			}
			for(int l=0; l<15; l++){
				brickwalls.add(new brickWall(257+30*l,407,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(257+30*l,257,30,30,this,"images/brickwall01.png"));
			}
			for(int m=0; m<6; m++){
				brickwalls.add(new brickWall(257+30*m,437,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(527+30*m,437,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(257+30*m,227,30,30,this,"images/brickwall01.png"));
				brickwalls.add(new brickWall(527+30*m,227,30,30,this,"images/brickwall01.png"));
			}
			for(int n=0; n<3; n++){
				for(int e=0; e<4; e++){
					brickwalls.add(new brickWall(437+30*n,287+30*e,30,30,this,"images/brickwall01.png"));
				}
			}
			
	}	

	static Image img_start1 = GameUtil.getImage("images/start01.png");
	static Image img_start2 = GameUtil.getImage("images/start02.png");	
	boolean start;
	boolean seclect1 = true;
	boolean seclect2 ;
	int time = Contant.time;	
	int score = 0;//�÷�	
	int guanka = 1;
	
	
	public void paint(Graphics g){ //����ͼ��
		g.drawImage(  GameUtil.getImage("images/backg.png"), 0, 0, null);		
			//��ʼ����	
			
			if(time >0){	
				Font f = g.getFont();
				g.setFont(new Font("����", Font.BOLD, 50));
			//	g.drawString("�׵���   ��Ʒ", 300, 360);
				g.setFont(f);
				try {
					MyThread.sleep( time);
				} catch (InterruptedException e) {			
					e.printStackTrace();			
			    }			
				time  -= Contant.time ;			
			}
		if( !start ){
				GameUtil.getSound("D:/eclipse work/CrazyTank/src/sound/start.wav");			
				if( seclect1 ){
					g.drawImage( img_start1, 47, 46, null);
					P2_tank.setLive( false);				
				}
				if( seclect2 ){
					g.drawImage( img_start2, 47, 46, null);		
					P2_tank.setLive( true);
				}			
		}
				if( start ){
//					GameUtil.getSound( "D:/eclipse work/CrazyTank/src/sound/backsound.wav");
//					g.drawString("�ӵ������еĸ���: " + bullets.size(), 80, 580);
//					g.drawString("̹�������еĸ���: " + enemy_tanks.size(), 80, 550);
//					g.drawString("��ը����: " + explodes.size(), 80, 610);					
					g.drawString("��ǰ�÷�Ϊ�� " + score , 430, 80);
					g.drawString("��    �� :    "+guanka, 430, 60);
					
					g.drawString("F3���� ",340,60);
					g.drawString("P1ʣ��������" +P1_tank.lifenum ,250,60);
					g.drawString("P1������ֵ �� " + P1_tank.getLife(),250, 80);
					g.drawString("P1�ĵ�ҩ��Ŀ��" + P1_tank.getBulletNum(),250, 100);	
					if( P2_tank.isLive() == true){
						g.drawString("F3���� ",690,60);
						g.drawString("P2ʣ��������" +P2_tank.lifenum ,600,60);
						g.drawString("P2������ֵ: " + P2_tank.getLife(),600, 80);
						g.drawString("P2�ĵ�ҩ��Ŀ: " + P2_tank.getBulletNum(),600, 100);					
						
					}						
					
//		if(r.nextInt(100) > 50){ //�����Ѫ����δ�����
//			Blood bloods = new Blood(100+step*70, 100+step*50,Contant.tank_W,Contant.tank_H );//
					Blood bloods1 = new Blood(95 , 340, Contant.tank_W, Contant.tank_H );//
					Blood bloods2 = new Blood(835, 340, Contant.tank_W, Contant.tank_H );//
					bloods1.drawBloods(g);	
					bloods2.drawBloods(g);	
					P1_tank.eatblood(bloods1);
					P2_tank.eatblood(bloods1);	
					P1_tank.eatblood(bloods2);
					P2_tank.eatblood(bloods2);	
					
					BulletsNum bulletsNum = new BulletsNum(95 , 250, Contant.tank_W, Contant.tank_H );
					bulletsNum.drawBulletsNum(g);
					P1_tank.eatbulletNum(bulletsNum);
					P2_tank.eatbulletNum(bulletsNum);
//		}					
					for(int i = 0; i < ices.size(); i++){//��ice
						Roadblock e =  ices.get(i); 
						e.draw(g);		
						P1_tank.collideswithIce(ices.get(i));
						P2_tank.collideswithIce(ices.get(i));
					}					
			
					P2_tank.drawTank(g);					
					P1_tank.drawTank(g); //�������
					
					for(int i = 0; i < enemy_tanks.size();i++){
						Tank t =   enemy_tanks.get(i); //�з�̹��
						t.drawTank(g);
						t.collideswithtank(enemy_tanks);	
						for(int j=0;j<ironwalls.size();j++){
							t.collideswithwall(ironwalls.get(j));
						}
						for(int j=0;j<brickwalls.size();j++){
							t.collideswithwall(brickwalls.get(j));
						}
						for(int j=0;j<grasses.size();j++){
							t.collideswithgrass(grasses.get(j));////̹�˹��ݼ���									
						}	
						for(int j=0;j<ices.size();j++){
							t.collideswithIce(ices.get(j));////̹�˹��ݼ���									
						}							
						P1_tank.collideswithtank(enemy_tanks);
						P2_tank.collideswithtank(enemy_tanks);
					}
					for(int i = 0; i < explodes.size();i++){
						Explode e =   explodes.get(i); //
						e.drawExplode(g);
						if(!P1_tank.isLive() || !P2_tank.isLive() ){ //������������Ϊʲô����˸��
							e.drawExplode(g);
							return;
						}
					}
					for(int i = 0; i < brickwalls.size(); i++){//��wall
						brickWall b = brickwalls.get(i); 
						b.draw(g);			
						P1_tank.collideswithwall( brickwalls.get(i));
						P2_tank.collideswithwall(  brickwalls.get(i));
					}
					for(int i = 0; i < ironwalls.size(); i++){//��wall
						Roadblock e = ironwalls.get(i); 
						e.draw(g);			
						P1_tank.collideswithwall( ironwalls.get(i));
						P2_tank.collideswithwall( ironwalls.get(i));
					}								
					
					for(int i = 0; i < grasses.size(); i++){//��grass
						Roadblock e =   grasses.get(i); 
						e.draw(g);							
						P1_tank.collideswithgrass(grasses.get(i));
						P2_tank.collideswithgrass(grasses.get(i));							
					}					
					for(int i = 0; i < bullets.size();i++){
						Bullet b = bullets.get(i);//�õ�bullets�е�ÿ���ӵ�
						b.drawBullet(g);//��ÿ���ӵ�����       ??
						b.HitTanks(enemy_tanks);// �ӵ�������ʧ					
						b.HitTank(P1_tank);//  ��������޵�	
						b.HitTank(P2_tank);
						for(int j=0;j<brickwalls.size();j++){
							b.Hitbrickwall( brickwalls.get(j) );  //�ӵ�����͸�ϰ���						
						}
						for(int j=0;j<ironwalls.size();j++){
							b.Hitironwall( ironwalls.get(j));						
						}							
					}
					if( P1_tank.lifenum==0&&P2_tank.lifenum==0 ){
						g.drawImage( GameUtil.getImage("images/gameover.png"), 47, 46, null);
						
					}
		}//start
		
	}//pain
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {// ����tank�е�move����	
			P1_tank.KeyPressed_P1(e);
			P2_tank.KeyPressed_P2(e);			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			P1_tank.keyReleased_P1(e);
			P2_tank.keyReleased_P2(e);
		}		
	}	
}
