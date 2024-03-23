package Controller;

import java.util.Scanner;
import Szoba.Szoba;
import Karakterek.Hallgato;
import Karakterek.Oktato;

import Targyak.Targyinventory;
import Targyak.Romlandok.Camambert;
import Targyak.Romlandok.Maszk;
import Targyak.Romlandok.Romlandok;
import Targyak.Romlandok.Rongy;
import Targyak.Romlandok.Sorospohar;
import Targyak.Romlandok.Tvsz;
import Targyak.ITargy;
import Targyak.Logarlec;
import Targyak.Tranzisztor;


public class Controller {
    public Controller() {
        System.out.println("Controller letrehozva");
    }

    public void init() {
        Szoba szoba_A = new Szoba();    // Szoba konstruktorában: Szoba létrehozva + Szoba neve
        Szoba szoba_B = new Szoba();

        szoba_A.setSzomszedok(szoba_B);
        szoba_B.setSzomszedok(szoba_A);

        //System.out.println("Két szoba inicializálása megtörtént");

        Hallgato hallgato = new Hallgato();
        Oktato oktato = new Oktato();

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
        t4. AddTargy(TVSZ);

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

    public void nextRound() {
        System.out.println("Letelt egy kor? (I/N)\n");
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (sc.nextLine().equals('I')) {
                System.out.println("Letelt egy kör, indul a következő");
                break;
            } else if (sc.nextLine().equals('N')) {
                System.out.println("Nem telt le még a kör");
                break;
            } else {
                System.out.println("Érvénytelen válasz, adj meg újat!");
            }
        }
    }

    public void nextPlayer() {}

    public void endGame() {
        System.out.println("Véget ért a játék? (I/N)\n");
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (sc.nextLine().equals('I')) {
                System.out.println("Vége a játéknak");
                break;
            } else if (sc.nextLine().equals('N')) {
                System.out.println("Nincs még vége a játéknak");
                break;
            } else {
                System.out.println("Érvénytelen válasz, adj meg újat!");
            }
        }
    }
}
