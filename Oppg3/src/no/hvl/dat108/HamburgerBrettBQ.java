package no.hvl.dat108;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HamburgerBrettBQ {
	private final int                      kapasitet;
	private final BlockingQueue<Hamburger> brettKoe;
	private       int                      burgerNummer;

	public HamburgerBrettBQ(int kapasitet) {
		this.kapasitet    = kapasitet;
		this.brettKoe     = new ArrayBlockingQueue<>(kapasitet);
		this.burgerNummer = 0;
	}

	public void add(Hamburger hb) {
		try {
			brettKoe.put(hb);
			System.out.println(Thread.currentThread()
			                         .getName() + " (kokk) legger på hamburger ◖" + hb.getNummer() + "◗. Brett: " + lagBrettStreng());
		} catch (InterruptedException e) {
			System.out.println("oopsie poopsie, we made a fucky wucky UwU");
		}
	}

	public void remove() {
		try {
			Hamburger hb = brettKoe.take();
			System.out.println(Thread.currentThread()
			                         .getName() + " (servitør) tar av hamburger ◖" + hb.getNummer() + "◗. Brett: " + lagBrettStreng());
		} catch (InterruptedException e) {
			System.out.println("oopsie poopsie, we made a fucky wucky UwU");
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
