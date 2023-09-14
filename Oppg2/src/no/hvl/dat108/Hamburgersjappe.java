package no.hvl.dat108;

import java.util.Arrays;

public class Hamburgersjappe {
	public static void main(String... blablabla) {
		final String[] kokker     = {"Anne", "Erik", "Knut"};
		final String[] servitorer = {"Mia", "Per"};
		final int      KAPASITET  = 4;
		skrivUtHeader(kokker, servitorer, KAPASITET);
		HamburgerBrett brett = new HamburgerBrett(KAPASITET);
		for (String navn : kokker) {
			new Kokk(brett, navn).start();
		}
		for (String navn : servitorer) {
			new Servitor(brett, navn).start();
		}
	}

	private static void skrivUtHeader(String[] kokker, String[] servitorer, int kapasitet) {
		String melding = "I denne simuleringen har vi\n" + kokker.length + " kokker " +
		                 Arrays.toString(kokker) + "\n" + servitorer.length + " servitører " +
		                 Arrays.toString(servitorer) + "\nKapasiteten til brettet er " + kapasitet +
		                 " hamburgere\nVi starter ...";
		System.out.println(melding);
	}
}
