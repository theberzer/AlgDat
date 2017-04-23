package main;

public class Node {
	public String name;
	public int key;
	public Node left, right;

	public Node(String name) {
		this.name = name;
		this.key = 1;
	}

	@Override
	public String toString() {
		String output = key == 1 ? " time ": " times";
		return "The word " + name + " has been used " + key + output;
	}

}
