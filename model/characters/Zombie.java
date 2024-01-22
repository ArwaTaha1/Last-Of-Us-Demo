package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		Hero h = getAdjacentHero(this);
		if (h==null)
			return;
		super.attack();

	}
	public static Hero getAdjacentHero(Zombie z) {
		ArrayList<Point> adjacent = Game.getAdjacent(z);
		boolean flag = false;
		for (int i =0; i<adjacent.size();i++) {
			int x = adjacent.get(i).x;
			int y = adjacent.get(i).y;
			flag = Game.map[x][y] instanceof CharacterCell && ((CharacterCell) Game.map[x][y]).getCharacter() instanceof Hero;
			if (flag==true) {
				z.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
				return (Hero) ((CharacterCell) Game.map[x][y]).getCharacter();
			}
			else 
				continue;
		}
		z.setTarget(null);
		return null;
		
	}
	
	
	
	
	
	
	
}

