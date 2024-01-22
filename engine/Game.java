package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Character;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {
	
	public static Cell [][] map = new Cell[15][15];
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	public static int cured=0;
	
	
		
	public static void loadHeroes(String filePath)  throws IOException {
		
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero=null;
			switch (content[1]) {
			case "FIGH":
				hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			case "MED":  
				hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3])) ;
				break;
			case "EXP":  
				hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			}
			availableHeroes.add(hero);
			line = br.readLine();
			
			
		}
		br.close();
	}
	public static void startGame(Hero h) {
		h.setLocation(new Point(0,0));
		for(int i =0;i<15;i++) {
			for(int j=0;j<15;j++) {
				map[i][j]=new CharacterCell(null);
			}
		}
		map[0][0] = new CharacterCell(h);
		map[0][0].setVisible(true);
		initializeCollectibles();
		initializeZombies();
		initializeTraps();
		ArrayList <Point> adjacent =getAdjacent(h);
		//adjacent.add(new Point(h.getLocation().x,h.getLocation().y));
		for (int i =0; i<adjacent.size();i++) {
			int x = adjacent.get(i).x;
			int y = adjacent.get(i).y;
			map[x][y].setVisible(true);
		}
		availableHeroes.remove(h);
		heroes.add(h);
		
		//System.out.println(map[14][1]);
		
		

	}
	public static void initializeCollectibles() {
		Random rand = new Random();
		int i = rand.nextInt(15);
		int j = rand.nextInt(15);
		for(int x=0;x<5;x++) {
			Cell c=Game.map[i][j];
			while(!isCellEmpty(c)) {
				i = rand.nextInt(15);
				j = rand.nextInt(15);
				c= Game.map[i][j];
			}
			
			Supply s=new Supply();
			CollectibleCell colcell=new CollectibleCell(s);
			Game.map[i][j]= colcell;
		}
		i = rand.nextInt((14 - 0) + 1) + 0;
		j = rand.nextInt((14 - 0) + 1) + 0;
		for(int x=0;x<5;x++) {
			Cell c=Game.map[i][j];
			while(!isCellEmpty(c)) {
				i = rand.nextInt((14 - 0) + 1) + 0;
				j = rand.nextInt((14 - 0) + 1) + 0;
				c= Game.map[i][j];
			}
			
			Vaccine v=new Vaccine();
		    CollectibleCell colcell=new CollectibleCell(v);
			Game.map[i][j]= colcell;
		}
	}
	public static void initializeZombies() {
		Random rand = new Random();
		int i = rand.nextInt((14 - 0) + 1) + 0;
		int j = rand.nextInt((14 - 0) + 1) + 0;
		
		for(int x=0;x<10;x++) {
			Cell c=Game.map[i][j];
			while(!isCellEmpty(c)) {
				i = rand.nextInt((14 - 0) + 1) + 0;
				j = rand.nextInt((14 - 0) + 1) + 0;
				c= Game.map[i][j];
			}
			
			Point loc=new Point(i,j);
			Zombie z= new Zombie();
			z.setLocation(loc);
			CharacterCell cell=(CharacterCell)map[i][j];
			cell.setCharacter(z);
			zombies.add(z);
		}
		
	}
	public static boolean isCellEmpty(Cell c) {
		if(c instanceof CharacterCell && ((CharacterCell) c).getCharacter()==null)
			return true;
		return false;
	}
	
	public static void initializeTraps() {
		Random rand = new Random();
		int i = rand.nextInt((14 - 0) + 1) + 0;
		int j = rand.nextInt((14 - 0) + 1) + 0;
		for(int x=0;x<5;x++) {
			Cell c=Game.map[i][j];
			while(!isCellEmpty(c)) {
				i = rand.nextInt((14 - 0) + 1) + 0;
				j = rand.nextInt((14 - 0) + 1) + 0;
				c= Game.map[i][j];
			}
			TrapCell t=new TrapCell();
			Game.map[i][j]= t;
		}
		
	}
	
	public static boolean checkWin() {
		for (int i = 0; i<15; i++) {
			for (int j =0; j<15;j++) {
				if (map[i][j] instanceof CollectibleCell && ((CollectibleCell) map[i][j]).getCollectible()instanceof Vaccine){
					return false;
					
				}
					
			}
				
		}
		for (int i= 0; i<heroes.size();i++) {
			if (heroes.get(i).getVaccineInventory().size()!=0)
				return false;
		}
		if (heroes.size()<5)
			return false;
		return true;
	}
	public static boolean checkGameOver() {
		for (int i = 0; i<15; i++) {
			for (int j =0; j<15;j++) {
				if (map[i][j] instanceof CollectibleCell && ((CollectibleCell) map[i][j]).getCollectible()instanceof Vaccine){
					return false;
					
				}
					
			}
				
		}
		for (int i= 0; i<heroes.size();i++) {
			if (heroes.get(i).getVaccineInventory().size()!=0)
				return false;
		}
		if (heroes.size()>=5)
			return false;
		return true;
		
	}
	public static void endTurn() throws NotEnoughActionsException, InvalidTargetException {
		for(int i=0;i<zombies.size();i++) {
			Zombie z=zombies.get(i);
			Hero h = Zombie.getAdjacentHero(z);
			if (h!=null) {
				z.attack();
			}
			else 
				continue;
			
			z.setTarget(null);
		}
		
		Random rand = new Random();
		int a = rand.nextInt((14 - 0) + 1) + 0;
		int b = rand.nextInt((14 - 0) + 1) + 0;
		Zombie z = new Zombie();
		Cell cell=Game.map[a][b];
		while(!isCellEmpty(cell)) {
			 rand = new Random();
			 a = rand.nextInt((14 - 0) + 1) + 0;
			 a = rand.nextInt((14 - 0) + 1) + 0;
			 Point p = new Point(a,b);
			 cell=Game.map[a][b];
		}
		CharacterCell o= new CharacterCell(z);
		Game.map[a][b]=o;
		o.setCharacter(z);
		z.setLocation(new Point(a,b));
		zombies.add(z);
		

		
		ArrayList <Cell> m = new ArrayList <Cell>();
		for(int i=0;i<heroes.size();i++) {
			Hero h=heroes.get(i);
			h.setActionsAvailable(h.getMaxActions());
			h.setSpecialAction(false);
			h.setTarget(null);	
			ArrayList <Point> x =getAdjacent(h);
			Point loc = h.getLocation();
			//map[(int)loc.getX()][(int)loc.getY()].setVisible(true);
			for(int j=0;j<x.size();j++) {
				Point p=x.get(j);
				int t=(int)p.getX();;
				int y=(int)p.getY();
				//map[t][y].setVisible(true);
				m.add(map[t][y]);
			}
		//	m.add(map[loc.x][loc.y]);
		}
		for(int q=0;q<15;q++) {
			for(int j=0;j<15;j++) {
				Cell c = map[q][j];
				if (m.contains(c))
					c.setVisible(true);
				else 
					c.setVisible(false);
			} 
		}
		
	}

	public static ArrayList<Point> getAdjacent(Character c) {
		//c.setLocation(new Point(0,0));
		int x = (int) c.getLocation().getX();
		int y = (int)c.getLocation().getY();
		ArrayList<Point> p = new ArrayList<Point>();
		int i = 1;
		while (i>=-1) {
			int j = 1;
			while (j>=-1) {
				int t  = x+i;
				int b  = y +j;
				if (t>=0 && t<15 && b>-1 && b<15)
					p.add(new Point(t,b));
				j--;		
			}
			i--;	
		}
	//	p.remove(new Point(x,y));
		return p;
		
	}


	public static void main(String [] args) {
		Fighter f = new Fighter("bye",10,100,50); 
		f.setLocation(new Point(0,14));
		System.out.print(getAdjacent(f));
	}


}
