package logarlec;

//megvalósítja az ITargy interfészt
public class Tranzisztor implements ITargy{

    private Tranzisztor par;
    private Szoba szoba;

    /*
     * Tranzisztor konstruktor
     */
    public Tranzisztor() {
        System.out.println("Tranzisztor létrehozva\n");
    }

    /*
     * Tranzisztor paraméteres konstruktor
     */
    public Tranzisztor(Tranzisztor t, Szoba s) {
        System.out.println("Tranzisztor létrehozva\n");
        par = t;
        szoba = s;
    }

    /*
     * Visszaadja a tranzisztor párját
     * @return Tranzisztor - a tranzisztor párja
     */
    public Tranzisztor getPar(){
        System.out.println("Tranzisztor párja lekérdezve");
        return par;
    }

    /*
     * Beállítja a tranzisztor párját
     * @param t - a beállítandó tranzisztor
     */
    public void setPar(Tranzisztor t){
        par = t;
        System.out.println("Tranzisztor pár beállítva");
    }

    /*
     * Visszaadja hogy a tranzisztor melyik szobában van
     * @return int - a tranzisztor szobája
     */
    public Szoba getSzoba(){
        System.out.println("Szoba lekérdezve");
        return szoba;
    }

    /*
     * Beállítja a tranzisztor szobáját
     * @param s - a beállítandó szoba
     */
    public void setSzoba(Szoba s){
        szoba = s;
        System.out.println("Szoba beállítva");
    }

    /*
    * Tranzisztor aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    @Override
    public void aktival(Karakter k) {
        k.jelenlegi.getAktiv().AddTargy(this);
        System.out.println("Tranzisztor aktiválva");
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
        System.out.println("A tranzisztor párosítható-e?");
        return par == null; //ha a tranzisztor párja null, akkor párosítható
    }

    /*
     * Tranzisztor kikapcsolása, aktiv jelzés beállítása
     */
    public void kikapcsol(){
        szoba.getAktiv().RemoveTargy(this);
        System.out.println("Tranzisztor kikapcsolva");
    }


    /**
     * Tranzisztor ezeket nem tudja használni
     */
    @Override
    public boolean hasznal(Karakter k){ return false;}
    @Override
    public boolean szur(Karakter k){ return false;}
}
