package logarlec;

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
		System.out.println("Hallgato_letrehozva");
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
		if(!newSzoba.beenged() || !jelenlegi.getSzomszedok().contains(newSzoba)) {
			System.out.println("Hallgato_sikertelen_mozgas");
			return;
		}
		jelenlegi.kilep(this);
		newSzoba.getBentlevok().add(this);
		jelenlegi = newSzoba;
		System.out.println("Hallgato_sikeres_mozgas");
		if(newSzoba.getAktiv().getTargyak().isEmpty()) return;

		for (ITargy t: newSzoba.getAktiv().getTargyak()){
			t.akcio(this);
		}

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
	* 5. Legfrissito
	 */
	public void aktival(int hely) {
		ITargy t = eszkozkeszlet.getTargyak().get(hely);
		t.aktival(this);
		System.out.println("Hallgato_targy_aktivalva");
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
		for (ITargy t: eszkozkeszlet.getTargyak()){
			if(t.hasznal(this)) {
				System.out.println("Hallgato_sikeresen_vedekezett");
				return true;
			}
		}
		System.out.println("Hallgato_meghalt");
		return false;
	}

	/*
	* A metodusban a hallgato teleportalasat valositjuk meg
	* A hallgato teleportalasahoz szukseges ket tranzisztor
	* Ha ossze vannak kapcsolva a tranzisztorok, akkor a hallgato teleportal
	 */
	public void teleport(Szoba newSzoba) {
		if(newSzoba.beenged()){
			jelenlegi.kilep(this);
			newSzoba.getBentlevok().add(this);
			jelenlegi = newSzoba;
			for (ITargy t: newSzoba.getAktiv().getTargyak()){
				t.akcio(this);
			}
			System.out.println("hallgato_teleportalva");
		}
		else {
			System.out.println("hallgato_nem_teleportalhato");
		}
	}
	/*
	* A metodusban a hallgato tranzisztorok osszekapcsolasat valositjuk meg
	* Egy tranzisztor egyszerre csak egy masikkal kapcsolhato ossze
	*
	 */
	public void osszekapcsol(int mit, int mivel) {
		Tranzisztor t1 = (Tranzisztor) eszkozkeszlet.getTargyak().get(mit);
		Tranzisztor t2 = (Tranzisztor) eszkozkeszlet.getTargyak().get(mivel);
		if(t1.canPair() && t2.canPair()) {
			t1.setPar(t2);
			t2.setPar(t1);
			System.out.println("Tranzisztorok_osszekapcsolva");
		}
		else {
			System.out.println("Tranzisztorok_nem_kapcsolhatoak_ossze");
		}

	}

	/*
	* A metodusban a hallgato szobajat allitjuk be
	* @param szoba - a beallitando szoba
	 */
	public void setSzoba(Szoba szoba) {
		jelenlegi = szoba;
		System.out.println("Hallgato szoba atallitva");
	}
}
