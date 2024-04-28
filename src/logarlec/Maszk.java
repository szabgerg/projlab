package logarlec;

// A Maszk osztály, amely a Romlandok osztályból származik
public class Maszk extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    private boolean hamis;

    // Konstruktor, amely létrehoz egy új Maszk objektumot
    public Maszk() {
        if (Proto.getRandVal() < 0.5) {hamis = false;}
        else {hamis = true;}
        System.out.print("Maszk_letrehozva\n");
    }

    // Felülírja a Romlandok osztály szűr metódusát
    @Override
    public boolean szur(Karakter k) { 
        if (hamis) {
            romlik();
            //ha a romlik hatására a tárgy hatralevo ideje 0, akkor a tárgy eltávolításra kerül az eszközkészletből
            if (getHatralevoIdo() == 0) {
                k.getEszkozkeszlet().RemoveTargy(this);
            }
            System.out.print("Maszk_szur\n");
            return true;
        }
        else {
            System.out.println("Maszk_hamis\n");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
        }
    }
}