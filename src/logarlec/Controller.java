package logarlec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Kontroller osztály, a játékban azokat a metódusokat valósítja meg,
 * amik egyik osztályhoz sem tratoznak szorosan.
 * Kívülről nyúl bele a játék irányításába.
 * A kontroller képes inicializálni egy pályát, beállítani a szobákat, karakrereket és tárgyakat,
 * végrehajtj a körönkénti élettartamcsökkentést, felelős a következő játékos értesítéséért illetve a
 * játék zárását is ez az osztály kezeli.
 */
public class Controller {

	//játékosok száma
	private int jatekosok;
	//soron lévő játékos sorszáma 1-től indexelve
	private int aktualisJatekos;
	private HashMap<Integer, Karakter> karakterMap = new HashMap<>();
	private List<Szoba> collectedSzobak = new ArrayList<>();
	private Set<Szoba> visitedSzobak = new HashSet<>();
	

	/*
	 * Kontruktor, ami létrehoz egy logarlec.Controller-t
	 */
	public Controller() {
		System.out.println("logarlec.Controller_letrehozva");
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
		//System.out.println("szoba_A létrehozva");
		Szoba szoba_B = new Szoba();
		//System.out.println("szoba_B létrehozva");

		List<Szoba> szobaLista1 = new ArrayList<>();
		List<Szoba> szobaLista2 = new ArrayList<>();

		szobaLista1.add(szoba_A);
		szobaLista2.add(szoba_B);

		szoba_A.setSzomszedok(szobaLista2);
		szoba_B.setSzomszedok(szobaLista1);

		Hallgato hallgato = new Hallgato(null, null);
		Oktato oktato = new Oktato(null, null);

		Targyinventory t1 = new Targyinventory();
		Targyinventory t2 = new Targyinventory();
		Targyinventory t3 = new Targyinventory();
		Targyinventory t4 = new Targyinventory();

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

		szoba_A.setBentiTargyak(t1);
		szoba_B.setBentiTargyak(t2);
		hallgato.setEszkozkeszlet(t3);
		oktato.setEszkozkeszlet(t4);

		System.out.println("logarlec.Controller_inicializalas");
	}

	public List<Szoba> szobakatOsszegyujt(Szoba startSzoba) {
        // Ellenőrizzük, hogy a kiinduló szoba nem lett-e már látogatva
        if (!visitedSzobak.contains(startSzoba)) {
            // Ha még nem, akkor hozzáadjuk a látogatott szobákhoz
            visitedSzobak.add(startSzoba);
            // Hozzáadjuk a kiinduló szobát az összegyűjtött szobákhoz
            collectedSzobak.add(startSzoba);

            // Szomszédok lekérése
            List<Szoba> neighbors = startSzoba.getSzomszedok();

            // Minden szomszédra rekurzívan meghívjuk a collectAllRooms metódust
            for (Szoba neighbor : neighbors) {
                szobakatOsszegyujt(neighbor);
            }
        }
        // Visszatérünk az összegyűjtött szobák listájával
        return collectedSzobak;
    }


	/*
	 * Végrehajtja a rongy élettartamának csökkentését körönként, ha
	 * a hátralevő idejük 1-el való csökkentése már 0-nak felel meg, akkor
	 * az 1-el való értékcsökkenés után el is tűnik az aktív tárgyak listájából, azaz megszűnik
	 */
	public void nextRound(Szoba szoba) {
		System.out.println("Kovetkezo_kor_kezdete");
		szobakatOsszegyujt(szoba);

		// Végigmegyünk az összes szobán
		for (Szoba s : collectedSzobak) {
			Targyinventory aktivTargyak = s.getAktiv();
	
			// Ellenőrizzük az aktív tárgyakat
			for (ITargy targy : aktivTargyak.getTargyak()) {
				// Ha a tárgynak 0 az élettartama, akkor eltávolítjuk az aktív tárgyak listából
				if (targy.romlik() == 0) {
					aktivTargyak.RemoveTargy(targy);
				}
			}
		}
	}

	/*
	 * Következő játékos kiválasztása
	 */
	public void nextPlayer() {
		aktualisJatekos++;
		if(aktualisJatekos > jatekosok){
			aktualisJatekos = 1;
		}
	}

	/*
	 * Végrehajtja a játék végének lekezelését, ez két esetben fordulhat elő:
	 * 1. hallgató felveszi a logarlécet
	 * 2. oktatók mindegyik hallgatónak elvették a lelkét
	 */
	public void endGame() {
		System.out.println("Jatek_vege");
		System.exit(0);
	}
}
