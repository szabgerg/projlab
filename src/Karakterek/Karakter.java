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
	public void mozog(Szoba newSzoba) {}

	/*
	 * A metódusban a karakter tárgyfelvételét valósítjuk meg
	 * Ha a karakternek nincs helye a tárgyaknak, akkor nem tud felvenni
	 */
	public void felvesz(int hely) {
		Szoba jelenlegi = new Szoba();
		Targyinventory eszkozkeszlet = new Targyinventory();
		System.out.println("Targy felvetel\n");
		System.out.println("Hany targy van mar a hallgato inventory-jaban? (n)\n");
		Scanner scanner = new Scanner(System.in);
		int pos = scanner.nextInt();
		if (pos >= 5) {
			System.out.println("Nincs tobb hely a hallgato inventory-jaban\n");
			scanner.close();
			return;
		}
		System.out.println("Milyen targyat szeretnel felvenni?\n");
		jelenlegi.getBentiTargyak();
		scanner.nextInt();
		eszkozkeszlet.AddTargy(null);
		
		System.out.println("A targy felvetele sikeres\n");
		jelenlegi.setBentiTargyak(null);
		scanner.close();
	}

	/*
	 * A metódusban a karakter tárgyletevését valósítjuk meg
	 * Ha a karakternek nincs tárgya, akkor nem tud letenni
	 * Amit letett, az a szobában lesz, mas karakterek felvehetik
	 */
	public void letesz(int hely) {
		Szoba jelenlegi = new Szoba();
		Targyinventory eszkozkeszlet = new Targyinventory();
		System.out.println("Targy letetel\n" +
				"Milyen targyat szeretnel letenni?\n");
		System.out.println("A targyak amiket magadnal viselsz:\n" +
				"1. Tvsz\n" +
				"2. Söröspohár\n" +
				"3. Rongy\n" +
				"4. Camambert\n" +
				"5. Maszk\n");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		eszkozkeszlet.RemoveTargy(null);
		jelenlegi.setBentiTargyak(null);
		scanner.close();
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