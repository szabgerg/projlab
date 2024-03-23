package Karakterek;

import Szoba.Szoba;
import Targyak.Logarlec;
import Targyak.Romlandok.Camambert;
import Targyak.Romlandok.Rongy;
import Targyak.Romlandok.Sorospohar;
import Targyak.Romlandok.Tvsz;
import Targyak.Targyinventory;
import Targyak.Tranzisztor;

import java.util.Scanner;

public class Hallgato extends Karakter {
	public Hallgato(Szoba szoba, Targyinventory inventory) {
		super(szoba, inventory);
		System.out.println("Hallgato letrehozasa\n");
		System.out.println("Hallgato eszkozkeszlete letrehozva\n");
		System.out.println("Hallgato szobaba teve\n");
	}
	@Override
	public void mozog(Szoba newSzoba) {
		System.out.println("Hallgato mozog\n");
		super.mozog(newSzoba);
		newSzoba.getAktivTargy();
		System.out.println("A szoba gazos? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		do if (choice.equals("I")) {
			System.out.println("A szoba gazos\n");
			System.out.println("Hallgatonak van maszkja? (I/N)\n");
			String mask = scanner.nextLine();
			do if (mask.equals("I")) {
				System.out.println("A hallgato felhasznalja a maszkjat\n");
			} else if (mask.equals("N")) {
				System.out.println("A hallgato elejt mindent\n");
				mindentelejt();
			} else {
				System.out.println("Nem ertelmezett valasz\n");
			} while (mask.equals("I") || mask.equals("N"));

		} else if (choice.equals("N")) {
			System.out.println("A szoba nem gazos\n");
		} else {
			System.out.println("Nem ertelmezett valasz\n");
		} while (choice.equals("I") || choice.equals("N"));
	}

	void aktival() {
		System.out.println("Hallgato aktival egy targyat\n");
		System.out.println("A targyak amiket magadnal viselsz:\n" +
				"1. Tranzisztor\n" +
				"2. Rongy\n" +
				"3. Camambert\n" +
				"4. Logarlec\n" +
				"Melyik targyat szeretned aktivalni? (n)\n");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		do switch (choice) {
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
		} while ((choice > 0) && (choice < 5));
		System.out.println("A kivalasztott targy aktivalva\n");
	}

	public boolean vedekezes() {
		System.out.println("Hallgato vedekezik\n");
		System.out.println("Van Tvsz-e? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();
		do if (choice.equals("I")) {
			System.out.println("A hallgato sikeresen vedekezik\n");
			Tvsz tvsz = new Tvsz();
			tvsz.aktival();
			return true;
		} else if (choice.equals("N")) {
			System.out.println("A hallgato nem rendelkezik tvsszel\n");
			System.out.println("A hallgatonak van sorospohara? (I/N)\n");
			String sors = scanner.nextLine();
			do if (sors.equals("I")) {
				System.out.println("A hallgato sikeresen vedekezik\n");
				Sorospohar sorsospohar = new Sorospohar();
				sorsospohar.aktival();
				return true;
			} else if (sors.equals("N")) {
				System.out.println("A hallgato nem rendelkezik sorospoharral\n");
				System.out.println("A hallgato meghal\n");
			} else {
				System.out.println("Nem ertelmezett valasz\n");
				return false;
			} while (sors.equals("I") || sors.equals("N"));
		} else {
			System.out.println("Nem ertelmezett valasz\n");
			return false;
		} while (choice.equals("I") || choice.equals("N"));

	}

	public void teleport() {
		System.out.println("Hallgato teleportal\n");
		Tranzisztor t1 = new Tranzisztor();
		t1.getPar();
		Tranzisztor t2 = new Tranzisztor();
		System.out.println("A tranzisztorok ossze vannak kapcsolva? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();
		do if (choice.equals("I")) {
			System.out.println("A tranzisztorok ossze vannak kapcsolva\n");
			mozog(t2.getSzoba());
			t1.kikapcsol();
			t2.kikapcsol();

		} else if (choice.equals("N")) {
			System.out.println("A tranzisztorok nincsenek ossze kapcsolva\n");
			System.out.println("A hallgato nem tud teleportalni\n");
		} else {
			System.out.println("Nem ertelmezett valasz\n");
		} while (choice.equals("I") || choice.equals("N"));
	}

	public void osszekapcsol() {
		System.out.println("Transzisztorok osszekapcsolasa\n");
		Tranzisztor t1 = new Tranzisztor();
		boolean szabad1 = t1.canPair();
		Tranzisztor t2 = new Tranzisztor();
		boolean szabad2 = t2.canPair();
		if (szabad1 && szabad2) {
			t1.setPar(t2);
			t2.setPar(t1);
			System.out.println("A tranzisztorok osszekapcsolva\n");
		}else {
			System.out.println("A tranzisztorok nem kapcsolhatoak ossze\n");
		}
	}
}
