package logarlec;

// A Rongy osztály, amely a Romlandok osztályból származik
public class Rongy extends Romlandok{
    // Konstruktor, amely létrehoz egy új Rongy objektumot
    public Rongy() {
        super(3);
        System.out.println("Ro_letrehozva");
    }

    // Felülírja a Romlandok osztály aktival metódusát
    @Override
    public void aktival(Karakter k){
        System.out.println("Ro_aktivalva");
        //hozzadja a rongyot az aktiv inventoryhoz
        k.getSzoba().getAktiv().AddTargy(this);
        k.getEszkozkeszlet().RemoveTargy(this);

        //beallitja a karaktert benanak
        for (Karakter karakter : k.getSzoba().getBentlevok()) {
            karakter.setBena(true);
        }
    }

    // Ha egy oktató egy szobába lép, ahol rogy van, akkor az oktató bena lesz
    public void akcio(Karakter k){
        k.setBena(true);
        System.out.println("Ro_akcio");
    }
}