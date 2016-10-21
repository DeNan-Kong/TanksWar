疯狂的坦克大战项目实现点：Version：1.0                 	2016.10.22/上传

1)生成一个可玩，可操作的游戏窗口
	a)继承Frame类,导入相应jar包快捷键 shift+ctrl+o;
	b)重写paint()
	
2)添加一个关闭窗口的处理
	a)addWindowListener(new WindowAdaptor())
	b)重写 windowClosing()方法
	
3)生成一个坦克，使用实心圆来代替（静态版）
	a)重写paint()方法，g.fillOval()
	b)给坦克改变颜色，顺便将窗口的背景颜色改变
	
4)让坦克动起来
	a)将坦克的x,y定义为变量
	b)启动一个重画线程的内部类，重写run()方法
		(1)定义死循环
		(2)调用repaint()
		(3)注意线程要sleep()
	c)使用双缓冲方法解决屏幕闪烁的问题
	
5)将游戏窗口的定值改为常量，方便后期修改、维护

6)添加键盘监听器，让坦克听从玩家的指挥
	a)addkeyListener(new keyAdapter())
	b)重写keyPressed()和keyReleased()方法
	c)添加操纵坦克的逻辑代码
	d)注意break穿透问题
7)使用面向对象的思维方式及设计模式重构代码（重点）

8)坦克朝8个方向运动
	a)确定坦克的运动方向是哪个方向，使用枚举类型定义 L,LU,RD... locateDirection()
	b)确定键盘按下后坦克到底朝哪个方向运动，使用布尔类型定义 bL,bD...
	c)写出坦克运动的方法move()
	
9)生成一枚子弹
	a)定义一个子弹类Bullet，定义相关属性和方法
	b)new出来一枚子弹
10)使用键盘中的某一个键，来控制子弹的打出 使用 space键

11)解决坦克在停止的情况下也能发出子弹
	a)先画出代表子弹运行方向的炮筒drawLine()
	b)判断炮筒方向和坦克的方向的关系
	c)将原来new 子弹的构造器的方向参数改为炮筒的方向
	
12)解决只有一颗子弹的问题,实现多发炮弹的连射
	a)创建一个放炮弹的容器
	b)使用泛型，约束容器中只能放炮弹类型的元素
	c)往容器中添加元素的方法容器.add(<Bullet>)
	
13)生成敌方坦克
	a)给Tank类添加一个区分敌我的boolean变量 good
	b)添加重载的构造器，将good属性添加到构造器当中
	c)给我方和敌方的坦克添加不同颜色
	
14)生成一个爆炸类
	a)在爆炸类当中添加应有的属性x,y,live
	b)使用直径不同的圆来模拟爆炸效果，int[] diameter 静态初始化
	c)添加一个表示步骤的变量 step
	d)drawOval(x,y,diameter[step],diameter[step])来表示每一次画圆的步骤
	e)在ctc中new出来一个静态的爆炸
	
15)敌方坦克死亡时添加爆炸效果
	a) public Rectangular getRect(){
			return new Rectangular(x,y,w,h);
	   }
	b)添加判断两个矩形是否相交的方法
		打击一辆坦克
		hitTank(Tank tank)
		将坦克放到容器中，打击一系列坦克
		hitTanks(List<Tank> tanks)
		
16)让敌人坦克运动更加智能
步骤：
	a)让敌人坦克动起来
		构造函数中可以指定方向
		New敌人坦克的时候指定敌人坦克的方向
	b)让敌军坦克向随机方向移动
		Tank是静态的，添加随机数产生器Random类
		move完成后，如果是敌军坦克，随机产生一个数，来设定坦克的下一个方向
		Direction.values()
	c)敌军坦克向随机方向移动随机的步骤
		添加变量step，记录随机步骤
		当step==0时，改变方向，否则，只要随机步骤递减
	d)敌人坦克发射炮弹
		本军炮弹不打本军
		炮弹添加好坏good，根据好坏画不同颜色
		修改炮弹的构造方法
		修改Tank的fire()方法
		修改hitTank()方法
	e)敌人的炮火不能太猛烈
	
17)添加障碍物Obstruction
	a)创建一个障碍物的父类Obstruction为抽象类，添加属性x,y,w,h,draw()为抽象方法，方便后期维护及修改
	b)创建Wall类Grass类继承Obstruction类
	c)new Wall() new Grass()
	
18)子弹撞墙的问题
	a)如果撞到，子弹live = false
	
19)坦克撞墙的问题
	a)如果撞到，记录上一步坦克运动的位置，返回上一步的位置
	b)不可让坦克的方向Direction.STOP
	
20)坦克撞坦克问题
	a)如果撞到，记录上一步坦克运动的位置，返回上一步的位置
	
21)超级子弹
	a)朝各个方向发射的子弹superFire()
	b)添加一个很大的炮弹，打到坦克上，此炮弹不消亡，完全出窗口才消亡
	c)弹雨
	
	                                                                         for DeNan k./2015.6
