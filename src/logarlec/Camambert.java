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
    public void aktival(Karakter k) {
        System.out.println("Camambert aktiválva");
        s.getAktiv().AddTargy(this);
    }

    //TODO: szól a játékosnak, hogy a camambert aktiválódott
    //TODO: akcio() függvény
}
