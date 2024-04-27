package logarlec;

//megvalósítja az ITargy interfészt
public class Logarlec implements ITargy{
    // Azt tárolja, hogy a tárgy hamis-e
    public boolean hamis = true;

    /*
     * Logarlec konstruktor
     */
    public Logarlec() {
        System.out.println("Logarlec létrehozva\n");
    }

    /*
    *A Logarlec tárgy aktiválása, a játék véget ér
    */
    public void aktival(Szoba s) {
        if (hamis) {
            System.out.println("Logarlec aktiválva");
            new Controller().endGame(); //A játék véget ér
        }
        else {
            System.out.println("A Logarlec hamis");
        }
    }
}
