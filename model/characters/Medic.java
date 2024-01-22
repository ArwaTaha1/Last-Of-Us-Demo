package model.characters;
import java.awt.Point;
import java.util.ArrayList;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import model.collectibles.*;



public class Medic extends Hero {
	
	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
		
	}
	
	public void useSpecial() throws Exception, NoAvailableResourcesException{

		Character target =  this.getTarget();
		if (target instanceof Zombie )
			throw new InvalidTargetException();
		int size = this.getSupplyInventory().size();
		int x = target.getLocation().x;
		int y = target.getLocation().y;
		ArrayList<Point> adjacent = Game.getAdjacent(this);
		boolean flag = false;
		for (int i =0; i<adjacent.size();i++) {
			if (adjacent.get(i).x==x &&adjacent.get(i).y==y || this==target) {
				this.useSpecial();
				target.setCurrentHp(target.getMaxHp());
				this.setActionsAvailable(this.getActionsAvailable()-1);
				flag = true;
			}
		}
		if (flag==false)
			throw new InvalidTargetException();
	}
	
}

