package Controller;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import Szoba.Szoba;
import Karakterek.Hallgato;
import Karakterek.Oktato;

import Targyak.Targyinventory;
import Targyak.Camambert;
import Targyak.Romlandok.Maszk;
import Targyak.Romlandok.Rongy;
import Targyak.Romlandok.Sorospohar;
import Targyak.Romlandok.Tvsz;
import Targyak.Logarlec;
import Targyak.Tranzisztor;

/*
 * Kontroller osztály, a játékban azokat a metódusokat valósítja meg,
 * amik egyik osztályhoz sem tratoznak szorosan.
 * Kívülről nyúl bele a játék irányításába.
 * A kontroller képes inicializálni egy pályát, beállítani a szobákat, karakrereket és tárgyakat,
 * végrehajtj a körönkénti élettartamcsökkentést, felelős a következő játékos értesítéséért illetve a
 * játék zárását is ez az osztály kezeli.
 */
public class Controller {

	/*
	 * Kontruktor, ami létrehoz egy Controller-t
	 */
	public Controller() {
		System.out.println("Controller letrehozva");
	}

	/*
	 * Inicializációt hajtja végre, létrehoz két szobát, amelyeket egymás szomszédainak állít be.
	 * Létrehoz egy oktató és egy hallgató játékost.
	 * Létrehoz minden tárgyból egyet (logarléc, tranzisztor, camambert, maszk, rongy, tvsz, sorospohar)
	 * A létrehozott tárgyakat (4db (t1-t4) itt létrehozott) gyűjteményekbe pakolja és ezeket állítja be
	 * a szobák aktív tárgyainak és a karakterek eszközkészletének
	 */
	public void init() {
		Szoba szoba_A = new Szoba();    // Szoba konstruktorában: Szoba létrehozva + Szoba neve
		System.out.println("szoba_A létrehozva");
		Szoba szoba_B = new Szoba();
		System.out.println("szoba_B létrehozva");

		List<Szoba> szobaLista1 = new ArrayList<>();
		List<Szoba> szobaLista2 = new ArrayList<>();

		szobaLista1.add(szoba_A);
		szobaLista2.add(szoba_B);

		szoba_A.setSzomszedok(szobaLista2);
		szoba_B.setSzomszedok(szobaLista1);

		//System.out.println("Két szoba inicializálása megtörtént");

		Hallgato hallgato = new Hallgato(null, null);
		Oktato oktato = new Oktato(null, null);

		//System.out.println("Két karakter sikeresen létrehozva");

		Targyinventory t1 = new Targyinventory();
		Targyinventory t2 = new Targyinventory();
		Targyinventory t3 = new Targyinventory();
		Targyinventory t4 = new Targyinventory();

		//System.out.println("Négy tárgygyűjtemény létrehozva");
		Logarlec logarlec = new Logarlec();
		t1.AddTargy(logarlec);

		Tranzisztor tranz1 = new Tranzisztor();
		t2.AddTargy(tranz1);

		Camambert camembert = new Camambert();
		t3.AddTargy(camembert);

		Tvsz TVSZ = new Tvsz();
		t4.AddTargy(TVSZ);

		Maszk maszk = new Maszk();
		t1.AddTargy(maszk);

		Rongy rongy = new Rongy();
		t2.AddTargy(rongy);

		Sorospohar sor = new Sorospohar();
		t3.AddTargy(sor);

		//System.out.println("Minden tárgyból egy létrehozva és gyűjteményhez adva");

		szoba_A.setBentiTargyak(t1);
		szoba_B.setBentiTargyak(t2);
		hallgato.setEszkozkeszlet(t3);
		oktato.setEszkozkeszlet(t4);

	}

	/*
	 * Végrehajtja a rongy élettartamának csökkentését körönként, ha
	 * a hátralevő idejük 1-el való csökkentése már 0-nak felel meg, akkor
	 * az 1-el való értékcsökkenés után el is tűnik az aktív tárgyak listájából, azaz megszűnik
	 */
	public void nextRound() {
		System.out.println("Következő kör kezdete\n");

		System.out.println("Van Rongy a tárgyak között? (I/N)");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();


		do if (choice.equals("I")) {
		    Rongy r = new Rongy();
		    Szoba s = new Szoba();
		    s.getAktiv();
			System.out.println("Mennyi a rongy élettartama? (n)");
			int left = scanner.nextInt();
			if (left > 1) {
				r.romlik();
				scanner.close();
                return;
			} else if (left == 1) {
				r.romlik();
				s.removeAktiv(r);
				System.out.println("A Rongy teljesen megszáradt, törlődik.");
				scanner.close();
                return;
			}
		} else if (choice.equals("N")) {
			System.out.println("Nincs rongy a szobában, ami száradjon.");
		} else {
			System.out.println("Érvénytelen válasz\n");
		} while (!(choice.equals("I") || choice.equals("N")));

		scanner.close();
	}

	/*
	 * Követkzeő játékos kiválasztása
	 */
	public void nextPlayer() {
        System.out.println("Van még élő hallgató? (I/N)\n");
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            choice = scanner.nextLine();
            if (choice.equals("I")) {
                System.out.println("Az aktuális játékos befejezte a körét? (I/N)\n");
                choice = scanner.nextLine();
                do if (choice.equals("I")) {
                    System.out.println("A sor a következő játákoson van!\n");

                } else if (choice.equals("N")) {
                    System.out.println("Folytassa a körét!\n");
                } else {
                    System.out.println("Érvénytelen válasz\n");
                } while (!(choice.equals("I") || choice.equals("N")));
            } else if (choice.equals("N")) {
                System.out.println("Az oktatók nyertek\n");
                this.endGame();
            } else {
                System.out.println("Érvénytelen válasz\n");
            }
        }while (!(choice.equals("I") || choice.equals("N"))) ;

            scanner.close();

    }

	//TODO
	/*
	 * Végrehajtja a játék végének lekezelését, ez két esetben fordulhat elő:
	 * 1. hallgató felveszi a logarlécet
	 * 2. oktatók mindegyik hallgatónak elvették a lelkét
	 */
	public void endGame() {
		System.out.println("Játék vége\n");
	}
}
