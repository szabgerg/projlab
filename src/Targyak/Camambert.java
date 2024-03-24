package Targyak;
import Szoba.Szoba;

//megvalósítja az ITargy interfészt
public class Camambert implements ITargy{

    /*
     * Camambert konstruktor
     */
    public Camambert() {
        System.out.println("Camambert létrehozva");
    }

    /*
    *A Camambert tárgy aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    public void aktival(Szoba s) {
        System.out.println("Camambert aktiválva");
        s.getAktiv().AddTargy(this);
    }
}
