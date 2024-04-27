package logarlec;

// A Romlandok osztály, amely implementálja az ITargy interfészt
public class Romlandok implements ITargy{
    // Az élettartam változó, amely tárolja az objektum élettartamát
    public int elettartam;

    // Konstruktor, amely létrehoz egy új Romlandok objektumot
    public Romlandok() {
        System.out.print("Romlandok_letrehozva\n");
    }

    // A romlik metódus, amely csökkenti az objektum élettartamát
    public void romlik() {
        elettartam--;
        System.out.println(",_elettartama_csokken_1-gyel");
    }

    // Az aktival metódus, amit majd a leszármazottak valósítanak meg
    public void aktival(Szoba s) {}
}