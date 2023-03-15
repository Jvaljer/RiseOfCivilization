package Model;

import Types.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This class is the model of the inventory. An inventory stores various
 * resources. Cells, Workers and Buildings all have an inventory.
 *
 * @author martin
 */
public class InventoryModel {	
	/* The map of all the ressources to their quantity */
	private ConcurrentHashMap<Resource, Integer> resources;
	/* Default of -1 means no max_resource */
	private int max_resource;
	
	public InventoryModel(List<Integer> ammounts, int max_amount) {
		max_resource = max_amount;
		resources = new ConcurrentHashMap<Resource, Integer>();
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			if (max_resource < 0) {
				resources.put(resource_values[i], ammounts.get(i));
			} else {
				resources.put(resource_values[i], Math.min(ammounts.get(i), max_resource));
			}
		}
	}
	
	public InventoryModel(int max_amount) {
		this(Arrays.asList(0, 0, 0, 0), max_amount);
	}
	
	public InventoryModel() {
		this(-1);
	}
	
	public ConcurrentHashMap<Resource, Integer> getResources() {
		return resources;
	}
	
	public int getAmmount(Resource r) {
		return resources.get(r);
	}
	
	public void clear() {
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			resources.replace(resource_values[i], 0);
		}
	}
	
	public int add(Resource r, int amount) {
		int prev_amount = resources.get(r);
		int amount_sum = prev_amount + amount;
		if (max_resource < 0) {
			resources.replace(r, amount_sum);
			return amount;
		} else {
			resources.replace(r, Math.min(amount_sum, max_resource));
			return Math.min(amount, max_resource - prev_amount);
		}
	}
	
	public void transfer(InventoryModel other) {
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			Resource resource = resource_values[i];
			resources.replace(resource, resources.get(resource) + other.getAmmount(resource));
		}
		other.clear();
	}
	
	public int remove(Resource r, int amount) {
		int prev_amount = resources.get(r);
		int amount_diff = prev_amount - amount;
		resources.replace(r, Math.max(amount_diff, 0));
		return Math.min(amount, prev_amount);
	}
	
}
