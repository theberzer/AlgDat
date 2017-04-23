package main;

public class BST {
	static Node root, newNode;
	static BST tree = new BST();

	//Method for creating Nodes within the tree
	public void createNode(String name){
		newNode = new Node(name);
		
		//If no root exists set root = newNode so that we can manipulate it.
		if (root == null){
			root = newNode;
		} else {
			Node focus = root;
			Node parent;
			
			
			while(true){
				parent = focus;
				
				//Searches trough the BST using the compareTo function inserting it alphabetical order
				if (name.toUpperCase().compareTo(focus.name.toUpperCase()) < 0){
					focus = focus.left;
					if (focus == null){
						parent.left = newNode;
						return;
					}
				} else if (name.toUpperCase().compareTo(focus.name.toUpperCase()) > 0){
					focus = focus.right;
					if (focus == null){
						parent.right = newNode;
						return;
					}
				} else {
					//If compareTo returns 0, it has found the word in the BST and increases the key.
					focus.key++;
					return;
				}
				
			}
		}
	} 
	
	//method for in order tranversel, printing the tree in alphabetical order
	public static void inOrderTranverseTree(Node focus) {
		if (focus != null) {
			inOrderTranverseTree(focus.left);
			System.out.println(focus);
			inOrderTranverseTree(focus.right);
		}
	}
}
