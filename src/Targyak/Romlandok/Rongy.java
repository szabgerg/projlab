package Targyak.Romlandok;

import java.util.Scanner;

// A Rongy osztály, amely a Romlandok osztályból származik
public class Rongy extends Romlandok{
    // Konstruktor, amely létrehoz egy új Rongy objektumot
    public Rongy() {
        System.out.print("Rongy létrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival() {
        System.out.print("Rongy használva");
        // Berakrja a rongyot a szobáb aktív tárgyai közé
        Szoba.setAktiv();
    }
}