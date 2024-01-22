package views;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import model.characters.Direction;

public class keyHandler implements KeyListener{
	
	public boolean UP,DOWN,LEFT,RIGHT;
	
	public void keyTyped(KeyEvent e) {
		
		char code = e.getKeyChar();
		
		if (code=='w'){
          System.out.print("hi");
		}
		if (code=='a'){
			DOWN=true;
		}
		if (code=='d'){
			LEFT=true;
		}
		if (code=='s') {
			RIGHT=true;
		}
	}


	public void keyPressed(KeyEvent e) {
		
		
	}

	
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code==KeyEvent.VK_W){
			UP=false;
		}
		if (code==KeyEvent.VK_S){
			DOWN=false;
		}
		if (code==KeyEvent.VK_A){
			LEFT=false;
		}
		if (code==KeyEvent.VK_D) {
			RIGHT=false;
		}
		
	}
	public static void main(String [] args) {
		
	}

}
