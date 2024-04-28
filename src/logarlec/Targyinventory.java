package logarlec;

import java.util.List;

/*
 * Targyinventory osztály
 */
public class Targyinventory {
    /*
     * Targyinventory konstruktor
     */
    private List<ITargy> targyak;

    public Targyinventory() {
        System.out.println("Targyinventory_letrehozva");
    }

    /*
     * Targyinventory konstruktor
     * @param targy - a tárgy, amelyet az inventory-hoz hozzáadunk
     */
    public Targyinventory(ITargy targy) {
        targyak.add(targy);
        System.out.println("Targyinventory_letrehozva_targgyal");
    }
    
    /*
     * Tárgy hozzáadása az inventory-hoz
     * @param targy - a hozzáadandó tárgy
     */
    public void AddTargy(ITargy targy) {
        targyak.add(targy);
        System.out.println("_targy_hozzaadasa_az_inventoryhoz");
    }

    /*
     * Tárgy eltávolítása az inventory-ból
     * @param targy - a törölni kívánt tárgy
     */
    public void RemoveTargy(ITargy targy) {
        targyak.remove(targy);
        System.out.println("_targy_eltavolitasa_az_inventorybol");
    }

    /*
     * Tárgyak visszaadása
     * @return targyak - a tárgyak listája
     */
    public List<ITargy> getTargyak() {
        return targyak;
    }
}
