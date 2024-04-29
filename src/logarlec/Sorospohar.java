package logarlec;

// A Sorospohar osztály, amely a Romlandok osztályból származik
public class Sorospohar extends Romlandok{
    // Konstruktor, amely létrehoz egy új Sorospohar objektumot
    public Sorospohar() {
        super(3);
        System.out.println("So_letrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public boolean hasznal(Karakter k) {
        System.out.println("So_hasznalva");
        // A játékos eszközkészletéből egy véletlenszerűen kiválasztott tárgy eltávolítása
        double rand = Proto.getRandVal();
		if(rand >= 0.5 || !k.getEszkozkeszlet().getTargyak().isEmpty()){
		    int randint = (int) (rand * 10);
		    if(randint > k.getEszkozkeszlet().getTargyak().size()){
			    randint = k.getEszkozkeszlet().getTargyak().size()-1;
		    }
            k.getEszkozkeszlet().getTargyak().remove(randint);
		    System.out.println("Item_leteve");
        }
        else {
            System.out.println("Nincs_targy_a_jatekosnal_nem_dob_el_targyat");
        }
        // A tárgy használata
        romlik();//_hatralevoido_csokken
        if (getHatralevoIdo() == 0) {
            k.getEszkozkeszlet().RemoveTargy(this);
        }
        return true;
    }
}


