package Targyak;

import Controller.Controller;
import Szoba.Szoba;

//megvalósítja az ITargy interfészt
public class Logarlec implements ITargy{

    /*
     * Logarlec konstruktor
     */
    public Logarlec() {
        System.out.println("Logarlec létrehozva");
    }

    /*
    *A Logarlec tárgy aktiválása, a játék véget ér
    */
    public void aktival(Szoba s) {
        System.out.println("Logarlec aktiválva");
        s.getAktiv().AddTargy(this);
        new Controller().endGame(); //A játék véget ér
    }
}
