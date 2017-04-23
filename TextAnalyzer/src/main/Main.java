package main;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input path");
		String path = sc.nextLine();
		sc.close();
		
		
		try {
			Scanner fileScanner = new Scanner(Paths.get(path), "UTF-8");
			
			while(fileScanner.hasNext()){
				String i = fileScanner.next().replaceAll("[^a-ÂA-≈ ]", "");
				BST.tree.createNode(i);
			}
			fileScanner.close();
		} catch (NoSuchFileException e) {
			System.out.println("FILE NOT FOUND");
		} catch(IOException o){
			o.printStackTrace();
		}
		
		BST.inOrderTranverseTree(BST.tree.root);

	}

}
