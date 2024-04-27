package logarlec;

// A Tvsz osztály, amely a Romlandok osztályból származik
public class Tvsz extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    public boolean hamis = true;

    // Konstruktor, amely létrehoz egy új Tvsz objektumot
    public Tvsz() {
        System.out.print("TVSZ_letrehozva\n");
    }
    
    @Override
    // Felülírja a Romlandok osztály aktival metódusát
    public void aktival(Szoba s) {
        if (hamis) {
            romlik();
            System.out.print("Tvsz_hasznalva");
        }
        else {
            System.out.println("A_TVSZ_hamis");
        }
    }
}