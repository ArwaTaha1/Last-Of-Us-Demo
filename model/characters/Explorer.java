package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import model.world.Cell;

public class Explorer extends Hero {
	

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}
	
   public void useSpecial()throws Exception
   {
	 this.setSpecialAction(true);
     super.useSpecial();
     for (int row = 0; row < 15; row++) {
    	 for (int col = 0; col < 15; col++) { 
    		 Cell cell=Game.map[row][col]; 
    		 cell.setVisible(true);
    		 }
    	 }  
   }
   
	

	
}
