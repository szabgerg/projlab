package Karakterek;

import Szoba.Szoba;
import Targyak.Targyinventory;

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
		System.out.println("Oktato mozog\n");
		super.mozog(newSzoba);
		jelenlegi.getAktiv();
		System.out.println("A szoba gazos? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		do if(choice.equals("I")) {
			System.out.println("A szoba gazos\n");
			mindentelejt();
			System.out.println("Az oktato elejt mindent\n");
		} else if(choice.equals("N")) {
			System.out.println("A szoba nem gazos\n");
		} else {
			System.out.println("Nem ertelmezett valasz\n");
		} while(choice.equals("I") || choice.equals("N"));

		System.out.println("Van a szobaban rongy? (I/N)\n");
		String rongy = scanner.nextLine();
		do if(rongy.equals("I")) {
			System.out.println("Az oktato megbenul\n");
			setbena(true);
			return;
		} else if(rongy.equals("N")) {
			System.out.println("Az oktato nem benul meg\n");
		} else {
			System.out.println("Nem ertelmezett valasz\n");
		} while(rongy.equals("I") || rongy.equals("N"));

		jelenlegi.getBentlevok();
		System.out.println("Van a szobaban hallgató? (I/N)\n");
		String hallgato = scanner.nextLine();
		do if(hallgato.equals("I")) {
			System.out.println("Az oktato elveszi a lelket a hallgatonak\n");
			lelekelvetel();
			return;
		} else if(hallgato.equals("N")) {
			System.out.println("Nincs a szobaban hallgato\n");
		} else {
			System.out.println("Nem ertelmezett valasz\n");
		} while(hallgato.equals("I") || hallgato.equals("N"));
	}
	/*
	 * Az oktato bena valtozojat allitja be
	 * Ha az oktato bena, akkor az oktato nem tud mozogni es cselekdni
	 * @param b - az allitando ertek
	 */
	void setbena(boolean b) {
		System.out.println("Oktato setbena\n");
	}

	/*
	 * Az oktato lelekelvetel cselekedetet valositja meg
	 * Az oktato lelekelvetelkor a hallgatoktol lelket vesz el
	 * Ha a hallgato megvedi magat, akkor az oktato nem tud lelket venni
	 * Ha a hallgato nem tudja megvedeni magat, akkor a hallgato mindent elejt es szamara
	 * vege a jateknak
	 */
	void lelekelvetel() {
		System.out.println("Oktato lelekelvetel\n");
		jelenlegi.getBentlevok();
		Hallgato hallgato = new Hallgato(new Szoba(), new Targyinventory());
		boolean siker = hallgato.vedekezes();
		if(siker) {
			System.out.println("A hallgato megvedi magat\n");
		} else {
			System.out.println("A hallgato nem tudta megvedeni magat\n");
			hallgato.mindentelejt();
			hallgato.jelenlegi = null;

		}
	}

}
