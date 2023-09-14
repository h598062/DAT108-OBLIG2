package no.hvl.dat108;

import java.util.Random;

public class Kokk extends Thread {
	private HamburgerBrett brett;
	Random rnd = new Random();

	public Kokk(HamburgerBrett brett, String navn) {
		this.brett = brett;
		this.setName(navn);
	}

	@Override
	public void run() {
		while (true) {
			try {
				wait(rnd.nextInt(2000, 6000));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			brett.add(new Hamburger());
		}
	}
}
