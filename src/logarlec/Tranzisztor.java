package logarlec;

//megvalósítja az ITargy interfészt
public class Tranzisztor implements ITargy{ //expected 9,10,11,12

    private Tranzisztor par;
    private Szoba szoba;

    /*
     * Tranzisztor konstruktor
     */
    public Tranzisztor() {
        System.out.println("Tr_letrehozva\n");
    }

    /*
     * Tranzisztor paraméteres konstruktor
     */
    public Tranzisztor(Tranzisztor t, Szoba s) {
        System.out.println("Tr_letrehozva\n");
        par = t;
        szoba = s;
    }

    /*
     * Visszaadja a tranzisztor párját
     * @return Tranzisztor - a tranzisztor párja
     */
    public Tranzisztor getPar(){
        return par;
    }

    /*
     * Beállítja a tranzisztor párját
     * @param t - a beállítandó tranzisztor
     */
    public void setPar(Tranzisztor t){
        par = t;
        System.out.println("Tr_par_beallitva\n");
    }

    /*
     * Visszaadja hogy a tranzisztor melyik szobában van
     * @return int - a tranzisztor szobája
     */
    public Szoba getSzoba(){
        return szoba;
    }

    /*
     * Beállítja a tranzisztor szobáját
     * @param s - a beállítandó szoba
     */
    public void setSzoba(Szoba s){
        szoba = s;
        System.out.println("Szoba_beallitva\n");
    }

    /*
    * Tranzisztor aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    @Override
    public void aktival(Karakter k) {
        k.jelenlegi.getAktiv().AddTargy(this);
        System.out.println("Tr_aktivalva\n");
        //ha a tranzisztor parja aktivalva van már, akkor aktiválásnál meghivodik a hallgato teleportálása
        if(par != null && par.getSzoba().getAktiv().getTargyak().contains(par)){
            //a hallgató a tranzisztor párjához teleportálódik
            ((Hallgato)k).teleport(par.getSzoba());
            this.kikapcsol();
            par.kikapcsol();
            par.setPar(null);
            this.setPar(null);
        }
    }

    /*
     * Tranzisztor párosíthatóságának ellenőrzése
     * @return boolean - párosítható-e a tranzisztor
     */
    public boolean canPair(){
        if(par != null){
            System.out.println("Tr_nem_parosithato\n");
            return false;
        }
        return true;
    }

    /*
     * Tranzisztor kikapcsolása, aktiv jelzés beállítása
     */
    public void kikapcsol(){
        szoba.getAktiv().RemoveTargy(this);
        System.out.println("Tr_kikapcsolva\n");
    }


    /**
     * Tranzisztor ezeket nem tudja használni
     */

    /*
     * Tranzisztor használata
     * @param k - a karakter, aki használja
     * @return - false, mivel a tranzisztor nem használható
     */
    @Override
    public boolean hasznal(Karakter k){ return false;}

    /*
     * Tranzisztor szűrése
     * @param k - a karakter, aki használja
     * @return - false, mivel a tranzisztor nem szűr
     */
    @Override
    public boolean szur(Karakter k){ return false;}
    @Override
    public void akcio(Karakter k) {}
}
