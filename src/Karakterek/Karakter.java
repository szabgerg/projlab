package Karakterek;

import Szoba.Szoba;
import Targyak.Targyinventory;

import java.util.Scanner;

public class Karakter {
	public void felvesz() {
		System.out.println("Targy felvetel\n" +
				"Milyen targyat szeretnel felvenni?\n");
		Szoba szoba = new Szoba();
		szoba.getBentiTargyak();
		Targyinventory eszkozok = new Targyinventory();
		Scanner scanner = new Scanner(System.in);
		String targy = scanner.nextLine();
		System.out.println("A kivalasztott targy: " + targy + " felveve\n");
		if (eszkozok.AddTargy()) {
			System.out.println("A targy felvetele sikeres\n");
			szoba.RemoveTargy();
		} else {
			System.out.println("A targy felvetele sikertelen\n");
		}

	}

	public void letesz() {
		System.out.println("Targy letetel\n" +
				"Milyen targyat szeretnel letenni?\n");
		System.out.println("A targyak amiket magadnal viselsz:\n" +
				"1. Tvsz\n" +
				"2. Söröspohár\n" +
				"3. Tranzisztor\n" +
				"4. Rongy\n" +
				"5. Camambert\n" +
				"6. Maszk\n");
		Scanner scanner = new Scanner(System.in);
		String targy = scanner.nextLine();
		System.out.println("A kivalasztott targy leteve\n");
		Szoba szoba = new Szoba();
		szoba.setBentiTargyak();
	}

	public void mozog(Szoba newSzoba) {
		Szoba old = new Szoba();
		if (newSzoba.beenged()) {
			System.out.println("A szobaban van eleg hely\n");
			old.kilep(this);
		} else {
			System.out.println("A szoba tele nincs hely\n");
		}
	}

	public Szoba getSzoba() {
		return null;
	}

	public void mindentelejt() {
		System.out.println("Minden targy eldobva\n");
		Szoba szoba = new Szoba();
		Targyinventory eszkozok = new Targyinventory();
		szoba.setBentiTargyak(eszkozok);
		eszkozok.targyak.clear();
	}

	public Karakter getID() {
		return null;
	}

	public Targyinventory getEszkozkeszlet() {
		return null;
	}

	public void setEszkozkeszlet(Targyinventory eszkozok) {
		System.out.println("Eszkozkeszlet beallitasa\n");
	}


}