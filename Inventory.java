import java.util.*;
import java.io.*;
public class Inventory{
	/**
	 * Stores the resources names mapped to their quantities
	 */
	private Map<String, Integer> stuff;
	
	/**
	 * Initializes the inventory with given amount
	 * @param food initial amount of food
	 * @param water initial amount of water
	 * @param ammo initial amount of ammo
	 * @param meds initial amount of meds
	 * @param caps initial amount of caps
	 */
	public Inventory(int food, int water, int ammo, int meds, int caps){
		stuff = new HashMap<>();
		stuff.put("food", food);
		stuff.put("water", water);
		stuff.put("meds", ammo);
		stuff.put("caps", meds);
		stuff.put("caps", caps);
	}
	
	/**
	 * gets the amount of a resource
	 * @param resource the name of the resource
	 * @return amount of the resource or 0 if not found
	 */
	public int get(String resource){
		return stuff.getOrDefault(resource.toLowerCase(), 0);
	}
	
	/**
	 * Adds a amount to a resource
	 * @param resource the name of the resource
	 * @param amount the amount to add
	 */
	public void add(String resource, int amount){
		resource = resource.toLowerCase();
		stuff.put(resource, get(resource) + amount);
	}
	
	/**
	 * Uses a amount of a resource if enough is available
	 * @param resource the name of the resource
	 * @param amount the amount to use
	 * @return true if the resource was successfully used, false otherwise
	 */
	public boolean use(String resource, int amount){
		resource = resource.toLowerCase();
		int current = get(resource);
		if(current >= amount){
			stuff.put(resource, current - amount);
			return true;
		}
		return false;
	}
	
	/**
	 * Prints the current inventory contents
	 */
	public void printInventory(){
		System.out.println("Current Inventory:");
		for(String key : stuff.keySet()){
			System.out.printf("  %s: %d%n", key, stuff.get(key));
		}
	}
	
	/**
	 * Checks if the inventory has enough of a given resource
	 * @param resource the name of the resource
	 * @param amount the amount to check for
	 * @return true if enough is available, false otherwise
	 */
	public boolean hasEnough(String resource, int amount){
		return get(resource) >= amount;
	}
	
	/**
	 * Sets a resource to a amount
	 * @param item the name of the resource
	 * @param amount the new amount to set
	 */
	public void set(String item, int amount){
		stuff.put(item.toLowerCase(), amount);
	}
}
