package no.hvl.dat108;

import java.util.Random;

public class KokkBQ extends Thread {
	private final HamburgerBrettBQ brett;
	Random rnd = new Random();

	public KokkBQ(HamburgerBrettBQ brett, String navn) {
		this.brett = brett;
		this.setName(navn);
	}

	@Override
	public synchronized void run() {
		while (true) {
			try {
				wait(rnd.nextInt(2000, 6000));
				//wait(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			HamburgerBQ burger = new HamburgerBQ(brett.nyttBurgerNr());
			brett.add(burger);
		}
	}
}
