package logarlec;

//megvalósítja az ITargy interfészt
public class Logarlec implements ITargy{
    // Azt tárolja, hogy a tárgy hamis-e
    public boolean hamis;

    /*
     * Logarlec konstruktor
     */
    public Logarlec() {
<<<<<<< HEAD
        System.out.println("Logarlec_letrehozva\n");
=======
        if (Proto.getRandVal() < 5) {hamis = false;}
        else {hamis = true;}
        System.out.println("Logarlec létrehozva\n");
>>>>>>> 9b670c0d5241fc367c90b4fb4fa6bb85efbeedca
    }

    /*
    *A Logarlec tárgy aktiválása, a játék véget ér
    */
    public void aktival(Karakter k) {
        if (hamis) {
            System.out.println("Logarlec_aktivalva");
            new Controller().endGame(); //A játék véget ér
        }
        else {
<<<<<<< HEAD
            System.out.println("A_Logarlec_hamis");
=======
            System.out.println("A Logarlec hamis");
            k.getEszkozkeszlet().RemoveTargy(this);
>>>>>>> 9b670c0d5241fc367c90b4fb4fa6bb85efbeedca
        }
    }

    /*
     * A Logarlec tárgy használata
     * @param k - a karakter, aki használja
     * @return - false, mivel a Logarlec nem használható
     */
    public boolean hasznal(Karakter k) { return false;}

    /*
     * A Logarlec tárgy szűrése
     * @param k - a karakter, aki használja
     * @return - false, mivel a Logarlec nem szűr
     */
    public boolean szur(Karakter k) { return false;}
}
