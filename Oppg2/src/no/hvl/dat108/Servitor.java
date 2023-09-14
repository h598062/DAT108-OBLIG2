package no.hvl.dat108;

public class Servitor extends Thread {
	private HamburgerBrett brett;

	public Servitor(HamburgerBrett brett, String navn) {
		this.brett = brett;
		this.setName(navn);
	}

	@Override
	public void run() {
		super.run();
	}
}
