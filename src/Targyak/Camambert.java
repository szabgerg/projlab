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
    public void aktival() {
        System.out.println("Camambert aktiválva");
        new Szoba().setAktiv(new Targyinventory()); //A szobában az aktív tárgyak inventory-jába kerül 
    }
}
