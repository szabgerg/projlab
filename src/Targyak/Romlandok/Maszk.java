package Targyak.Romlandok;

import Szoba.Szoba;

// A Maszk osztály, amely a Romlandok osztályból származik
public class Maszk extends Romlandok{
    // Konstruktor, amely létrehoz egy új Maszk objektumot
    public Maszk() {
        System.out.print("Maszk létrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Szoba s) {
        System.out.print("Maszk használva");
    }
}