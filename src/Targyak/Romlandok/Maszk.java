package Targyak.Romlandok;
import java.util.Scanner;

// A Maszk osztály, amely a Romlandok osztályból származik
public class Maszk extends Romlandok{
    // Konstruktor, amely létrehoz egy új Maszk objektumot
    public Maszk() {
        System.out.print("Maszk létrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival() {
        System.out.print("Maszk használva");
        // Csökkenti a maszk élettartamát
        romlik();
        System.out.println("Aktív tárgy idejéből mennyi van hátra? (n)");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        // Ha a maszk élettartama lejárt, akkor eltávolítja az eszköztárból
        if (n == 0) {
            System.out.println("Maszk törlődik az eszköztárból");
        } else if (!(n > 0)) {
            System.out.println("Nem értelmezett válasz");
        }
    }
}