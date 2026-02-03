/**
 * A static method to build and return
 * the root of a branching story tree for the game.
 */
import java.util.*;
public class Story{
	
	/**
	* Makes and returns the root of a story tree
	* @return the root node of the story tree
	*/
	public static TreeNodeStory storyLine(){
		TreeNodeStory start = new TreeNodeStory(
			"You emerge from Vault 76 into a ruined world blanketed in ash and silence. " +
			"To the left, dense forest stirs quietly. To the right, the remains of a pre-War town ahead. " +
			"Do you go left into the forest, or right toward the ruins?");
			
		//StartL->forest
		TreeNodeStory forest = new TreeNodeStory
			("The forest is dark and overgrown. Mutated plants choke the path. " +
				"You hear water ringing in your ear to the left, and you hear the piter pater of footsteps to your right.");
				
		//forestL->river
		TreeNodeStory river = new TreeNodeStory("You find a clean spring surrounded by glowing mushrooms. You fill your bottles and feel revitalized. After taking a rest you see a glowing light, to your left, and to your right you hear the mermer of people talking.", "water", 3);
		//forestR->ambush
		TreeNodeStory ambush = new TreeNodeStory("A Super Mutant lunges from behind a tree. You're too slow. This is the end for you.\n Game Over");
		
		//riverL->glowingCave
		TreeNodeStory glowingCave = new TreeNodeStory("You follow the stream and discover a glowing cave. Inside, rare chems are hidden among fungus.", "stempacks", 1);
		//riverR->armyDoor
		TreeNodeStory armyDoor = new TreeNodeStory("You walk closer to the sound of people talking, You find a door. You push your ear to the door and you hear a what sounds like a army general giving commands to there patalion. Do you open the door(L), or do you Run(R)?");
		
		
		//StartR->ruins
		TreeNodeStory ruins = new TreeNodeStory
		("The ruins are still and hollow. Rubble and collapsed buildings fill the streets. " +
			"To the left, you see a subway tunnel entrance open like a mouth. To the right, a rusted tower reaching into the sky.");
		
		//ruinsL->tunnel
		TreeNodeStory tunnel = new TreeNodeStory("Inside the subway, you find old military crates. Among the dust, you find usable gear.", "ammo", 2);
		//ruinsR->tower
		TreeNodeStory tower = new TreeNodeStory("You begin to climb the tower, but the metal gives way. You fall with a scream. Darkness.\n ");
		
		//left
		TreeNodeStory stash = new TreeNodeStory("A locked door in the tunnel creaks open to reveal a stash of food sealed since the war.(InstaMash)", "food", 5);
		//right
		TreeNodeStory ghoulNest = new TreeNodeStory("You wake stand up feeling disoriented and not sure where you are. While you gain your composure you relise you are surrounded with 4 feral ghouls awaken. Do you try to shoot the ghouls or run away?");

		
		
		// First layer
		start.setLeft(forest);
		start.setRight(ruins);
		
		forest.setLeft(river);
		forest.setRight(ambush);//die
		
		ruins.setLeft(tunnel);
		ruins.setRight(tower);
		
		// Second layer
		river.setLeft(glowingCave);// reward
		river.setRight(armyDoor);
		
		tunnel.setLeft(stash);// reward
		tower.setRight(ghoulNest);
		
		return start;
	}
	
}
