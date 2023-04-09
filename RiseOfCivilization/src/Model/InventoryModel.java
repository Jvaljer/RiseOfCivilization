package Model;

import Types.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This class is for the model of an inventory. An inventory stores all
 * the different types of resources. Cells, Workers, Buildings ang the
 * City all have an inventory.
 * 
 * @author Martin
 */
public class InventoryModel {
	private ConcurrentHashMap<Resource, Integer> resources;
	private int max_resource; /* Default of -1 means no max_resource */
	
	/**
     * This is a constructor of an Inventory. It has a specified
	 * maximum amount it can store and has specified starting resources.
	 * 
	 * @param ammounts the list of amount for all resource in order of the Enum
	 * @param max_amount the maximum amount of resource the inventory can store
     */
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
	
	/**
     * This is a constructor of an Inventory. It has a specified
	 * maximum amount it can store and holds no resource.
	 * 
	 * @param max_amount the maximum amount of resource the inventory can store
     */
	public InventoryModel(int max_amount) {
		this(Arrays.asList(0, 0, 0, 0), max_amount);
		max_resource = max_amount;
	}
	
	/**
     * This is the default constructor of an Inventory. It has no
	 * maximum amount it can store and holds no resource.
     */
	public InventoryModel() {
		this(-1);
	}
	
	/**
    * Returns map of all resources to their quantity
    */
	public ConcurrentHashMap<Resource, Integer> getResources() {
		return resources;
	}
	
	/**
    * Returns the stored amount of a single resource.
	* 
	* @param r the resource
    */
	public int getAmount(Resource r) {
		return resources.get(r);
	}
	
	/**
    * Remove all stored resources from the inventory
    */
	public void clear() {
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			resources.replace(resource_values[i], 0);
		}
	}
	
	/**
    * Adds a specified amount of a single resource
	* 
	* @param r      the resource
	* @param amount the amount of resource to be added
    */
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
	
	/**
    * Removes all of the resource stored in the inventory and adds them
	* to another one.
	* 
	* @param r     the resource
	* @param other the other inventory
    */
	public void transfer(InventoryModel other) {
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			Resource resource = resource_values[i];
			resources.replace(resource, resources.get(resource) + other.getAmount(resource));
		}
		other.clear();
	}
	
	/**
    * Removes a specified amount of a single resource
	* 
	* @param r      the resource
	* @param amount the amount of resource to be removed
    */
	public int remove(Resource r, int amount) {
		int prev_amount = resources.get(r);
		int amount_diff = prev_amount - amount;
		resources.replace(r, Math.max(amount_diff, 0));
		return Math.min(amount, prev_amount);
	}
	
	/**
    * Returns the maximum of resource that can be stored
    */
	public int GetMaxAmount() {
		return max_resource;
	}
	
	/**
    * Increases the maximum of resource that can be stored by a
	* specified amount
	* 
	* @param amount the amount added to the maximum
    */
	public void IncreaseMaxAmount(int n) {
		max_resource += n;
		return;
	}
}
