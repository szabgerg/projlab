package logarlec;

//megvalósítja az ITargy interfészt
public class Camambert implements ITargy{

    /*
     * Camambert konstruktor
     */
    public Camambert() {
        System.out.println("Ca_letrehozva\n");
    }

    /*
    *A Camambert tárgy aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    @Override
    public void aktival(Karakter k) {
        k.getSzoba().getAktiv().AddTargy(this);
        // A karakterek elejtenek mindent, az oktatók benák lesznek
        for (Karakter karakter : k.getSzoba().getBentlevok()) {
            karakter.setBena(true);
            // Ha a karakter nem tud védekezni, akkor elejti az összes tárgyát
            for (ITargy targy : karakter.getEszkozkeszlet().getTargyak()) {
                if (!targy.szur(karakter)) {
                    karakter.mindentelejt();
                }
            }
        }
        System.out.println("Ca_aktivalva\n");
    }

    // Ha egy karakter egy szobába lép, ahol camambert van, akkor a karakter elejt mindent és az oktató bena lesz
    public void akcio(Karakter k){
        k.setBena(true);
        // Ha a karakter nem tud védekezni, akkor elejti az összes tárgyát
        for (ITargy targy : k.getEszkozkeszlet().getTargyak()) {
            if (!targy.szur(k)) {
                k.mindentelejt();
            }
        }
        System.out.println("Ca_akcio\n");
    }

    @Override
    public boolean hasznal(Karakter k) { return false;}
    @Override
    public boolean szur(Karakter k) { return false;}
}
