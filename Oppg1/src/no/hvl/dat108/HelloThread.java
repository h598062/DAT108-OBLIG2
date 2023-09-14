package no.hvl.dat108;

import static javax.swing.JOptionPane.showInputDialog;

public class HelloThread {
	public static void main(String[] args) {
		boolean avslutt = false;
		PrintRunnable printerRunnable = new PrintRunnable();
		Thread printerThread = new Thread(printerRunnable);
		printerThread.start();
		while (!avslutt) {
			String input = showInputDialog("Skriv inn melding, eller quit for å avslutte");
			if (input != null) {
				if (input.equals("quit") || input.equals("Quit")) {
					System.out.println("avslutter...");
					avslutt = true;
					printerRunnable.avslutt();
				} else {
					printerRunnable.setMelding(input);
				}
			}
		}
	}
}
