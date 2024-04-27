package logarlec;

// A Maszk osztály, amely a Romlandok osztályból származik
public class Maszk extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    public boolean hamis = true;

    // Konstruktor, amely létrehoz egy új Maszk objektumot
    public Maszk() {
        System.out.print("Maszk_letrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Szoba s) {
        if (hamis) {
            romlik();
            System.out.print("Maszk_hasznalva");
        }
        else {
            System.out.println("A_Maszk_hamis");
        }
    }
}