package Karakterek;

import Szoba.Szoba;
import Targyak.Targyinventory;

import java.util.Scanner;
/*
* Karakter osztály ami a játékban szereplő karaktereket reprezentálja
* A karaktereknek van egy szobájuk ahol tartózkodnak és egy eszközkeszletük
* A karakterek tudnak mozogni, tárgyakat felvenni, letenni és mindent elejteni
* Ősosztálya a hallgató és az oktató osztálynak
* */
public class Karakter {
	/*
	* Szoba amiben a karakter tartózkodik
	* Nem lehet null
	* */
	protected Szoba jelenlegi;
	protected Targyinventory eszkozkeszlet;
	public Karakter(Szoba szoba, Targyinventory inventory) {
	}
	public void felvesz() {
		System.out.println("Targy felvetel\n");
		System.out.println("Hany targy van mar a hallgato inventory-jaban? (n)\n");
		Scanner scanner = new Scanner(System.in);
		int hely = scanner.nextInt();
		if (hely == 5) {
			System.out.println("Nincs tobb hely a hallgato inventory-jaban\n");
			return;
		}
		System.out.println("Milyen targyat szeretnel felvenni?\n");
		jelenlegi.getBentiTargyak();
		String targy = scanner.nextLine();
		if (eszkozkeszlet.AddTargy(null)) {
			System.out.println("A targy felvetele sikeres\n");
			jelenlegi.RemoveTargy(null);
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
		jelenlegi.setBentiTargyak();
	}

	public void mozog(Szoba newSzoba) {
		if (newSzoba.beenged()) {
			System.out.println("A szobaban van eleg hely\n");
			jelenlegi.kilep(this);
		} else {
			System.out.println("A szoba tele nincs hely\n");
		}
	}

	public Szoba getSzoba() {
		return null;
	}

	public void mindentelejt() {
		System.out.println("Minden targy eldobva\n");
		jelenlegi.setBentiTargyak(null);
		eszkozkeszlet.targyak.clear();
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