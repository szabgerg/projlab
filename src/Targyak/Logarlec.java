package Targyak;

import Controller.Controller;

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
    public void aktival() {
        System.out.println("Logarlec aktiválva");
        new Controller().endGame(); //A játék véget ér
    }
}
