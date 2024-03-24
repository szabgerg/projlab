package Targyak.Romlandok;

import java.util.Scanner;

import Szoba.Szoba;

// A Sorospohar osztály, amely a Romlandok osztályból származik
public class Sorospohar extends Romlandok{
    // Konstruktor, amely létrehoz egy új Sorospohar objektumot
    public Sorospohar() {
        System.out.print("Sorospohár létrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Szoba s) {
        System.out.print("Sorospohár használva");
        // Hozzáadja a sorospoharat az aktiv inventoryhoz
        s.getAktiv().AddTargy(this);
        // Csökkenti a sorospohár élettartamát
        romlik();
        System.out.println("Aktív tárgy idejéből mennyi van hátra? (n)");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        // Ha a sorospohár élettartama lejárt, akkor eltávolítja az eszköztárból
        if (n == 0) {
            System.out.println("Sorospohar törlődik az eszköztárból");
        } else if (!(n > 0)) {
            System.out.println("Nem értelmezett válasz");
        }
    }
}