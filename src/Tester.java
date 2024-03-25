import java.util.Scanner;
import Controller.Controller;
import Szoba.Szoba;
import Targyak.Targyinventory;
import Targyak.Tranzisztor;


import Karakterek.Hallgato;
import Karakterek.Oktato;
import Karakterek.Karakter;
import Targyak.Romlandok.Rongy;
import Targyak.Camambert;

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
    public void startMenu(){
    Scanner scanner = new Scanner(System.in);

    System.out.println("1. Játék elindítása(Init)\n" +
                   "2. Tárgyfelvétel\n" +
                   "3. Tárgy letevése\n" +
                   "4. Karakter mozgás szobák között\n" +
                   "5. Hallgatótól lélek elvétel\n" +
                   "6. Következő játékos\n" +
                   "7. Tárgy használat\n" +
                   "8. Karakter sikeresen összekapcsol két tranzisztort\n" +
                   "9. Karakter sikeresen teleportál tranzisztorral\n" +
                   "10. Körváltás\n" +
                   "11. Egy szoba kettővé válik szét\n" +
                   "12. Két szoba egyesül egy szobává\n" );
    
    System.out.println("Add meg a végrhajtandó művelet menüjének számát");
    int num = scanner.nextInt();

    while(num < 1 || num > 24){
        System.out.println("Hibás szám, adj meg újat!");
        num = scanner.nextInt();
        scanner.close();
    }

    //Mintaobjektumok, amiken keresztül meg lehet hívni a kiválasztott metódusokat
    Controller controller = new Controller();
    Szoba sz1 = new Szoba();
    Szoba sz2 = new Szoba();
    Targyinventory t1 = new Targyinventory();
    Karakter k1 = new Karakter(sz1, t1);
    Oktato o1 = new Oktato(sz1, t1);
    Hallgato h1 = new Hallgato(sz1, t1);

    Rongy r = new Rongy();
    Camambert c = new Camambert();
    Tranzisztor tr1 = new Tranzisztor();
    Tranzisztor tr2 = new Tranzisztor();

    switch(num){

        //Init                                                  ---------------->>> EZ JÓ
        case 1:
            controller.init();
            System.out.println("1-es teszteset sikeresen lefutott\n");
            break;

        // Tárgyfelvétel                                        ---------------->>> EZ MOST JÓ SZERINTEM
        case 2:
            h1.felvesz();
            System.out.println("2-es teszteset sikeresen lefutott\n");
            break;

        //Tárgy letevése                                        ---------------->>> EZ MOST JÓ SZERINTEM
        case 3:
            h1.letesz();
            System.out.println("3-as teszteset sikeresen lefutott\n");
            break;

        //Sikeres mozgás                                        ---------------->>> EZ MOST JÓ SZERINTEM
        case 4:
            k1.mozog(sz2);
            System.out.println("4-es teszteset sikeresen lefutott\n");
            break;

        //Lélek elvétel                                         ---------------->>> EZ MOST JÓ SZERINTEM
        case 5:
            h1.vedekezes();
            System.out.println("5-ös teszteset sikeresen lefutott\n");
            break;
    
        //Következő játékos                                     ---------------->>> EZ MOST JÓ SZERINTEM
        case 6:
            controller.nextPlayer();
            System.out.println("6-os teszteset sikeresen lefutott\n");
            break;

        //Tárgy használata                                      ---------------->>> EZ MOST JÓ SZERINTEM
        case 7:
            h1.aktival();
            System.out.println("7-es teszteset sikeresen lefutott\n");
            break;

        //Sikeres tranzisztorpárosítás                          ---------------->>> EZ MOST JÓ SZERINTEM
        case 8:
            h1.osszekapcsol();
            System.out.println("8-as teszteset sikeresen lefutott\n");
            break;

        //TODO: NO LINE FOUND EXCEPTION
        //Teleportálás
        case 9:
            h1.teleport();
            System.out.println("9-es teszteset sikeresen lefutott\n");
            break;

        //Új kör                                                --------------->>> EZ MOST JÓ SZERINTEM
        case 10:                                                        
            controller.nextRound();
            System.out.println("10-es teszteset sikeresen lefutott\n");
            break;

        //Szoba kettővé                                         ---------------->>> EZ MOST JÓ SZERINTEM
        case 11:
            sz1.split(sz1);
            System.out.println("11-es teszteset sikeresen lefutott\n");
            break;

        //Szobak egyesülnek                                     ---------------->>> EZ MOST JÓ SZERINTEM
        case 12:
            sz1.merge(sz2);
            System.out.println("12-es teszteset sikeresen lefutott\n");
            break;

        default:
            break;
     }
    }

}
