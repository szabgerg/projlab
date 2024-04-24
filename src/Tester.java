import Controller.Controller;
import Karakterek.Hallgato;
import Karakterek.Karakter;
import Karakterek.Oktato;
import Szoba.Szoba;
import Targyak.Camambert;
import Targyak.Romlandok.Rongy;
import Targyak.Targyinventory;
import Targyak.Tranzisztor;

import java.util.Scanner;

/* Tester osztály, feladata a menüsor felkínálása a felhasználó számára
 * és a kiválasztott szám szerinti művelet végrehajtása a mintabjektumok segítségével.
 */
public class Tester {
	/* Tester konstruktora, felelős egy Tester létrejöttéért
	 * ennek sikerességéről szöveggel értesíti a felhasználót
	 */
	public Tester() {
		System.out.println("Tester létrejött");
	}

	/* A starMenu felsorolja, hogy milyen műveletek közül választhat a felhasználó
	 * egy számot megadva az adott menühöz kapcsolódó művelet fut le.
	 * Ha 0-nál kisebb vagy 24-nél nagyobb számot ad meg a felhasználó, a program újra fogja kérni a számot.
	 */
	public void startMenu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1. Játék elindítása(Init)\n" +
							"2. Tárgyfelvétel\n" +
							"3. Tárgy letevése\n" +
							"4. Hallgató mozgása szobák között\n" +
							"5. Oktató mozgása szobák között\n" +
							"6. Hallgatótól lélek elvétel\n" +
							"7. Következő játékos\n" +
							"8. Tárgy használat\n" +
							"9. Karakter sikeresen összekapcsol két tranzisztort\n" +
							"10. Karakter sikeresen teleportál tranzisztorral\n" +
							"11. Körváltás\n" +
							"12. Egy szoba kettővé válik szét\n" +
							"13. Két szoba egyesül egy szobává\n");

		System.out.println("Add meg a végrehajtandó művelet menüjének számát");
		int num = scanner.nextInt();

		while (num < 1 || num > 13) {
			System.out.println("Hibás szám, adj meg újat!");
			num = scanner.nextInt();
			scanner.close();
		}

		//Mintaobjektumok, amiken keresztül meg lehet hívni a kiválasztott metódusokat
		Controller controller = new Controller();
		Szoba sz1 = new Szoba();
		Szoba sz2 = new Szoba();
		Targyinventory t1 = new Targyinventory();
		Oktato o1 = new Oktato(sz1, t1);
		Hallgato h1 = new Hallgato(sz1, t1);

		Rongy r = new Rongy();
		Camambert c = new Camambert();
		Tranzisztor tr1 = new Tranzisztor();
		Tranzisztor tr2 = new Tranzisztor();

		switch (num) {

			//Init                                                  ---------------->>> EZ JÓ
			case 1:
				controller.init();
				System.out.println("1-es teszteset sikeresen lefutott\n");
				break;

			// Tárgyfelvétel                                        ---------------->>> EZ MOST JÓ SZERINTEM
			case 2:
				h1.felvesz(0);
				System.out.println("2-es teszteset sikeresen lefutott\n");
				break;

			//Tárgy letevése                                        ---------------->>> EZ MOST JÓ SZERINTEM
			case 3:
				h1.letesz(0);
				System.out.println("3-as teszteset sikeresen lefutott\n");
				break;

			//Hallgató mozgás                                        ---------------->>> EZ MOST JÓ SZERINTEM
			case 4:
				h1.mozog(sz2);
				System.out.println("4-es teszteset sikeresen lefutott\n");
				break;
			//Oktató mozgás
			case 5:
				o1.mozog(sz2);
				System.out.println("5-ös teszteset sikeresen lefutott\n");
				break;

			//Lélek elvétel                                         ---------------->>> EZ MOST JÓ SZERINTEM
			case 6:
				h1.vedekezes();
				System.out.println("6-os teszteset sikeresen lefutott\n");
				break;

			//Következő játékos                                     ---------------->>> EZ MOST JÓ SZERINTEM
			case 7:
				controller.nextPlayer();
				System.out.println("7-es teszteset sikeresen lefutott\n");
				break;

			//Tárgy használata                                      ---------------->>> EZ MOST JÓ SZERINTEM
			case 8:
				h1.aktival(0);
				System.out.println("8-as teszteset sikeresen lefutott\n");
				break;

			//Sikeres tranzisztorpárosítás                          ---------------->>> EZ MOST JÓ SZERINTEM
			case 9:
				h1.osszekapcsol(1,2);
				System.out.println("9-es teszteset sikeresen lefutott\n");
				break;

			//Teleportálás
			case 10:
				h1.teleport(0);
				System.out.println("10-es teszteset sikeresen lefutott\n");
				break;

			//Új kör                                                --------------->>> EZ MOST JÓ SZERINTEM
			case 11:
				controller.nextRound();
				System.out.println("11-es teszteset sikeresen lefutott\n");
				break;

			//Szoba kettővé                                         ---------------->>> EZ MOST JÓ SZERINTEM
			case 12:
				sz1.split();
				System.out.println("12-es teszteset sikeresen lefutott\n");
				break;

			//Szobak egyesülnek                                     ---------------->>> EZ MOST JÓ SZERINTEM
			case 13:
				sz1.merge(sz2);
				System.out.println("13-as teszteset sikeresen lefutott\n");
				break;

			default:
				break;
		}
	}

}
