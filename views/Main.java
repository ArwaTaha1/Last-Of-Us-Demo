package views;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) throws Exception {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Last Of Us");
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		GameWindow gamewindow = new GameWindow();
		window.add(gamewindow);
		//window.pack();
		gamewindow.startgame();
	}
	
}
