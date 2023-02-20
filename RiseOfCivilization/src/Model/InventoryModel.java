package Model;

import Types.*;

import java.util.concurrent.ConcurrentHashMap;


/**
 * This class is for the model of the inventory. The inventory stores various
 * ressources that can be harvested from ressource cells either by workers or
 * by buildings built on the cell. Avialable ressources are the following :
 * Wood from Forests, Wheat from Farms, Stone from Quarries & Iron from Mines.
 *
 * @author martin
 */
public class InventoryModel {	
	/* The map of all the ressources to their quantity */
	private ConcurrentHashMap<Resource, Integer> resources;
	
	public InventoryModel() {
		resources = new ConcurrentHashMap<Resource, Integer>();
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			resources.put(resource_values[i], 0);
		}
	}
	
	public ConcurrentHashMap<Resource, Integer> getResources() {
		return resources;
	}
	
	public int getAmount(Resource r) {
		return resources.get(r);
	}
	
	public void collect(Resource r, int amount) {
		resources.replace(r, resources.get(r) + amount);
	}
	
	public void use(Resource r, int amount) {
		resources.replace(r, resources.get(r) - amount);
	}
	
}
