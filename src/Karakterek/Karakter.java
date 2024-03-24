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
	 * Nem lehet null, amig a karakter a játékban van
	 *
	 */
	/* Karakter konstruktora
	 * @param szoba - a szoba, ahol a karakter tartózkodik
	 * @param inventory - a karakter eszközkeszlete
	 */
	public Karakter(Szoba szoba, Targyinventory inventory) {
	}

	/*
	 * A karakter mozgását megvalósító metódus
	 * @param newSzoba - az új szoba, ahova a karakter mozog
	 * A metódusban a karakter mozgását valósítjuk meg
	 *
	 */
	public void mozog(Szoba newSzoba) {
		Szoba jelenlegi = new Szoba();
		if (newSzoba.beenged()) {
			System.out.println("A szobaban van eleg hely\n");
			jelenlegi.kilep(this);
			newSzoba.setBentlevok(null);
		} else {
			System.out.println("A szoba tele, nincs hely\n");
		}
	}

	/*
	 * A metódusban a karakter tárgyfelvételét valósítjuk meg
	 * Ha a karakternek nincs helye a tárgyaknak, akkor nem tud felvenni
	 */
	public void felvesz() {
		Szoba jelenlegi = new Szoba();
		Targyinventory eszkozkeszlet = new Targyinventory();
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
		scanner.close();
		eszkozkeszlet.AddTargy(null)
		System.out.println("A targy felvetele sikeres\n");
		jelenlegi.setBentiTargyak(null);

	}

	/*
	 * A metódusban a karakter tárgyletevését valósítjuk meg
	 * Ha a karakternek nincs tárgya, akkor nem tud letenni
	 * Amit letett, az a szobában lesz, mas karakterek felvehetik
	 */
	public void letesz() {
		Szoba jelenlegi = new Szoba();
		Targyinventory eszkozkeszlet = new Targyinventory();
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
		scanner.close();
		if (eszkozkeszlet.RemoveTargy(null)) {  //ha sikerült a tárgy letétele
			System.out.println("A kivalasztott targy leteve\n");
			jelenlegi.setBentiTargyak(null);
		} else {
			System.out.println("A kivalasztott targy nem letezik\n");
		}
	}

	/*
	 * A karakter melyik szobában tartózkodik
	 */
	public Szoba getSzoba() {
		return null;
	}

	/*
	 * A karakter minden tárgyát eldobó metódus
	 */
	public void mindentelejt() {
		Szoba jelenlegi = new Szoba();
		Targyinventory eszkozkeszlet = new Targyinventory();
		System.out.println("Minden targy eldobva\n");
		jelenlegi.setBentiTargyak(null);
	}

	/*
	 * Visszaadja a karakter azonosítóját
	 * Kell ez egyaltalan?
	 */
	public Karakter getID() {
		return null;
	}

	/*
	 * A karakter eszközkeszletét visszaadó metódus
	 */
	public Targyinventory getEszkozkeszlet() {
		return null;
	}

	/*
	 * A karakter eszközkeszletét beállító metódus
	 */
	public void setEszkozkeszlet(Targyinventory eszkozok) {
		System.out.println("Eszkozkeszlet beallitasa\n");
	}


}