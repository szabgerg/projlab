package logarlec;

// A Tvsz osztály, amely a Romlandok osztályból származik
public class Tvsz extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    private boolean hamis;

    // Konstruktor, amely létrehoz egy új Tvsz objektumot
    public Tvsz() {
        if (Proto.getRandVal() < 5) {hamis = false;}
        else {hamis = true;}
        System.out.print("Tv_letrehozva\n");
    }
    
    @Override
    // Felülírja a Romlandok osztály aktival metódusát
    public boolean hasznal(Karakter k) {
        if (hamis) {
            System.out.print("Tv_hasznalva\n");
            romlik();
            if (getHatralevoIdo() == 0) {
                k.getEszkozkeszlet().RemoveTargy(this);
            }
            return true;
        }
        else {
            System.out.println("Tv_hamis\n");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
        }
    }
}