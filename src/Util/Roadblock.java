package Util;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * 障碍物类
 */
public abstract class Roadblock {
	
	 public int x;
	 public int y;
	 public int width;
	 public int height;
	boolean open;	
	
	
//抽象类 子类必须必须继承
	public  abstract void draw ( Graphics g);
	
	public Rectangle getRect( ) {
		return  new Rectangle( x, y, width, height);		
	}
	
}
