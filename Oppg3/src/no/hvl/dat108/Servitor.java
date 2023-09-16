package no.hvl.dat108;

import java.util.Random;

public class Servitor extends Thread {
	private final HamburgerBrettBQ brett;
	Random rnd = new Random();

	public Servitor(HamburgerBrettBQ brett, String navn) {
		this.brett = brett;
		this.setName(navn);
	}

	@Override
	public synchronized void run() {
		while (true) {
			try {
				// wait(1000);
				wait(rnd.nextInt(1000, 4000));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			brett.remove();
		}
	}
}
