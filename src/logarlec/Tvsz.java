package logarlec;

// A Tvsz osztály, amely a Romlandok osztályból származik
public class Tvsz extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    public boolean hamis;

    // Konstruktor, amely létrehoz egy új Tvsz objektumot
    public Tvsz() {
        if (Proto.getRandVal() < 5) {hamis = false;}
        else {hamis = true;}
        System.out.print("TVSZ létrehozva\n");
    }
    
    @Override
    // Felülírja a Romlandok osztály aktival metódusát
    public boolean hasznal(Karakter k) {
        if (hamis) {
            System.out.print("Tvsz használva");
            romlik();
            return true;
        }
        else {
            System.out.println("A TVSZ hamis");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
        }
    }
}