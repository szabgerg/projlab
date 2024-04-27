package logarlec;

// A Romlandok osztály, amely implementálja az ITargy interfészt
public class Romlandok implements ITargy{
    // Az élettartam változó, amely tárolja az objektum élettartamát
    private int hatralevoido;

    // Konstruktor, amely létrehoz egy új Romlandok objektumot
    public Romlandok() {
        System.out.print("Romlandok_letrehozva\n");
    }

    // A romlik metódus, amely csökkenti az objektum élettartamát
    public void romlik() {
<<<<<<< HEAD
        elettartam--;
        System.out.println(",_elettartama_csokken_1-gyel");
=======
        hatralevoido--;
        System.out.println(", élettartama csökken 1-gyel");
>>>>>>> 9b670c0d5241fc367c90b4fb4fa6bb85efbeedca
    }

    // Az aktival metódus, amit majd a leszármazottak valósítanak meg
    public void aktival(Karakter k) {}
    public boolean hasznal(Karakter k) {return false;}
    public boolean szur(Karakter k) {return false;}
}