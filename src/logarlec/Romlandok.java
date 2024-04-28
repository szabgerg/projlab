package logarlec;

// A Romlandok osztály, amely implementálja az ITargy interfészt
public class Romlandok implements ITargy{
    // Az élettartam változó, amely tárolja az objektum élettartamát
    private int hatralevoido;

    // Konstruktor, amely létrehoz egy új Romlandok objektumot
    public Romlandok() {
        System.out.print("Romlandok_letrehozva");
    }

    // A hatralevoido getter metódus
    public int getHatralevoIdo() {
        return hatralevoido;
    }

    // A romlik metódus, amely csökkenti az objektum élettartamát
    public void romlik() {
        hatralevoido--;
        System.out.println("_hatralevoido_csokken");
    }

    // Az aktival metódus, amit majd a leszármazottak valósítanak meg
    @Override
    public void aktival(Karakter k) { /*override */ }
    @Override
    public boolean hasznal(Karakter k) {return false;}
    @Override
    public boolean szur(Karakter k) {return false;}
    @Override
    public void akcio(Karakter k) {/*override */}
}