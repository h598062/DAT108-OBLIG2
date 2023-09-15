package no.hvl.dat108;

import java.util.Random;

public class Servitor extends Thread {
	private final HamburgerBrett brett;
	Random rnd = new Random();

	public Servitor(HamburgerBrett brett, String navn) {
		this.brett = brett;
		this.setName(navn);
	}

	@Override
	public void run() {
		while (true) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			brett.remove();
		}
	}
}
