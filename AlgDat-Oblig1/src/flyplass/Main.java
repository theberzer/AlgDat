package flyplass;

import java.util.*;

public class Main {
	// Globale variabler
	private static LinkedList<Integer> koInn = new LinkedList<>();
	private static LinkedList<Integer> koUt = new LinkedList<>();
	private static boolean ledigAnk = true;
	private static boolean ledigAvg = false;
	private static int landet, avgang, avist, inn, ut, teller, timer;
	private static double flyinn, flyut, venteTid, ledigTid;

	public static void main(String[] args) {
		// Innput
		Scanner in = new Scanner(System.in);
		System.out.println("Velkommen til Moss Rygge Lufthavn(RIP)");
		System.out.println("Hvor mange timer skal simuleringen gå?:");
		timer = in.nextInt();
		System.out.println("Forventet antall ankomster per time?:");
		flyinn = in.nextDouble();
		System.out.println("Forventet antall avganger per time?: ");
		flyut = in.nextDouble();
		in.close();

		// Gjennomføring
		for (int i = 0; i < timer; ++i) {
			teller++;
			System.out.println(teller + ": Fly " + i + " klar for landing.");

			// Landing
			for (int j = 0; j < getPoissonRandom(flyinn); ++j) {
				if (koInn.size() > 10) {
					System.out.println("Fly " + i + " avvist");
					avist++;
				} else {
					koInn.add(j);
				}
			} // Avgang
			for (int j = 0; j < getPoissonRandom(flyut); ++j) {
				if (koUt.size() > 10) {
					avist++;
				} else {
					koUt.add(j);
					ut++;
				}
			} // Ankomst
			if (!(koInn.isEmpty()) && ledigAnk == true) {
				venteTid += koInn.remove();
				landet++;
				ledigAnk = false;
				ledigAvg = true;
				System.out.println("Fly " + (i) + " har landet");
			} // Avgang
			else if (!(koUt.isEmpty()) && ledigAvg == true) {
				venteTid += koUt.remove();
				avgang++;
				ledigAvg = false;
				System.out.println("Fly " + i + " klar til avgang.");
				avgang++;
			} else {
				ledigTid++;
				ledigAnk = true;
			}
		}
		// Output
		System.out.println("Simuleringen ferdig etter: " + timer);
		System.out.println("Totalt antall fly behandlet: " + teller);
		System.out.println("Antall fly landet: " + landet);
		System.out.println("Antall fly tatt av: " + avgang);
		System.out.println("Antall fly avvist : " + avist);
		System.out.println("Antall fly klare for landing: " + inn);
		System.out.println("Antall fly klare til å ta av : " + ut);
		System.out.println("Prosent ledig tid  : " + (venteTid / ledigTid) * 100 + "%");
		System.out.println("Gj.snitt. ventetid for landing: " + (venteTid / landet) + " time(r)");
		System.out.println("Gj.snitt. ventetid for avgang: " + (venteTid / avgang) + " time(r)");
	}

	private static int getPoissonRandom(double mean) {
		Random r = new Random();
		double L = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		do {
			p = p * r.nextDouble();
			k++;
		} while (p > L);
		return k - 1;
	}
}
