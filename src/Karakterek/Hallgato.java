package Karakterek;

import Szoba.Szoba;
import Targyak.Logarlec;
import Targyak.Romlandok.Camambert;
import Targyak.Romlandok.Rongy;
import Targyak.Tranzisztor;

import java.util.Scanner;

public class Hallgato extends Karakter {
	void aktival() {
		System.out.println("Hallgato aktival egy targyat\n");
		System.out.println("A targyak amiket magadnal viselsz:\n" +
				"1. Tranzisztor\n" +
				"2. Rongy\n" +
				"3. Camambert\n" +
				"4. Logarlec\n");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();

		switch (choice) {
			case 1:
				System.out.println("Tranzisztor aktivalva\n");
				Tranzisztor tranzisztor = new Tranzisztor();
				tranzisztor.aktival();
				break;
			case 2:
				System.out.println("Rongy aktivalva\n");
				Rongy rongy = new Rongy();
				break;
			case 3:
				System.out.println("Camambert aktivalva\n");
				Camambert camambert = new Camambert();
				camambert.aktival();
				break;
			case 4:
				System.out.println("Logarlec aktivalva\n");
				Logarlec logarlec = new Logarlec();
				logarlec.aktival();
				break;
			default:
				System.out.println("Nincs ilyen targy\n");
				break;
		}
		System.out.println("A kivalasztott targy aktivalva\n");
	}

	@Override
	public void mozog(Szoba newSzoba) {
		System.out.println("Hallgato mozog\n");
		super.mozog(newSzoba);
		newSzoba.getAktivTargy();
		System.out.println("A szoba gazos? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		if (choice.equals("I")) {
			System.out.println("A szoba gazos\n");
			System.out.println("Hallgatonak van maszkja?\n");
			String mask = scanner.nextLine();
			if (mask.equals("I")) {
				System.out.println("A hallgato felhasznalja a maszkjat\n");
			} else {
				System.out.println("A hallgato elejt mindent\n");
				mindentelejt();
			}
		} else {
			System.out.println("A szoba nem gazos\n");
		}
	}
}
