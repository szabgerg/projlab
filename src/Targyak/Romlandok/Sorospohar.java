package Targyak.Romlandok;

import Szoba.Szoba;

// A Sorospohar osztály, amely a Romlandok osztályból származik
public class Sorospohar extends Romlandok{
    // Konstruktor, amely létrehoz egy új Sorospohar objektumot
    public Sorospohar() {
        System.out.print("Sorospohár létrehozva\n");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Szoba s) {
        System.out.print("Sorospohár használva");
    }
}