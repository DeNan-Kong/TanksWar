package Util;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * �ϰ�����
 */
public abstract class Roadblock {
	
	 public int x;
	 public int y;
	 public int width;
	 public int height;
	boolean open;	
	
	
//������ ����������̳�
	public  abstract void draw ( Graphics g);
	
	public Rectangle getRect( ) {
		return  new Rectangle( x, y, width, height);		
	}
	
}
