package Targyak.Romlandok;

import Szoba.Szoba;

// A Tvsz osztály, amely a Romlandok osztályból származik
public class Tvsz extends Romlandok{
    // Konstruktor, amely létrehoz egy új Tvsz objektumot
    public Tvsz() {
        System.out.print("TVSZ létrehozva\n");
    }
    
    @Override
    // Felülírja a Romlandok osztály aktival metódusát
    public void aktival(Szoba s) {
        System.out.print("Tvsz használva");

    }
}