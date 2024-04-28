package logarlec;

public interface ITargy {
    /*
     * Az adott tárgy használatával a játékos meghívja ezt a függvényt.
     */
    public abstract void aktival(Karakter k);
    /*
     * Az adott tárgy használata védekezésre, más esetben false.
     */
    public abstract boolean hasznal(Karakter k);
    /*
     * Az adott tárgy használata szűresre, csak a maszk használja, minden más esetben false.
     */
    public abstract boolean szur(Karakter k);

    /*
     * Az adott tárgy akciója, ...
     */
    public abstract void akcio(Karakter k);

    public abstract void gaztalanit(Szoba szoba);
}
