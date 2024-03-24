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

    public void nextRound() {
        System.out.println("Következő kör kezdete\n");
        
        System.out.println("Van Rongy a tárgyak között? (I/N)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        ////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Rongy r = new Rongy();
        Szoba s = new Szoba();
        s.getAktiv();

        do if(choice.equals("I")){
            System.out.println("Mennyi a rongy élettartama? (n)");
            int left = scanner.nextInt();
            if(left > 1){
                r.romlik();
            } else if(left == 1){
                r.romlik();
                s.removeAktiv(r);
                System.out.println("A Rongy teljesen megszáradt, törlődik.");
            }
        } else if(choice.equals("N")){
            System.out.println("Nincs rongy a szobában, ami száradjon.");
        } else {
            System.out.println("Érvénytelen válasz\n");
        } while (choice.equals("I") || choice.equals("N"));

        scanner.close();
    }

    public void nextPlayer() {
        System.out.println("Az aktuális játékos befejezte a körét? (I/N)\n");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        do if (choice.equals("I")) {
			System.out.println("A sor a következő játákoson van!\n");

		} else if (choice.equals("N")) {
			System.out.println("Folytassa a körét!\n");
		} else {
			System.out.println("Érvénytelen válasz\n");
		} while (choice.equals("I") || choice.equals("N"));

    }

    //TODO
    public void endGame() {

        System.out.println("Véget ért a játék? (I/N)\n");
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (sc.nextLine().equals('I')) {
                System.out.println("Vége a játéknak");
                Hallgato h1 = new Hallgato();
                Szoba sz1 = new Szoba();
                if(h1.getSzoba() == null){
                    sz1.kilep(h1);
                    
                }
                break;
            } else if (sc.nextLine().equals('N')) {
                System.out.println("Nincs még vége a játéknak");
                break;
            } else {
                System.out.println("Érvénytelen válasz, adj meg újat!");
            }
        }
        sc.close();
    }
}
