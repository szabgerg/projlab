package logarlec;

// A Tvsz osztály, amely a Romlandok osztályból származik
public class Tvsz extends Romlandok{

    // Azt tárolja, hogy a tárgy hamis-e
    private boolean hamis;

    // Konstruktor, amely létrehoz egy új Tvsz objektumot
    public Tvsz() {
        super(3);
        if (Proto.getRandVal() < 0.5) {hamis = false;}
        else {hamis = true;}
        System.out.println("Tv_letrehozva");
    }
    
    @Override
    // Felülírja a Romlandok osztály aktival metódusát
    public boolean hasznal(Karakter k) {
        if (hamis) {
            System.out.println("Tv_hasznalva");
            romlik();//_hatralevoido_csokken
            if (getHatralevoIdo() == 0) {
                k.getEszkozkeszlet().RemoveTargy(this);
            }
            return true;
        }
        else {
            System.out.println("Tv_hamis");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
        }
    }
}