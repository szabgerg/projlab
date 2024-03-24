package Targyak;
import Targyak.Romlandok.Romlandok;

import java.util.Scanner;

public class Targyinventory {
    /*
     * Targyinventory konstruktor
     */
    public Targyinventory() {
        System.out.println("Targyinventory létrehozva");
    }

    public Targyinventory(ITargy targy) {
        System.out.println("Targyinventory létrehozva tárggyal");
    }
    
    /*
     * Tárgy hozzáadása az inventory-hoz
     * @param targy - a hozzáadandó tárgy
     */
    public void AddTargy(ITargy targy) {
        /*add to inventory */
        System.out.println("Tárgy hozzáadása az inventory-hoz");
    }
    /*
     * Tárgy eltávolítása az inventory-ból
     * @param targy - a törölni kívánt tárgy
     * @return - sikerült-e a törlés
     */
    public boolean RemoveTargy(ITargy targy) {
        System.out.println("Tárgy eltávolítása az inventory-ból");
        System.out.println("Hány tárgy van már a hallgató inventory-jában? (n)");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        if(n > 0){ //legalább 1 tárgy kell legyen az inventory-ban
            System.out.println("A tárgy eltávolítása sikeres");
            return true;
        }
        else if (n == 0){ //ha nincs tárgy az inventory-ban
            System.out.println("A tárgy eltávolítása sikertelen, mert a hallgató inventory-ja üres");
            return false;
        }
        else {
            System.out.println("Nem értelmezett válasz");
            return false;
        }
    }

}
