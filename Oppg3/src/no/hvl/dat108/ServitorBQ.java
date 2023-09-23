package no.hvl.dat108;

import java.util.Random;

public class ServitorBQ extends Thread {
	private final HamburgerBrettBQ brett;
	Random rnd = new Random();

	public ServitorBQ(HamburgerBrettBQ brett, String navn) {
		this.brett = brett;
		this.setName(navn);
	}

	@Override
	public synchronized void run() {
		while (true) {
			try {
				// wait(1000);
				wait(rnd.nextInt(2000, 6000));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			brett.remove();
		}
	}
}
