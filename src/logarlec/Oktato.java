package logarlec;

import java.util.Scanner;
/*
 * Oktato osztaly, ami a Karakter osztalybol szarmazik
 *
 */

public class Oktato extends Karakter {
	/*
	 * Oktato konstruktora
	 *
	 * @param szoba - a szoba, ahol az oktato tartozkodik
	 * @param inventory - az oktato targykeszlete
	 */
	public Oktato(Szoba szoba, Targyinventory inventory) {
		super(szoba, inventory);
		System.out.println("Oktato letrehozasa\n");
		System.out.println("Oktato eszkozkeszlete letrehozva\n");
		System.out.println("Oktato szobaba teve\n");
	}
	/*
	 * Az oktato mozgasat megvalosito metodus
	 *
	 * @param newSzoba - az uj szoba, ahova az oktato mozog
	 *
	 * A metodusban az oktato mozgasat valositjuk meg, es a szobaban levo aktiv
	 * targyakat is vizsgaljuk
	 * Ha a szoba gazos, akkor az oktato mindent elejt
	 * Ha a szobaban rongy van, akkor az oktato megbenul
	 * Ha a szobaban hallgato van, akkor az oktato elveszi a lelket a hallgatonak
	 */
	@Override
	public void mozog(Szoba newSzoba) {
		if (bena) return;
		if(Proto.getRandVal()<0) {//ha nincs igazi random akkor az oktató irányítható
			if (!newSzoba.beenged() || !jelenlegi.getSzomszedok().contains(newSzoba)) return;

			jelenlegi.kilep(this);
			newSzoba.getBentlevok().add(this);
			jelenlegi = newSzoba;

			if (newSzoba.getAktiv().getTargyak().isEmpty()) return;

			for (ITargy t : newSzoba.getAktiv().getTargyak()) {
				t.akcio(this);
			}

			if (bena) return;
			lelekelvetel();
		}else {//van random tehát az oktató egy "ai" lesz
			double rand = Proto.getRandVal();
			if(rand <0.5) {
				random_letesz();
				random_felvesz();
				return;
			}
			int randint = (int) (rand * 10);
			if(randint > jelenlegi.getSzomszedok().size()){
				randint = jelenlegi.getSzomszedok().size()-1;
			}
			newSzoba = jelenlegi.getSzomszedok().get(randint);

			if (!newSzoba.beenged() || !jelenlegi.getSzomszedok().contains(newSzoba)) return;

			jelenlegi.kilep(this);
			newSzoba.getBentlevok().add(this);
			jelenlegi = newSzoba;

			if (newSzoba.getAktiv().getTargyak().isEmpty()) return;

			for (ITargy t : newSzoba.getAktiv().getTargyak()) {
				t.akcio(this);
			}

			if (bena) return;
			lelekelvetel();
			random_letesz();
			random_felvesz();
		}

	}
	/*
	 * Az oktato bena valtozojat allitja be
	 * Ha az oktato bena, akkor az oktato nem tud mozogni es cselekdni
	 * @param b - az allitando ertek
	 */
	void setbena(boolean b) {
		bena = b;
	}

	/*
	 * Az oktato lelekelvetel cselekedetet valositja meg
	 * Az oktato lelekelvetelkor a hallgatoktol lelket vesz el
	 * Ha a hallgato megvedi magat, akkor az oktato nem tud lelket venni
	 * Ha a hallgato nem tudja megvedeni magat, akkor a hallgato mindent elejt es szamara
	 * vege a jateknak
	 */
	void lelekelvetel() {
		for (Karakter karakter : jelenlegi.getBentlevok()) {
			if (karakter.vedekezes()) {
				karakter.mindentelejt();
			}
		}
	}

	void random_felvesz() {
		double rand = Proto.getRandVal();
		if(rand < 0.5 || eszkozkeszlet.getTargyak().size() >= 5) return;
		int randint = (int) (rand * 10);
		if(randint > jelenlegi.getBentiTargyak().getTargyak().size()){
			randint = jelenlegi.getBentiTargyak().getTargyak().size()-1;
		}

		felvesz(randint);
	}

	void random_letesz() {
		double rand = Proto.getRandVal();
		if(rand < 0.5) return;
		int randint = (int) (rand * 10);
		if(randint > eszkozkeszlet.getTargyak().size()){
			randint = eszkozkeszlet.getTargyak().size()-1;
		}
		letesz(randint);

	}

}
