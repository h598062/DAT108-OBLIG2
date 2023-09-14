package no.hvl.dat108;

public class PrintRunnable implements Runnable{
	private volatile String  melding = "Hello World!";
	private boolean avbryt;

	@Override
	public void run() {
		while (!avbryt) {
			try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			System.out.println(melding);
		}
	}

	public void setMelding(String s) {
		this.melding = s;
	}
	public void avslutt() {
		this.avbryt = true;
	}
}
