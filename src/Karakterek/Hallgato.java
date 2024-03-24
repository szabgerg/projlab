package Karakterek;

import Szoba.Szoba;
import Targyak.Logarlec;
import Targyak.Camambert;
import Targyak.Romlandok.Rongy;
import Targyak.Romlandok.Sorospohar;
import Targyak.Romlandok.Tvsz;
import Targyak.Targyinventory;
import Targyak.Tranzisztor;

import java.util.Scanner;
/*
* Hallgato osztaly ami a jatekban szereplo hallgatokat reprezentalja
* A Hallgato osztaly a Karakter osztalybol szarmazik
* A Hallgato osztalyban a hallgato mozgasat, vedelmet, teleportalasat, es targyak aktivalasat valositjuk meg
 */
public class Hallgato extends Karakter {
	/*
	* Hallgato konstruktora
	* @param szoba - a szoba, ahol a hallgato tartozkodik
	* @param inventory - a hallgato targykeszlete
	*
	 */
	public Hallgato(Szoba szoba, Targyinventory inventory) {
		super(szoba, inventory);
		System.out.println("Hallgato letrehozasa\n");
		System.out.println("Hallgato eszkozkeszlete letrehozva\n");
		System.out.println("Hallgato szobaba teve\n");
	}
	/*
	* Az hallgato mozgasat megvalosito metodus
	* @param newSzoba - az uj szoba, ahova a hallgato mozog
	* A metodusban a hallgato mozgasat valositjuk meg, es a szobaban levo aktiv
	* targyakat is vizsgaljuk
	* Ha a szoba gazos, akkor a hallgato maszkot hasznal
	* Ha a hallgatonak nincs maszkja, akkor elejt mindent
	 */
	@Override
	public void mozog(Szoba newSzoba) {
		System.out.println("Hallgato mozog\n");
		super.mozog(newSzoba);
		newSzoba.getAktiv();
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
		scanner.close();
	}
	/*
	* Az hallgato targyak aktivalasat megvalosito metodus
	* A metodusban a hallgato targyak aktivalasat valositjuk meg
	* A hallgato kivalaszthat egy targyat, amit aktivalni szeretne
	* A hallgato valaszthat a kovetkezo targyak kozul:
	* 1. Tranzisztor
	* 2. Rongy
	* 3. Camambert
	* 4. Logarlec
	 */
	public void aktival() {
		Szoba jelenlegi = new Szoba();
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
				tranzisztor.aktival(jelenlegi);
				jelenlegi.setBentiTargyak(null);
				break;
			case 2:
				System.out.println("Rongy aktivalva\n");
				Rongy rongy = new Rongy();
				rongy.aktival(jelenlegi);
				jelenlegi.setBentiTargyak(null);
				break;
			case 3:
				System.out.println("Camambert aktivalva\n");
				Camambert camambert = new Camambert();
				camambert.aktival(jelenlegi);
				jelenlegi.setBentiTargyak(null);
				break;
			case 4:
				System.out.println("Logarlec aktivalva\n");
				Logarlec logarlec = new Logarlec();
				logarlec.aktival(jelenlegi);
				break;
			default:
				System.out.println("Nincs ilyen targy\n");
				break;
		} while ((choice > 0) && (choice < 5));
		System.out.println("A kivalasztott targy aktivalva\n");
		scanner.close();
	}
	/*
	* A metodusban a hallgato lelekelvetel elleni vedelmet valositjuk meg
	* Ha a hallgato rendelkezik tvsszel, akkor sikeresen vedekezik
	* Ha a hallgato nem rendelkezik tvsszel, akkor megvizsgaljuk, hogy rendelkezik-e sorospoharral
	* Ha a hallgato rendelkezik sorospoharral, akkor sikeresen vedekezik
	* Ha a hallgato nem rendelkezik sorospoharral, akkor meghal
	* @return - a hallgato sikeresen vedekezett-e
	 */

	public boolean vedekezes() {
		Targyinventory eszkozkeszlet = new Targyinventory();
		Szoba jelenlegi = new Szoba();
		System.out.println("Hallgato vedekezik\n");
		System.out.println("Van Tvsz-e? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();
		do if (choice.equals("I")) {
			Tvsz tvsz = new Tvsz();
			tvsz.aktival(null);
			System.out.println("A hallgato sikeresen vedekezik\n");
			System.out.println("Mennyi maradt meg a felhasznalasi idejebol? (n)\n");
			int left = scanner.nextInt();
			if (left == 1) {
				eszkozkeszlet.RemoveTargy(null);
			}
			scanner.close();
			return true;
		} else if (choice.equals("N")) {//söröspohár
			System.out.println("A hallgato nem rendelkezik tvsszel\n");
			System.out.println("A hallgatonak van sorospohara? (I/N)\n");
			String sors = scanner.nextLine();
			do if (sors.equals("I")) {
				Sorospohar sorsospohar = new Sorospohar();
				sorsospohar.aktival(jelenlegi);
				System.out.println("A hallgato sikeresen vedekezik\n");
				System.out.println("Mennyi maradt meg a felhasznalasi idejebol? (n)\n");
				int left = scanner.nextInt();
				if (left == 1) {
					eszkozkeszlet.RemoveTargy(null);
				}
				scanner.close();
				return true;
			} else if (sors.equals("N")) {
				System.out.println("A hallgato nem rendelkezik sorospoharral\n");
				System.out.println("A hallgato meghal\n");
				return false;
			} else {
				System.out.println("Nem ertelmezett valasz\n");
				scanner.close();
				return false;
			} while (sors.equals("I") || sors.equals("N"));
		} else {
			System.out.println("Nem ertelmezett valasz\n");
			scanner.close();
			return false;
		} while (choice.equals("I") || choice.equals("N"));
	}
	/*
	* A metodusban a hallgato teleportalasat valositjuk meg
	* A hallgato teleportalasahoz szukseges ket tranzisztor
	* Ha ossze vannak kapcsolva a tranzisztorok, akkor a hallgato teleportal
	 */
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
		scanner.close();
	}
	/*
	* A metodusban a hallgato tranzisztorok osszekapcsolasat valositjuk meg
	* Egy tranzisztor egyszerre csak egy masikkal kapcsolhato ossze
	*
	 */
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

	/*
	* A metodusban a hallgato szobajat allitjuk be
	* @param szoba - a beallitando szoba
	 */
	public void setSzoba(Szoba szoba) {
		System.out.println("Hallgato szoba atallitva\n");
	}
}
