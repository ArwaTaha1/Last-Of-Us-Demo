package model.collectibles;

import java.util.ArrayList;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Supply implements Collectible  {

	public Supply() {
		
	}

	
	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
		
	}

	
	public void use(Hero h) throws NoAvailableResourcesException {

		 int i= h.getSupplyInventory().size();
		 if(i>0) {
			 h.getSupplyInventory().remove(i-1);	 
		 }
		 else {
			 NoAvailableResourcesException error = new NoAvailableResourcesException();
			 throw error;
		 }
		 
		 
	
	}


	
		
		

}
