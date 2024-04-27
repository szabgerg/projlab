package logarlec;

// A Romlandok osztály, amely implementálja az ITargy interfészt
public class Romlandok implements ITargy{
    // Az élettartam változó, amely tárolja az objektum élettartamát
    private int hatralevoido;

    // Konstruktor, amely létrehoz egy új Romlandok objektumot
    public Romlandok() {
        System.out.print("Romlandok_letrehozva\n");
    }

    // A hatralevoido getter metódus
    public int getHatralevoIdo() {
        return hatralevoido;
    }

    // A romlik metódus, amely csökkenti az objektum élettartamát
    public void romlik() {
        hatralevoido--;
        System.out.println(", élettartama csökken 1-gyel");
    }

    // Az aktival metódus, amit majd a leszármazottak valósítanak meg
    public void aktival(Karakter k) { /*override */ }
    public boolean hasznal(Karakter k) {return false;}
    public boolean szur(Karakter k) {return false;}
}