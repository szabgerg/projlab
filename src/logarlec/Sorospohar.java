package logarlec;

// A Sorospohar osztály, amely a Romlandok osztályból származik
public class Sorospohar extends Romlandok{
    // Konstruktor, amely létrehoz egy új Sorospohar objektumot
    public Sorospohar() {
        System.out.print("Sorospohár létrehozva\n");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Szoba s) {
        romlik();
        System.out.print("Sorospohár használva");
        //TODO: megvalósítani: eldob egy tárgyat
    }
}