package logarlec;

//megvalósítja az ITargy interfészt
public class Camambert implements ITargy{

    /*
     * Camambert konstruktor
     */
    public Camambert() {
        System.out.println("Camambert létrehozva\n");
    }

    /*
    *A Camambert tárgy aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    @Override
    public void aktival(Karakter k) {
        k.getSzoba().getAktiv().AddTargy(this);
        System.out.println("Camambert aktiválva");

        // A karakterek elejtenek mindent, az oktatók benák lesznek
        for (Karakter karakter : k.getSzoba().getBentlevok()) {
            karakter.setBena(true);
            //TODO: elejt mindnet, de mivan ha tud védekezni?
        }
    }

    // Ha egy karakter egy szobába lép, ahol camambert van, akkor a karakter elejt mindent és az oktató bena lesz
    public void akcio(Karakter k){
        k.setBena(true);
        //TODO: elejt mindnet, de mivan ha tud védekezni?
    }

    @Override
    public boolean hasznal(Karakter k) { return false;}
    @Override
    public boolean szur(Karakter k) { return false;}
}
