package logarlec;

// A Maszk osztály, amely a Romlandok osztályból származik
public class Maszk extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    private boolean hamis;

    // Konstruktor, amely létrehoz egy új Maszk objektumot
    public Maszk() {
        if (Proto.getRandVal() < 0.5) {hamis = false;}
        else {hamis = true;}
        System.out.print("Maszk létrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public boolean szur(Karakter k) { 
        if (hamis) {
            System.out.print("Maszk használva");
            romlik();
            //ha a romlik hatására a tárgy hatralevo ideje 0, akkor a tárgy eltávolításra kerül az eszközkészletből
            if (getHatralevoIdo() == 0) {
                k.getEszkozkeszlet().RemoveTargy(this);
            }
            return true;
        }
        else {
            System.out.println("A Maszk hamis");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
        }
    }
}