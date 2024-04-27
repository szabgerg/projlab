package logarlec;

// A Tvsz osztály, amely a Romlandok osztályból származik
public class Tvsz extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    public boolean hamis;

    // Konstruktor, amely létrehoz egy új Tvsz objektumot
    public Tvsz() {
<<<<<<< HEAD
        System.out.print("TVSZ_letrehozva\n");
=======
        if (Proto.getRandVal() < 5) {hamis = false;}
        else {hamis = true;}
        System.out.print("TVSZ létrehozva\n");
>>>>>>> 9b670c0d5241fc367c90b4fb4fa6bb85efbeedca
    }
    
    @Override
    // Felülírja a Romlandok osztály aktival metódusát
    public boolean hasznal(Karakter k) {
        if (hamis) {
<<<<<<< HEAD
            romlik();
            System.out.print("Tvsz_hasznalva");
        }
        else {
            System.out.println("A_TVSZ_hamis");
=======
            System.out.print("Tvsz használva");
            romlik();
            return true;
        }
        else {
            System.out.println("A TVSZ hamis");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
>>>>>>> 9b670c0d5241fc367c90b4fb4fa6bb85efbeedca
        }
    }
}