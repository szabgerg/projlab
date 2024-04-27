package logarlec;

public interface ITargy {
    /*
     * Az adott tárgy használatával a játékos meghívja ezt a függvényt.
     */
    abstract public void aktival(Karakter k);
    /*
     * Az adott tárgy használata védekezésre.
     */
    abstract public boolean hasznal(Karakter k);
    /*
     * Az adott tárgy használata szűresre.
     */
    abstract public boolean szur(Karakter k);
}
