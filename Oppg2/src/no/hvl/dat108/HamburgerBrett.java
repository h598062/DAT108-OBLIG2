package no.hvl.dat108;

import java.util.ArrayDeque;
import java.util.Queue;

public class HamburgerBrett {
	private final int kapasitet;
	private Queue<Hamburger> brettKoe;
	public HamburgerBrett(int kapasitet) {
		this.kapasitet = kapasitet;
		this.brettKoe = new ArrayDeque<>(kapasitet);
	}

	public void add(Hamburger hamburger) {
		if (brettKoe.size() >= kapasitet) {

		}
	}
}
