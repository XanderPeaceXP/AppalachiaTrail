/**
 * PartyMember is an individual in the player's group
 * Each party member has a name, health, radiation level, and status
 * for being alive and sick. The class manages health, sickness,
 * radiation, and printing current status.
 */
public class PartyMember{
	
	/**
	 * The name of the party member
	 */
	private String name;
	/** 
	 * The health of the party member (0-100)
	 */
	private int health;
	/** 
	 * The radiation level of the party member (0-100)
	 */
	private int radiation;
	/** 
	 * Whether the party member is alive
	 */
	private boolean alive;
	/** 
	 * Whether the party member is currently sick
	 */
	private boolean sick;
	
	/**
	 * Constructs a new PartyMember with default values
	 * @param name the name of the party member
	 */
	public PartyMember(String name){
		this.name = name;
		this.health = 100;
		this.radiation = 0;
		this.alive = true;
		this.sick = false;
	}
	
	/**
	 * Returns a status string for the party member
	 * @return the status of the party member
	 */
	public String status() {
		return name + " - HP: " + health + " - RAD: " + radiation + " - " +
				(alive ? (sick ? "Sick" : "Healthy") : "Deceased");
	}
	
	/** 
	 * @return true if the party member is sick
	 */
	public boolean isSick(){
		return sick;
	}
	
	/**
	 * Marks the party member as sick if they are alive
	 */
	public void makeSick(){
		if(alive){
			sick = true;
			System.out.println(name + " has become sick.");
		}
	}
	
	/**
	 * Cures the party member if they are alive and sick
	 */
	public void cure(){
		if(alive && sick){
			sick = false;
			System.out.println(name + " has recovered.");
		}
	}
	
	/** 
	 * @return the name of the party member
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return the current health of the party member 
	 */
	public int getHealth(){
		return health;
	}
	
	/**
	 * @return true if the party member is alive
	 */
	public boolean isAlive(){
		return alive;
	}
	/**
	 * Applies damage if health drops to 0 or below marks as dead
	 * @param amount the amount of damage to apply
	 */
	public void damage(int amount){
		health -= amount;
		if (health <= 0){
			alive = false;
			health = 0;
			System.out.println(name + " has died.");
		}
	}
	
	/**
	 * Increases radiation level
	 * @param rads the amount of radiation to add
	 */
	public void rad(int rads){
		radiation += rads;
		if(radiation > 100) radiation = 100;
	}
	
	/**
	 * Heals the party member by a given amount, capped at 100 health
	 * @param amount the amount to heal
	 */
	public void heal(int amount){
		if(alive){
			health += amount;
			if(health > 100) health = 100;
		}
	}
	
	/**
	 * Returns the string of the party member, using status()
	 * @return the status string
	 */
	@Override
	public String toString() {
		return status();
	}
}
