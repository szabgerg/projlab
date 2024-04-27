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
    }

    //TODO: nem tudom melyik kell ide, melyik nem
    @Override
    public boolean hasznal(Karakter k) { return false;}
    @Override
    public boolean szur(Karakter k) { return false;}

    //TODO: szól a játékosnak, hogy a camambert aktiválódott
    //TODO: akcio() függvény
}
