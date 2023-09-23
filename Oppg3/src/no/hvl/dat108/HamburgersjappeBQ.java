package no.hvl.dat108;

import java.util.Arrays;

public class HamburgersjappeBQ {
	public static void main(String... blablabla) {
		final String[] kokker = {"Anne", "Erik", "Knut"};
		final String[] servitorer = {"Mia", "Per"};
		// flere kokker enn servitører, forventer at denne kommer til å ende opp med at kokker alltid venter med å legge på
		final int KAPASITET = 4;
		skrivUtHeader(kokker, servitorer, KAPASITET);
		HamburgerBrettBQ brett = new HamburgerBrettBQ(KAPASITET);
		for (String navn : kokker) {
			new KokkBQ(brett, navn).start();
		}
		for (String navn : servitorer) {
			new ServitorBQ(brett, navn).start();
		}
	}

	private static void skrivUtHeader(String[] kokker, String[] servitorer, int kapasitet) {
		String melding = "I denne simuleringen har vi\n" + kokker.length + " kokker " + Arrays.toString(kokker) + "\n" + servitorer.length +
		                 " servitører " + Arrays.toString(servitorer) + "\nKapasiteten til brettet er " + kapasitet + " hamburgere\nVi starter ...";
		System.out.println(melding);
	}
}
