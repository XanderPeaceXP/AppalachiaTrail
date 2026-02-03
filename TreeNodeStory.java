/**
 * TreeNodeStory has a node in a branching story tree
 * Each node contains text and optional items
 * Nodes may have left and right children to branch decisions
 */
import java.util.*;
public class TreeNodeStory{
	/**
	 * The narrative text used with this node
	 */
	private String text;
	/**
	 * The left child node (e.g., if player chooses "left")
	 */
	private TreeNodeStory left;
	/**
	 * The right child node (e.g., if player chooses "right").
	 */
	private TreeNodeStory right;
	/**
	 * Optional item reward at this story node
	 */
	private String item;
	/**
	 * The quantity of the item rewarded
	 */
	private int amount;
	
	/**
	 * Makes a story node with only narrative text
	 * @param text the story content at this node
	 */
	public TreeNodeStory(String text){
		this.text = text;
		this.item = null;
		this.amount = 0;
	}
	
	/**
	 * Constructs a story node with narrative text and items
	 * @param text the story content at this node
	 * @param item the item rewarded at this node
	 * @param amount the quantity of the item rewarded
	 */
	public TreeNodeStory(String text, String item, int amount) {
		this.text = text;
		this.item = item;
		this.amount = amount;
	}
	/**
	 * @return the item rewarded at this node, or null if none
	 */
	
	public String getItem(){
		return item;
	}
	
	/**
	 * @return the quantity of the item rewarded
	 */
	public int getAmount(){
		return amount;
	}
	
	/**
	 * @return the story text at this node
	 */
	public String getText(){
		return text;
	}
	
	/**
	 * @return the left child node
	 */
	public TreeNodeStory getLeft(){
		return left;
	}
	
	/**
	 * @return the right child node
	 */
	public TreeNodeStory getRight(){
		return right;
	}
	
	/**
	 * Sets the left child node
	 * @param left the node to be set as the left child
	 */
	public void setLeft(TreeNodeStory left) {
		this.left = left;
	}
	
	/**
	 * Sets the right child node
	 * @param right the node to be set as the right child
	 */
	public void setRight(TreeNodeStory right) {
		this.right = right;
	}
	
	/**
	 * If this node is a leaf
	 * @return true if this node has no left or right children
	 */
	public boolean isLeaf(){
		return left == null && right == null;
	}
	
}
