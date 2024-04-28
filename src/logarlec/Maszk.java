package logarlec;

// A Maszk osztály, amely a Romlandok osztályból származik
public class Maszk extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    private boolean hamis;

    // Konstruktor, amely létrehoz egy új Maszk objektumot
    public Maszk() {
        if (Proto.getRandVal() < 0.5) {hamis = false;}
        else {hamis = true;}
        System.out.print("Ma_letrehozva\n");
    }

    // Felülírja a Romlandok osztály szűr metódusát
    @Override
    public boolean szur(Karakter k) { 
        if (hamis) {
            System.out.print("Ma_szur\n");
            System.out.print("Ma\n");
            romlik(); //_hatralevoido_csokken
            //ha a romlik hatására a tárgy hatralevo ideje 0, akkor a tárgy eltávolításra kerül az eszközkészletből
            if (getHatralevoIdo() == 0) {
                k.getEszkozkeszlet().RemoveTargy(this);
            }
            return true;
        }
        else {
            System.out.println("Ma_hamis\n");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
        }
    }
}