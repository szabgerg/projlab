package logarlec;

// A Rongy osztály, amely a Romlandok osztályból származik
public class Rongy extends Romlandok{
    // Konstruktor, amely létrehoz egy új Rongy objektumot
    public Rongy() {
        System.out.print("Rongy létrehozva\n");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Karakter k){
        System.out.println("Rongy aktivalva");
        //hozzadja a rongyot az aktiv inventoryhoz
        s.getAktiv().AddTargy(this);

        //TODO: szól a játékosnak, hogy a rongy aktiválódott
        //TODO: akcio() függvény
    }
}