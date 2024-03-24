package Targyak;
/*
 * Targyinventory osztály
 */
public class Targyinventory {
    /*
     * Targyinventory konstruktor
     */
    public Targyinventory() {
        System.out.println("Targyinventory létrehozva");
    }

    public Targyinventory(ITargy targy) {
        System.out.println("Targyinventory létrehozva tárggyal");
    }
    
    /*
     * Tárgy hozzáadása az inventory-hoz
     * @param targy - a hozzáadandó tárgy
     */
    public void AddTargy(ITargy targy) {
        /*add to inventory */
        System.out.println("Tárgy hozzáadása az inventory-hoz");
    }
    /*
     * Tárgy eltávolítása az inventory-ból
     * @param targy - a törölni kívánt tárgy
     * @return - sikerült-e a törlés
     */
    public void RemoveTargy(ITargy targy) {
        System.out.println("Tárgy eltávolítása az inventory-ból");
    }

}
