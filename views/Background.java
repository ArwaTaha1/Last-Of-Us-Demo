package views;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JLabel l;
	ImageIcon image;
	JButton b1;

	
	public  void BackgroundImageJFrame(ImageIcon i){
		setLayout(new BorderLayout());
		setContentPane(new JLabel(i));
		setLayout(new FlowLayout());
		setSize(399,399);
		setSize(400,400);
	}

}
