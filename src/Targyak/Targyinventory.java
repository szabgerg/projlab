package Targyak;

import java.util.List;

/*
 * Targyinventory osztály
 */
public class Targyinventory {
    /*
     * Targyinventory konstruktor
     */

    public List<ITargy> targyak;
    public Targyinventory() {
        System.out.println("Targyinventory létrehozva\n");
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
