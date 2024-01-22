package views;

import engine.*;
import model.characters.*;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.JPanel;

public class GameWindow extends JPanel implements Runnable{
	final int ogTile = 16;
	final int scale = 3;
	final int Tile = ogTile*scale;
	final int columns = 15;
	final int rows= 15;
	final int width = Tile * columns;
	final int height = Tile * rows;
	//set position of player
	int playerX = 100;
	int playerY = 100;
	int speed = 4; 
	int FPS =60;
	keyHandler key = new keyHandler();
	Thread gameThread;
	
	public GameWindow() throws Exception {
		view v = new view();
		v.createWorldPanel();
		//this.set=v;
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
		
	}
	
	public void startgame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	double drawTime = 1000000000/FPS;
	double nextdrawTime = System.nanoTime() + drawTime ;
	public void run() {
		while(gameThread!=null) {
			update();
			repaint();

			try {
				double remTime = nextdrawTime - System.nanoTime();
				remTime = remTime/1000000;
				if(remTime <0) {
					remTime=0;
				}
				Thread.sleep((long) remTime);
				nextdrawTime += drawTime;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}

	public void update() {
		if(key.UP==true) {
			 playerY = playerY - speed;
		}
		if(key.DOWN==true) {
			playerY = playerY + speed;
		}
		if(key.LEFT==true) {
			playerX = playerX - speed;
		}
		if(key.RIGHT==true) {
			playerX = playerX + speed;
		}
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics g2 = (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.fillRect(playerX, playerY, Tile , Tile);
		
        
	}
	
}
