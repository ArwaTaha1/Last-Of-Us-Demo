package model.collectibles;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}

	public void pickUp(Hero h) {
		
		h.getVaccineInventory().add(this);
			
	}

	
	public void use(Hero h)  {

		 int i= h.getVaccineInventory().size();
		 int j = Game.zombies.size();
		 ArrayList<Vaccine> vaccine = h.getVaccineInventory();
		 int x = h.getTarget().getLocation().x;
		 int y = h.getTarget().getLocation().y;
		 Game.zombies.remove(j-1);
		 int rand = (int)(Math.random() * (Game.availableHeroes.size()-1));
		 Hero hero = Game.availableHeroes.get(rand);
		 Game.heroes.add(hero);
		 Game.availableHeroes.remove(hero);
		 vaccine.remove(i-1);
		 hero.setLocation(new Point(x,y));;
		 Game.map[x][y] = new CharacterCell(hero);
		 
		 
	}
}
