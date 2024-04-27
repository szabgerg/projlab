package logarlec;

// A Maszk osztály, amely a Romlandok osztályból származik
public class Maszk extends Romlandok{
    // Azt tárolja, hogy a tárgy hamis-e
    public boolean hamis;

    // Konstruktor, amely létrehoz egy új Maszk objektumot
    public Maszk() {
<<<<<<< HEAD
        System.out.print("Maszk_letrehozva");
=======
        if (Proto.getRandVal() < 5) {hamis = false;}
        else {hamis = true;}
        System.out.print("Maszk létrehozva");
>>>>>>> 9b670c0d5241fc367c90b4fb4fa6bb85efbeedca
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public boolean szur(Karakter k) { 
        if (hamis) {
<<<<<<< HEAD
            romlik();
            System.out.print("Maszk_hasznalva");
        }
        else {
            System.out.println("A_Maszk_hamis");
=======
            System.out.print("Maszk használva");
            romlik();
            return true;
        }
        else {
            System.out.println("A Maszk hamis");
            k.getEszkozkeszlet().RemoveTargy(this);
            return false;
>>>>>>> 9b670c0d5241fc367c90b4fb4fa6bb85efbeedca
        }
    }
}