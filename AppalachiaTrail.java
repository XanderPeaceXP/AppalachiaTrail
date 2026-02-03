/**
 * AppalachiaTrail is the main class for my Fallout game.
 * It handles the setup, player interaction, the ENTIRE shop system, 
 * and it runs the story being told to the player.
 * 
 * @author Xander Peace
 */

import java.util.*;
import java.io.*;
public class AppalachiaTrail{
	/**
	 * Text Speed for slowPrint
	 */
	public static final int textSpeed = 0;
	
	/**
	 * Makes all text color green
	 */
	public static final String GREEN = "\u001B[32m";
	
	/**
	 * The main method initializes input and random, and it starts the game
	 * @param args tester
	 */
	public static void main(String[] args){
		Scanner input = Args.console != null ? Args.console : new Scanner(System.in);
		Random rng = Args.rand != null ? Args.rand : new Random();
		AppalachiaTrail trail = new AppalachiaTrail();
		trail.play(input);
	}
	
	/**
	 * Starts the game sequence
	 * @param scan Scanner for input
	 */
	public void play(Scanner scan){
		Inventory inventory = new Inventory(0, 0, 0, 0, 100);
		int sleepTime = 2;
		
		
		slowPrint("=== Fallout: Appalachian Trail ===\n");
		slowPrint("25 Years After the Great War, you emerge from your vault not knowing where to go you start your journey by traveling though the remains of Appalachia.");
		slowPrint("You are haunted by thought of radiation, raiders, and whatever else lays out there. You quickly realize you need to find safty. You are told of a place called");
		slowPrint("Fort Atlas - a fortified Brotherhood stronghold said to offer safety, structure, and salvation. But reaching it won’t be easy. Will you survive the trail?\n");
		
		slowPrint("You are a Vault Dweller from vault 76");
		slowPrintInLine("What is your name?: ");
		
		
		
		
		List<PartyMember> party = new ArrayList<>();
		String yourName = scan.nextLine();
		party.add(new PartyMember(yourName));
		
		clearScreen();
		sleep(sleepTime); //make things look smoother
		
		slowPrint("Your party: ");
		for(PartyMember member : party){
			slowPrint(" " + member);
		}
		slowPrint("\nYou're about ready to head off on your adventure");
		slowPrint("But dont forget to stop by the WhiteSpring Golf Club to get your supplies");
		
		slowPrintInLine("\nPress Enter to continue to the WhiteSprint Golf Club...");
		scan.nextLine();
		clearScreen();
		sleep(sleepTime);
		
		buySupplies(scan, inventory);
		slowPrint("Are you ready to head onto the trail?");
		slowPrint("There is no turning back once you have started");
		slowPrintInLine("Press [Enter] to continue... ");
		scan.nextLine();
		clearScreen();
		
		slowPrint("=== Fallout: Appalachian Trail ===\n");
		traverseStory(scan, Story.storyLine(), inventory);
	}
	/**
	 * Handles buying items
	 * @param scan Scanner for user input
	 * @param inventory the player inv
	 */
	public static void buySupplies(Scanner scan, Inventory inventory){
		Map<String, Integer> prices = new LinkedHashMap<>();
		prices.put("food", 2); //per pound
		prices.put("water", 1); //per bottle
		prices.put("ammo", 4); //20 per box
		prices.put("stempacks", 15); //for each
		
		List<String> items = new ArrayList<>(prices.keySet());
		
		
		while(true){
			slowPrint("=== WhiteSpring Golf Club ===");
			slowPrint("You have " + inventory.get("caps") + " caps to spend.");
			slowPrint("But you dont have to spend them all right now.\n");
			slowPrint("Store Items:");
			for(int i = 0; i < items.size(); i++){
				String item = items.get(i);
				int price = prices.get(item);
				System.out.printf("%d. %s - %d caps each (you have: %d)%n", 
									(i + 1), item, price, inventory.get(item));
			}
			slowPrint((items.size() + 1) + ". Exit Store");
			
			slowPrintInLine("Select an item to buy (1-" + (items.size() + 1) + "): ");
			int choice;
			try{
				choice = Integer.parseInt(scan.nextLine());
			}catch (Exception e){
				slowPrint("Invalid input. Try again.");
				scan.nextLine();
				clearScreen();
				continue;
			}
			if(choice == items.size() + 1){
				slowPrint("Exiting store...");
				clearScreen();
				sleep(2);
				break;
			}
			
			if(choice < 1 || choice > items.size()){
				slowPrint("Invalid choice. Try again.");
				scan.nextLine();
				clearScreen();
				continue;
			}
			
			String selectedItem = items.get(choice - 1);
			int price = prices.get(selectedItem);
			
			slowPrintInLine("How many " + selectedItem + " would you like to buy? ");
			int amount;
			try{
				amount = Integer.parseInt(scan.nextLine());
				if(amount == -1){
					slowPrint("Must buy at least 1 item.");
					scan.nextLine();
					clearScreen();
					continue;
				}
			}catch (Exception e){
				slowPrint("Invalid amount. Try again.");
				scan.nextLine();
				clearScreen();
				continue;
			}
			
			int currentAmount = inventory.get(selectedItem);
			int totalCost = amount * price;
			int refundCaps = currentAmount * price;
			
			inventory.add("caps", refundCaps);
			
			if(inventory.hasEnough("caps", totalCost)){
				inventory.use("caps", totalCost);
				inventory.set(selectedItem, amount);
				int newTotal = inventory.get(selectedItem);
				clearScreen();
				slowPrint("Updated " + selectedItem + " to " + amount + " units for " + totalCost + " caps.");
				slowPrint("You now have " + newTotal + " " + selectedItem + ".");
				slowPrint("Remaining caps: " + inventory.get("caps") + "\n");
			}else{
				// Revert refund if new total is too expensive
				slowPrint("Not enough caps for that amount.\nPress Enter to continue back to shop... ");
				scan.nextLine();
				clearScreen();
			}
		}
	}
	/**
	 * Goes thought the story tree based on user choice
	 * @param scan Scanner for input
	 * @param node Starting node of the story tree
	 * @param inventory playes inv (to give items)
	 */
	public static void traverseStory(Scanner scan, TreeNodeStory node, Inventory inventory){
		while(node != null && !node.isLeaf()){
			slowPrint("\n" + node.getText());
			slowPrint("Choose: [L]eft or [R]ight");
			
			String choice = scan.nextLine().trim().toUpperCase();
			if(choice.equals("L")){
				node = node.getLeft();
			}else if (choice.equals("R")){
				node = node.getRight();
			}else{
				slowPrint("Invalid choice. Try again.");
			}
		}
		if(node != null){
			slowPrint("\n" + node.getText());
			
			if(node.getItem() != null && node.getAmount() > 0){
				inventory.add(node.getItem(), node.getAmount());
				slowPrint("You gained " + node.getAmount() + " " + node.getItem() + "!");
			}
		}
	}
	
	/**
	 * Prints text slowly with green color
	 * @param text the text to print
	 */
	public static void slowPrint(String text){
		System.out.print(GREEN);
		for(char c : text.toCharArray()){
			System.out.print(c);
			try{
				Thread.sleep(textSpeed);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}
	
	/**
	 * Prints text slowly in line with color
	 * @param text the text to print
	 */
	public static void slowPrintInLine(String text){
		System.out.print(GREEN);
		for(char c : text.toCharArray()){
			System.out.print(c);
			try{
				Thread.sleep(textSpeed);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
	}
	
	/**
	 * Pauses the game for n seconds
	 * @param seconds amount of time to sleep
	 */
	public static void sleep(int seconds){
		try{
			Thread.sleep(seconds * 1000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Clears the screen
	 */
	public static void clearScreen() {
		try{//windows
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch(Exception e){//mac and linux
			try{
				String term = System.getenv("TERM"); // https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getenv-java.lang.String-
				if(term != null && !term.equals("dumb")){
					new ProcessBuilder("clear").inheritIO().start().waitFor();
				}
			}catch(Exception e2){}
		}
	}
}
