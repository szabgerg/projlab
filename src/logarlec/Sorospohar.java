package logarlec;

// A Sorospohar osztály, amely a Romlandok osztályból származik
public class Sorospohar extends Romlandok{
    // Konstruktor, amely létrehoz egy új Sorospohar objektumot
    public Sorospohar() {
        System.out.print("Sorospohár létrehozva\n");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public boolean hasznal(Karakter k) {
        // A játékos eszközkészletéből egy véletlenszerűen kiválasztott tárgy eltávolítása
        int targyakSzama = k.getEszkozkeszlet().getTargyak().size();
        int randomIndex = (int) (Proto.getRandVal() * (targyakSzama + 1));
        k.getEszkozkeszlet().getTargyak().remove(randomIndex);
        // A tárgy használata
        romlik();
        System.out.print("Sorospohár használva");
        return true;
    }
}


