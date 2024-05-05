package logarlec;

//megvalósítja az ITargy interfészt
public class Logarlec implements ITargy{

    // Azt tárolja, hogy a tárgy hamis-e
    private boolean hamis;

    /*
     * Logarlec konstruktor
     */
    public Logarlec() {
        if (Proto.getRandVal() < 0.5) {hamis = false;}
        else {hamis = true;}
        System.out.println("Lo_letrehozva");
    }

    /*
    *A Logarlec tárgy aktiválása, a játék véget ér
    */
    @Override
    public void aktival(Karakter k) {
        if (hamis) {
            System.out.println("Lo_aktivalva");
            new Controller().endGame(); //A játék véget ér
        }
        else {
            System.out.println("Lo_hamis");
            k.getEszkozkeszlet().RemoveTargy(this);
        }
    }

    /*
     * A Logarlec tárgy használata
     * @param k - a karakter, aki használja
     * @return - false, mivel a Logarlec nem használható
     */
    @Override
    public boolean hasznal(Karakter k) { return false;}

    /*
     * A Logarlec tárgy szűrése
     * @param k - a karakter, aki használja
     * @return - false, mivel a Logarlec nem szűr
     */
    @Override
    public boolean szur(Karakter k) { return false;}

    /*
     * A Logarlec tárgy akciója
     * @param k - a karakter, aki használja
     */
    @Override
    public void akcio(Karakter k) {}
    /*
     * A Logarlec tárgy gaztalanítása
     * @param szoba - a szoba, ahol a tárgy gaztalanításra kerül
     */
    public boolean gaztalanit(Szoba szoba) {return false;}
    /*
     * A Logarlec tárgy romlása
     * @return -1, mivel a Logarlec nem romlik
     */
    @Override
    public int romlik() {return -1;}
}
