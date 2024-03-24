package Targyak.Romlandok;

import java.util.Scanner;

// A Tvsz osztály, amely a Romlandok osztályból származik
public class Tvsz extends Romlandok{
    // Konstruktor, amely létrehoz egy új Tvsz objektumot
    public Tvsz() {
        System.out.print("TVSZ létrehozva");
    }
    
    @Override
    // Felülírja a Romlandok osztály aktival metódusát
    public void aktival() {
        System.out.print("Tvsz használva");
        // Csökkenti a Tvsz élettartamát
        romlik();
        System.out.println("Aktív tárgy idejéből mennyi van hátra? (n)");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        // Ha a Tvsz élettartama lejárt, akkor eltávolítja az eszköztárból
        if (n == 0) {
            System.out.println("TVSZ törlődik az eszköztárból");
        } else if (!(n > 0)) {
            System.out.println("Nem értelmezett válasz");
        }
    }
}