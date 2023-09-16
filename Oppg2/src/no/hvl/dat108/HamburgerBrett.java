package no.hvl.dat108;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HamburgerBrett {
	private final int              kapasitet;
	private final Queue<Hamburger> brettKoe;

	private int burgerNummer;

	private final Lock      lock               = new ReentrantLock();
	private final Condition plassTilBurger     = lock.newCondition();
	private final Condition burgerTilgjengelig = lock.newCondition();

	public HamburgerBrett(int kapasitet) {
		this.kapasitet    = kapasitet;
		this.brettKoe     = new ArrayDeque<>(kapasitet);
		this.burgerNummer = 0;
	}

	public void add(Hamburger hb) {
		lock.lock();
		try {
			while (brettKoe.size() >= kapasitet) { // sjekk om det er plass til en ny hamburger
				System.out.println(Thread.currentThread()
				                         .getName() + " (kokk) klar med hamburger, men brett fullt. Venter!");
				plassTilBurger.await(); // vent til det er plass til en ny hamburger
			}
			brettKoe.add(hb);
			burgerTilgjengelig.signalAll();
			System.out.println(Thread.currentThread()
			                         .getName() + " (kokk) legger på hamburger ◖" + hb.getNummer() + "◗. Brett: " + lagBrettStreng());
		} catch (InterruptedException e) {
			System.out.println("oopsie poopsie, we made a fucky wucky UwU");
		} finally {
			lock.unlock();
		}
	}

	public void remove() {
		lock.lock();
		try {
			while (brettKoe.isEmpty()) {
				System.out.println(Thread.currentThread()
				                         .getName() + " (servitør) ønsker å ta hamburger, men brett tomt. Venter!");
				burgerTilgjengelig.await();
			}
			Hamburger hb = brettKoe.remove();
			plassTilBurger.signalAll(); // gi beskjed til andre som prøver å legge til at det er en ledig plass
			System.out.println(Thread.currentThread()
			                         .getName() + " (servitør) tar av hamburger ◖" + hb.getNummer() + "◗. Brett: " + lagBrettStreng());
		} catch (InterruptedException e) {
			System.out.println("oopsie poopsie, we made a fucky wucky UwU");
		} finally {
			lock.unlock();
		}
	}

	public synchronized int nyttBurgerNr() {
		burgerNummer++;
		return burgerNummer;
	}

	private String lagBrettStreng() {
		// [◖1◗]
		Iterator<Hamburger> it = brettKoe.iterator();
		StringBuilder       sb = new StringBuilder();
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
