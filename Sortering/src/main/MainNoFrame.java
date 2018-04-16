package main;

import java.util.Scanner;

public class MainNoFrame {
	static long startTime, endTime, totalTime;
	static int n, sort, select;
	static Comparable[] tall;
	static int[] tallx;
	



	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How long should the array be?");
		n = input.nextInt();
		System.out.println("Which sorting algorithm? (1-4)\n1: Quicksort\n2: Insertionsort\n3: Mergesort\n4: Radixsort");
		sort = input.nextInt();
		System.out.println("Run or Estimate? (1-2)\n1: Run\n2: Estimate");
		select = input.nextInt();
		input.close();
		startSort();
		if(select == 2){
			totalTime = totalTime/5;
			Double C = totalTime / (n*Math.log(n));
			System.out.println("Result: " + C + " ms");
			
		} else {
		System.out.println("Result: " + totalTime + " ms");
		}
		
	}

	public static void startSort() {
		if (select == 1) {
			makeArray();
			sort();
		} else {
			for (int i = 0; i < 5; i++) {
				makeArray();
				sort();
			}
		}
	}

	public static void sort() {
		startTime = System.currentTimeMillis();
		if (sort == 1) {
			Sort.quickSort(tall);
		} else if (sort == 2) {
			Sort.insertionSort(tall);
		} else if (sort == 3) {
			Sort.mergeSort(tall);
		} else if (sort == 4) {
			Sort.radixSort(tallx, n);
		}
		endTime = System.currentTimeMillis();

		totalTime += (endTime - startTime);
	}

	public static void makeArray() {
		tall = new Integer[n];
		tallx = new int[n];

		for (int i = 0; i < n; i++) {
			if (sort == 4) {
				tallx[i] = (int) (Math.random() * (2 * n));
			} else {
				tall[i] = (int) (Math.random() * (2 * n));
			}
		}
	}

}
