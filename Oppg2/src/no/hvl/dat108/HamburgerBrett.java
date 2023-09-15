package no.hvl.dat108;

import java.util.ArrayDeque;
import java.util.Queue;

public class HamburgerBrett {
	private final int              kapasitet;
	private final Queue<Hamburger> brettKoe;

	private int burgerNummer;

	public HamburgerBrett(int kapasitet) {
		this.kapasitet    = kapasitet;
		this.brettKoe     = new ArrayDeque<>(kapasitet);
		this.burgerNummer = 0;
	}

	public synchronized void add(Hamburger hamburger, Kokk kokk) {
		while (brettKoe.size() >= kapasitet) { // sjekk om det er plass til en ny hamburger
			try {
				wait(); // vent til det er plass til en ny hamburger
			} catch (InterruptedException e) {
				System.out.println("oopsie poopsie, we made a fucky wucky UwU");
			}
		}
		System.out.println(
				kokk.getName() + " (kokk) legger på hamburger ◖" + hamburger.getNummer() + "◗. Brett: " +
				lagBrettStreng());
		brettKoe.add(hamburger);
		notifyAll(); // gi beskjed til alle som venter at det er lagt til en ny
	}

	public synchronized Hamburger remove() {
		while (brettKoe.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("oopsie poopsie, we made a fucky wucky UwU");

			}
		}
		notifyAll(); // gi beskjed til andre som prøver å legge til at det er en ledig plass
		return brettKoe.remove();
	}

	public synchronized int nyttBurgerNr() {
		burgerNummer++;
		return burgerNummer;
	}

	private String lagBrettStreng() {
		// [◖1◗]
		StringBuilder sb = new StringBuilder("[");
		for (Hamburger b : brettKoe) {
			sb.append("◖")
			  .append(b.getNummer())
			  .append("◗")
			  .append(", ");
		}
		return sb.substring(0, sb.length() - 2) + "]";
	}
}
