import java.util.Scanner;
import Controller.Controller;
import Szoba.Szoba;
import Targyak.Targyinventory;

import java.util.ArrayList;
import java.util.List;

import Karakterek.Hallgato;
import Karakterek.Oktato;
import Karakterek.Karakter;

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
                   "2. Sikeres tárgyfelvétel\n" +
                   "3. Sikertelen tárgyfelvétel\n" +
                   "4. Tárgy letevése\n" +
                   "5. Sikeres karakter mozgás szobák között\n" +
                   "6. Sikertelen szobaváltási kísérlet\n" +
                   "7. Sikeres hallgató-lélek elvétel\n" +
                   "8. Utolsó játékos kiesése\n" +
                   "9. Sikertelen lélekelvétel TVSZ használata miatt\n" +
                   "10. Sikertelen lélekelvétel söröspohár miatt\n" +
                   "11. Rongy használat\n" +
                   "12. Hallgatók nyernek\n" +
                   "13. Camembert begázosítja a szobát\n" +
                   "14. Karakter sikeresen összekapcsol két tranzisztort\n" +
                   "15. Karakter sikeresen teleportál tranzisztorral\n" +
                   "16. Tranzisztor felhasználás folyamatának belső működése\n" +
                   "17. Gázos szobában jelenlét maszk nélkül\n" +
                   "18. Gázos szoba kivédése maszkkal\n" +
                   "19. Rongy száradás\n" +
                   "20. A rongy tárgy megszáradt\n" +
                   "21. Egy szoba kettővé válik szét\n" +
                   "22. Két szoba egyesül egy szobává\n" +
                   "23. Elátkozott szoba működése játék közben\n" +
                   "24. Oktató belép egy szobába, ahol aktív rongy van és lebénul\n");
    
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

    switch(num){

        //Init
        case 1:
            controller.init();
            break;

        //Sikeres tárgyfelvétel
        case 2:
            h1.felvesz();
            break;

        //Sikertelen tárgyfelvétel
        case 3:
            h1.felvesz();
            break;

        //Tárgy letevése
        case 4: 
            h1.letesz();
            break;

        //Sikeres mozgás
        case 5:
            k1.mozog(sz2);
            break;

        //Sikertelen mozgás
        case 6:
            k1.mozog(sz2);
            break;

        //Sikeres lélek elvétel
        case 7:
            h1.vedekezes();
            break;

        //Utolso játékos kiesés
        case 8:
            controller.endGame();
            break;

        //Sikertelen lélekelvétel TVSZ miatt
        case 9:
            h1.vedekezes();
            break;
        
        //Sikertelen lélekelvétel söröspohár miatt
        case 10:
            h1.vedekezes();
            break;

        //Rongy használata
        case 11:

            break;

        //Hallgatók nyernek
        case 12:
            h1.felvesz();
            break;

        //Camembert gázosít
        case 13:

            break;

        //Sikeres tranzisztorpárosítás
        case 14:

            break;

        //Teleportálás
        case 15:

            break;

        //Tranzisztor belülről
        case 16:

            break;

        //Gázos szoba maszk nélkül
        case 17:
            h1.mozog(sz2);
            break;

        //Gázos szoba maszkkal
        case 18:
            h1.mozog(sz2);
            break;

        //Rongy szárad
        case 19:
            controller.nextRound();
            break;

        //Rongy megszárad
        case 20:
            controller.nextRound();
            break;

        //Szoba kettővé
        case 21:            
            sz1.split(sz1);
            break;

        //Szobak egyesülnek
        case 22:
            sz1.merge(sz2);
            break;

        //Elatkozott szoba működése
        case 23:
            break;

        //Oktató megbénul a rongytól
        case 24:

            break;
        default:
            break;
     }
    }

}
