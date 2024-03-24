package Targyak.Romlandok;

import Szoba.Szoba;
import Targyak.Targyinventory;

// A Rongy osztály, amely a Romlandok osztályból származik
public class Rongy extends Romlandok{
    // Konstruktor, amely létrehoz egy új Rongy objektumot
    public Rongy() {
        System.out.print("Rongy létrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Szoba s) {
        System.out.print("Rongy használva");
        // Berakrja a rongyot a szobáb aktív tárgyai közé
        new Szoba().setAktiv(s.getAktiv());
    }
}