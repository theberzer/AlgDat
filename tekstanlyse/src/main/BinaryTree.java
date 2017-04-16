package main;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class BinaryTree {

	public static Path path;
	public static String text;
	static Node root;
	static Node newNode;
	static BinaryTree tree = new BinaryTree();

	public void addNode(int key, String name) {
		newNode = new Node(key, name);

		if (root == null) {
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;

			while (true) {
				parent = focusNode;

				if (key < focusNode.key) {
					focusNode = focusNode.leftChild;

					if (focusNode == null) {
						parent.leftChild = newNode;
						return;
					}

				} else {
					focusNode = focusNode.rightChild;

					if (focusNode == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public static void inOrderTranverseTree(Node focusNode) {
		if (focusNode != null) {
			inOrderTranverseTree(focusNode.leftChild);
			System.out.println(focusNode);
			inOrderTranverseTree(focusNode.rightChild);
		}
	}

	public static void readFile(Path path) throws IOException {

		try {
			Scanner sc = new Scanner(path, "UTF-8");
			while (sc.hasNext()) {
				String i = sc.next();
				Node focus = root.nodeSearch(i, root);
				if (newNode != null) {
					// System.out.println("new node: " + newNode.getName());
					if (focus != null) {
						focus.setKey(focus.getKey() + 1);
						System.out.println(focus.getKey());
						System.out.println("Increased " + focus.getName());
						System.out.println("repeated instance of " + i);
					} else {
						tree.addNode(1, i);
						System.out.println("first instance of " + i);
					}
				} else {
					tree.addNode(1, i);
					System.out.println("root node created");
				}

				System.out.println("---------------------new line---------------------");
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}

	}

	public static void main(String[] args) throws IOException {
		// Scanner sc = new Scanner(System.in);
		// System.out.println("Input path");
		// String path = sc.next();
		// sc.close();
		String path = "C:\\Users\\berzi\\Desktop\\test3.txt";

		readFile(Paths.get(path));
		inOrderTranverseTree(tree.root);

	}

}

class Node {
	public int key;
	public String name;

	Node leftChild;
	Node rightChild;

	Node(int key, String name) {
		this.key = key;
		this.name = name;

	}

	public Node nodeSearch(String name, Node node) {
		Node foundNode = null;

		System.out.println(node.getName() + " ny ord: " + name);
		if (node.getName().equalsIgnoreCase(name)) {
			System.out.println("FOUND");
			return foundNode = node;
		} else if (node.leftChild != null) {
			System.out.println("going left");
			foundNode = nodeSearch(name, node.leftChild);
			return foundNode;
		} else if (node.rightChild != null && foundNode == null) {
			System.out.println("going right");
			foundNode = nodeSearch(name, node.rightChild);
			return foundNode;

		} else
		System.out.println("NOT FOUND");
		return null;

	}


	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "The word " + name + " has been used " + key;
	}

}