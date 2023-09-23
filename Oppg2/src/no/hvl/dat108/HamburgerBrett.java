package no.hvl.dat108;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class HamburgerBrett {
	private final int kapasitet;
	private final Queue<Hamburger> brettKoe;
	private int burgerNummer;

	public HamburgerBrett(int kapasitet) {
		this.kapasitet    = kapasitet;
		this.brettKoe     = new ArrayDeque<>(kapasitet);
		this.burgerNummer = 0;
	}

	public synchronized void add(Hamburger hb) {
		while (brettKoe.size() >= kapasitet) { // sjekk om det er plass til en ny hamburger
			try {
				System.out.println(Thread.currentThread()
				                         .getName() + " (kokk) klar med hamburger, men brett fullt. Venter!");
				wait(); // vent til det er plass til en ny hamburger
			} catch (InterruptedException e) {
				System.out.println("ERROR");
			}
		}
		brettKoe.add(hb);
		notifyAll();
		System.out.println(Thread.currentThread()
		                         .getName() + " (kokk) legger på hamburger ◖" + hb.getNummer() + "◗. Brett: " + lagBrettStreng());
	}

	public synchronized void remove() {
		while (brettKoe.isEmpty()) {
			try {

				System.out.println(Thread.currentThread()
				                         .getName() + " (servitør) ønsker å ta hamburger, men brett tomt. Venter!");
				wait();
			} catch (InterruptedException e) {
				System.out.println("ERROR");
			}
		}
		Hamburger hb = brettKoe.remove();
		notifyAll(); // gi beskjed til andre som prøver å legge til at det er en ledig plass
		System.out.println(Thread.currentThread()
		                         .getName() + " (servitør) tar av hamburger ◖" + hb.getNummer() + "◗. Brett: " + lagBrettStreng());
	}

	public synchronized int nyttBurgerNr() {
		burgerNummer++;
		return burgerNummer;
	}

	private String lagBrettStreng() {
		// [◖1◗]
		Iterator<Hamburger> it = brettKoe.iterator();
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		while (it.hasNext()) {
			sb.append("◖")
			  .append(it.next()
			            .getNummer())
			  .append("◗");
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.append(']')
		         .toString();
	}
}
