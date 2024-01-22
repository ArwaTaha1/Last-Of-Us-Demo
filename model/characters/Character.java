package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.Cell;
import model.world.CharacterCell;


public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	
	public Character() {
	}
	

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp <= 0) {
			this.currentHp = 0;
			this.onCharacterDeath();
		}
			
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	
	public void attack() throws NotEnoughActionsException, InvalidTargetException {
		Character c = this;
		Character target = c.target;
		target.setCurrentHp(target.getCurrentHp()-this.getAttackDmg());
		target.defend(this);
	}
	public void defend(Character c) {
		
		int damage= this.getAttackDmg()/2;
		c.setCurrentHp(c.getCurrentHp()-damage);

	}
	
	public void onCharacterDeath() {
		 ArrayList <Hero> availableheroes = Game.availableHeroes;
		 ArrayList <Hero> heroes =  Game.heroes;
	     ArrayList <Zombie> zombies = Game.zombies;
	     int a = this.location.x;
	     int b = this.location.y;
	     if(this instanceof Zombie) {
			zombies.remove(this);
			for (int i =0 ;i<1;i++) {
				int x = (int)(Math.random() * (15));
				int y = (int)(Math.random() * (15));
				if (Game.map[x][y] instanceof CharacterCell && ((CharacterCell) Game.map[x][y]).getCharacter()==null) {
						Zombie z = new Zombie();
						z.setLocation(new Point(x,y));
						((CharacterCell) Game.map[x][y]).setCharacter(z);
				}
				else 
					i--;
				}
		}
		if (this instanceof Hero)
		{
			heroes.remove(this);
		}
		((CharacterCell) Game.map[a][b]).setCharacter(null);
	}


	}
	
	
	
	
	
	
	
	
  
