package Targyak.Romlandok;

import Szoba.Szoba;

// A Rongy osztály, amely a Romlandok osztályból származik
public class Rongy extends Romlandok{
    // Konstruktor, amely létrehoz egy új Rongy objektumot
    public Rongy() {
        System.out.print("Rongy létrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Szoba s){
        System.out.println("Rongy aktivalva");
        //hozzadja a rongyot az aktiv inventoryhoz
        s.getAktiv().AddTargy(this);
    }
}