package logarlec;

//megvalósítja az ITargy interfészt
public class Camambert implements ITargy{

    /*
     * Camambert konstruktor
     */
    public Camambert() {
        System.out.println("Ca_letrehozva");
    }

    /*
    *A Camambert tárgy aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    @Override
    public void aktival(Karakter k) {
        System.out.println("Ca_aktivalva");
        k.getSzoba().getAktiv().AddTargy(this);
        k.getEszkozkeszlet().RemoveTargy(this);
        // A karakterek elejtenek mindent, az oktatók benák lesznek
        for (Karakter karakter : k.getSzoba().getBentlevok()) {
            karakter.setBena(true);
            // Ha a karakter nem tud védekezni, akkor elejti az összes tárgyát
            boolean vanvedekezes = false;
            for (ITargy targy : karakter.getEszkozkeszlet().getTargyak()) {
                if (targy.szur(karakter)) {
                    vanvedekezes = true;
                }
            }
            if (!vanvedekezes) {
                karakter.mindentelejt();
            }

        }
    }
    //a legfrissítővel eltávolítják a camambertet, a szoba nem lesz gázos
    public boolean gaztalanit(Szoba szoba){
        szoba.getAktiv().RemoveTargy(this);
        return true;
    }

    // Ha egy karakter egy szobába lép, ahol camambert van, akkor a karakter elejt mindent és az oktató bena lesz
    public void akcio(Karakter k){
        System.out.println("Ca_akcio");
        k.setBena(true);
        // Ha a karakter nem tud védekezni, akkor elejti az összes tárgyát
        boolean vanvedekezes = false;
        for (ITargy targy : k.getEszkozkeszlet().getTargyak()) {
            if (targy.szur(k)) {
                vanvedekezes = true;
            }
        }
        if (!vanvedekezes) {
            k.mindentelejt();
        }
    }

    /*
     * A Camambert tárgy használata
     * @param k - a karakter, aki használja
     * @return - false, mivel a Camambert nem használható
     */
    @Override
    public boolean hasznal(Karakter k) { return false;}

    /*
     * A Camambert tárgy szűrése
     * @param k - a karakter, aki használja
     * @return - false, mivel a Camambert nem szűr
     */
    @Override
    public boolean szur(Karakter k) { return false;}

    /*
     * A Camambert tárgy romlik
     * @return -1, mivel a Camambert nem romlik
     */
    @Override
    public int romlik() {return -1;}
}
