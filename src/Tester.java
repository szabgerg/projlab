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
                   "4. Tárgy letevése\n" +
                   "5. Karakter mozgás szobák között\n" +
                   "7. Hallgató-lélek elvétel\n" +
                   "8. Utolsó játékos kiesése\n" +
                   "11. Rongy használat\n" +
                   "14. Karakter sikeresen összekapcsol két tranzisztort\n" +
                   "15. Karakter sikeresen teleportál tranzisztorral\n" +
                   "19. Rongy száradás\n" +
                   "21. Egy szoba kettővé válik szét\n" +
                   "22. Két szoba egyesül egy szobává\n" );
    
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

        //Init
        case 1:
            controller.init();
            System.out.println("1-es teszteset sikeresen lefutott\n");
            break;

        // Tárgyfelvétel                                         ---------------->>> EZ MOST JÓ SZERINTEM
        case 2:
            h1.felvesz();
            System.out.println("2-es teszteset sikeresen lefutott\n");
            break;

        //Tárgy letevése                                        ---------------->>> EZ MOST JÓ SZERINTEM
        case 4:
            h1.letesz();
            System.out.println("4-es teszteset sikeresen lefutott\n");
            break;

        //Sikeres mozgás                                        ---------------->>> EZ MOST JÓ SZERINTEM
        case 5:
            k1.mozog(sz2);
            System.out.println("5-ös teszteset sikeresen lefutott\n");
            break;

        //Lélek elvétel                                 ---------------->>> EZ MOST JÓ SZERINTEM
        case 7:
            h1.vedekezes();
            System.out.println("7-es teszteset sikeresen lefutott\n");
            break;
        //TODO: szerintem itt nem elég hogy lefut az endgame, de javitsatok ki ha tévedek
        //Utolso játékos kiesés
        case 8:
            controller.endGame();
            System.out.println("8-as teszteset sikeresen lefutott\n");
            break;

        //Tárgy használata
        case 11:
            r.aktival( sz1);
            System.out.println("11-es teszteset sikeresen lefutott\n");
            break;

        //Sikeres tranzisztorpárosítás
        case 14:
            h1.osszekapcsol();
            System.out.println("14-es teszteset sikeresen lefutott\n");
            break;

        //Teleportálás
        case 15:
            h1.teleport();
            System.out.println("15-ös teszteset sikeresen lefutott\n");
            break;

        //Rongy szárad(új kör)
        case 19:
            controller.nextRound();
            System.out.println("19-es teszteset sikeresen lefutott\n");
            break;

        //Szoba kettővé
        case 21:
            sz1.split(sz1);
            System.out.println("21-es teszteset sikeresen lefutott\n");
            break;

        //Szobak egyesülnek
        case 22:
            sz1.merge(sz2);
            System.out.println("22-es teszteset sikeresen lefutott\n");
            break;

        default:
            break;
     }
    }

}
