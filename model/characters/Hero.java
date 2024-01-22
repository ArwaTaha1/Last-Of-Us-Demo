package model.characters;
import java.util.ArrayList;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine; 
import java.awt.Point;
import model.world.*;
import engine.*;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
		
		
		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name,maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory=new ArrayList<Supply>();
			this.specialAction=false;
		
		}

		public boolean isSpecialAction() {
			return specialAction;
		}
		
		public void setSpecialAction(boolean specialAction) {
			this.specialAction = specialAction;
		}

		public int getActionsAvailable() {
			return actionsAvailable;
		}

		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}

		public int getMaxActions() {
			return maxActions;
		}
		
		public ArrayList<Vaccine> getVaccineInventory() {
			return vaccineInventory;
		}

		public ArrayList<Supply> getSupplyInventory() {
			return supplyInventory;
		}
		
		public void attack() throws NotEnoughActionsException, InvalidTargetException  {
	
 	
				int actions = this.getActionsAvailable();
				if (this.getTarget()==null) {
					throw new InvalidTargetException(); }
				else if (this.getTarget() instanceof Hero){
					throw new InvalidTargetException();
				}
				else {if (!(this instanceof Fighter) && actions<=0 )
					throw new NotEnoughActionsException();
					
		          int x=this.getTarget().getLocation().x;
		          int y=this.getTarget().getLocation().y;
		          boolean flag=false;
		          ArrayList <Point> p=Game.getAdjacent(this);
		          for(int i=0;i<p.size();i++) {
		        	  if(p.get(i).x==x && p.get(i).y==y) {
		        		  flag=true;
		        	  }
		          }
		          if(flag==false) {
		            throw new InvalidTargetException();
		          }
		          else {
		        	 if (this instanceof Fighter && this.isSpecialAction()==true)
		        		 super.attack();
		        	 else {
		        		 super.attack();
		        		 actionsAvailable--;
		        	 } 
		          }
		      }
	}	
		
		public void move(Direction d) throws MovementException, NotEnoughActionsException {
			if (actionsAvailable==0)
				throw new NotEnoughActionsException();
			Hero h = this;
			int x = h.getLocation().x;
			int y = h.getLocation().y;
			int right = y+1;
			int left = y-1;
			int up = x+1;
			int down = x-1;
			
			switch(d) {
			
			case LEFT:
				if (y==0)
					throw new MovementException();
				else if (Game.map[x][left] instanceof CharacterCell &&  (((CharacterCell) Game.map[x][left]).getCharacter()!=null)) {
					{
						throw new MovementException();
					}
				}
				else if (Game.map[x][left] instanceof TrapCell) {
					TrapCell trapcell = (TrapCell)Game.map[x][left];
					h.setCurrentHp(h.getCurrentHp()-trapcell.getTrapDamage());
					Game.map[x][left] = new CharacterCell(null);
				}
				else if (Game.map[x][left] instanceof CollectibleCell) {
					((CollectibleCell) Game.map[x][left]).getCollectible().pickUp(this);
					Game.map[x][left] = new CharacterCell(null);
				}
				
					if (h.getCurrentHp()!=0) {
						((CharacterCell) Game.map[x][left]).setCharacter(this);
						h.setLocation(new Point(x,left));
						ArrayList<Point> adjacent = Game.getAdjacent(h);
						for (int i =0; i<adjacent.size();i++) {
							int a = adjacent.get(i).x;
							int b = adjacent.get(i).y;
							Game.map[a][b].setVisible(true);
						}
					
						Game.map[x][y].setVisible(true);
						((CharacterCell) Game.map[x][y]).setCharacter(null);
						actionsAvailable--;
						break;
					
						}	
					else 
						h.onCharacterDeath();
				
			case RIGHT :
				if (y==14)
					throw new MovementException();
				else if (Game.map[x][right] instanceof CharacterCell &&  (((CharacterCell) Game.map[x][right]).getCharacter()!=null))  {
					throw new MovementException();
				}
				else if (Game.map[x][right] instanceof TrapCell) {
					TrapCell trapcell = (TrapCell)Game.map[x][right];
					h.setCurrentHp(h.getCurrentHp()-trapcell.getTrapDamage());
					Game.map[x][right] = new CharacterCell(null);
				}
				else if (Game.map[x][right] instanceof CollectibleCell) {
					((CollectibleCell) Game.map[x][right]).getCollectible().pickUp(this);
					Game.map[x][right] = new CharacterCell(null);
				}
				if (h.getCurrentHp()!=0) {
					((CharacterCell) Game.map[x][right]).setCharacter(this);
					h.setLocation(new Point(x,right));
					ArrayList<Point> adjacent = Game.getAdjacent(h);
					for (int i =0; i<adjacent.size();i++) {
						int a = adjacent.get(i).x;
						int b = adjacent.get(i).y;
						Game.map[a][b].setVisible(true);
					}
					Game.map[x][y].setVisible(true);
					((CharacterCell) Game.map[x][y]).setCharacter(null);
					actionsAvailable--;
					break;
				}
				else 
					h.onCharacterDeath();
			case UP :
				if (x==14)
					throw new MovementException();
				else if (Game.map[up][y] instanceof CharacterCell &&  (((CharacterCell) Game.map[up][y]).getCharacter()!=null)) {
					throw new MovementException();
				}
				else if (Game.map[up][y] instanceof TrapCell) {
					TrapCell trapcell = (TrapCell)Game.map[up][y];
					h.setCurrentHp(h.getCurrentHp()-trapcell.getTrapDamage());
					Game.map[up][y] = new CharacterCell(null);
				}
				else if (Game.map[up][y] instanceof CollectibleCell) {
					((CollectibleCell) Game.map[up][y]).getCollectible().pickUp(this);
					Game.map[up][y] = new CharacterCell(null);
				}
				if (h.getCurrentHp()!=0) {
					((CharacterCell) Game.map[up][y]).setCharacter(this);
					h.setLocation(new Point(up,y));
					ArrayList<Point> adjacent = Game.getAdjacent(h);
					for (int i =0; i<adjacent.size();i++) {
						int a = adjacent.get(i).x;
						int b = adjacent.get(i).y;
						Game.map[a][b].setVisible(true);
					}
					Game.map[x][y].setVisible(true);
					((CharacterCell) Game.map[x][y]).setCharacter(null);
					actionsAvailable--;
					break;	
			}
				else 
					h.onCharacterDeath();
			case DOWN :
				if (x==0)
					throw new MovementException();
				else if (Game.map[down][y] instanceof CharacterCell &&  (((CharacterCell) Game.map[down][y]).getCharacter()!=null)) {
					throw new MovementException();
				}
				else if (Game.map[down][y] instanceof TrapCell) {
					TrapCell trapcell = (TrapCell)Game.map[down][y];
					h.setCurrentHp(h.getCurrentHp()-trapcell.getTrapDamage());
					Game.map[down][y] = new CharacterCell(null);
				}
				else if (Game.map[down][y] instanceof CollectibleCell) {
					((CollectibleCell) Game.map[down][y]).getCollectible().pickUp(this);
					Game.map[down][y] = new CharacterCell(null);
				}
				if (h.getCurrentHp()!=0) {
					((CharacterCell) Game.map[down][y]).setCharacter(this);
					h.setLocation(new Point(down,y));
					ArrayList<Point> adjacent = Game.getAdjacent(h);
					for (int i =0; i<adjacent.size();i++) {
						int a = adjacent.get(i).x;
						int b = adjacent.get(i).y;
						Game.map[a][b].setVisible(true);
					}
					Game.map[x][y].setVisible(true);
					((CharacterCell) Game.map[x][y]).setCharacter(null);
					actionsAvailable--;
					break;
			}
				else 
					h.onCharacterDeath();
				
			}
}	
	

	
	public void useSpecial() throws Exception, NoAvailableResourcesException , InvalidTargetException{
		
		ArrayList<Supply> supplies = this.getSupplyInventory();
		if (supplies.size()>0) 
			supplies.get(supplies.size()-1).use(this);
		else 
			throw new NoAvailableResourcesException();
	}
	
	public void cure() throws NotEnoughActionsException, InvalidTargetException {

		ArrayList<Point> adjacent = Game.getAdjacent(this);
		Character target = this.getTarget();
		int size = this.getVaccineInventory().size();
		if (actionsAvailable==0)
			throw new NotEnoughActionsException();
		if (target==null)
			throw new InvalidTargetException();
		if (target instanceof Hero)
			throw new InvalidTargetException();
		boolean flag = false;
		int x = target.getLocation().x;
		int y = target.getLocation().y;
		for (int i = 0; i<adjacent.size();i++) {
			if (adjacent.get(i).x==x &&adjacent.get(i).y==y) {
				this.getVaccineInventory().get(size-1).use(this);
				flag=true;
				actionsAvailable--;
			}
		}
		if (flag==false)
			throw new InvalidTargetException();	
	}
	
}

	


			

	

		
		
