package logarlec;

// A Romlandok osztály, amely implementálja az ITargy interfészt
public class Romlandok implements ITargy{
    // Az élettartam változó, amely tárolja az objektum élettartamát
    private int hatralevoido;

    // Konstruktor, amely létrehoz egy új Romlandok objektumot
    public Romlandok() {
        System.out.print("Romlandok létrehozva\n");
    }

    // A romlik metódus, amely csökkenti az objektum élettartamát
    public void romlik() {
        hatralevoido--;
        System.out.println(", élettartama csökken 1-gyel");
    }

    // Az aktival metódus, amit majd a leszármazottak valósítanak meg
    public void aktival(Karakter k) {}
    public boolean hasznal(Karakter k) {return false;}
    public boolean szur(Karakter k) {return false;}
}