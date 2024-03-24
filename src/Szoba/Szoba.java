package Szoba;

import java.util.List;
import java.util.Scanner;

import Karakterek.Karakter;
import Targyak.*;
import Targyak.Romlandok.Romlandok;

/*
 * Szoba osztály, a játék teljes egészében a szobák
 * valamelyikében játszódik. A karakterek szobák között
 * mozoghatnak, hacsak nem vesztette el a lelkét egy
 * hallgató, minden karakter egyszerre mindig egy szobában
 * tartozkodik. A szobában tárgyak is lehetnek, a szobák
 * különböző tulajdonságokkal rendelkezhetnek
 */
public class Szoba {
    public Szoba(List<Karakter> karakterek, Targyinventory inventory) {
        System.out.println("Szoba letrehozasa\n");
	System.out.println("Szoba inventoryja letrehozva\n");
	System.out.println("Szobaba karakterek beteve\n");
    }
    /* A szoba befogadóképessége alapján
     * eldönti, hogy egy új karakter be tud-e lépni
     * @return: true, ha beléphet, false, ha tele a szoba
     */
    public boolean beenged() {
        int cap = getBefogadokepesseg();
        System.out.println("A szoba befogadokepessege " + cap);
        System.out.println("Hany karakter van mar a szobában?");
		Scanner scanner = new Scanner(System.in);
		int hanyan = scanner.nextInt();
        scanner.close();
        if (hanyan < cap) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /* Visszatér az adott szobában tartózkodó karakterekkel
     * @return karakterek listája
     */
    public List<Karakter> getBentlevok(){
        return null;
    }

    /* Beállítja a szobában tartózkodó karaktereket,
     * szobák közti mozgás/elhalálozás esetében
     * @param k a bent lévő karakterek listája, beállítandó érték
     */
    public void setBentlevok(List<Karakter> k){
		System.out.println("Bent levo karakterek beallitasa\n");
    }

    /* Meghívódik, ha egy karakter elhagyja a szobát,
     * beállítja a frissített bentlevok listáját
     * @param k: a kilépő karakter
     */
    public void kilep(Karakter k){
        System.out.println("Kilepes a szobabol");
        setBentlevok(null);
    }

    /* Visszatér a szobában található tárgyak listájával
     * @return Targyinventory
     */
    public Targyinventory getBentiTargyak(){
		System.out.println("A szobában található tárgyak:\n" +
                "1. Tvsz\n" +
                "2. Söröspohár\n" +
                "3. Tranzisztor\n" +
                "4. Rongy\n" +
                "5. Camambert\n" +
                "6. Maszk\n");
        return null;
    }

    /* Beállítja a szobában található tárgyakat
     * @param t a tárgyak listája, a beállítandó érték
     */
    public void setBentiTargyak(Targyinventory t){
		System.out.println("Szoba targyainak beallitasa\n");
    }

    /* Visszaadja a szoba szomszédait
     * @return a szobák listája, amelyekbe át lehet jutni
     */
    public List<Szoba> getSzomszedok(){
        return null;
    }

    /* Beállítja a szoba szomszédait, akikhez
     * van egyenes út az adott szobából
     * @param s a szomszédok listája
     */
    public void setSzomszedok(List<Szoba> s){
		System.out.println("Szoba szomszedainak beallitasa\n");
    }

    /* Visszaadja a szoba jelenleg aktív tárgyait,
     * amelyek hatása jelenleg befolyásolja a szoba működését
     * @return Targyinventory, az aktív tárgyak listája
     */
    public Targyinventory getAktiv(){
        return null;
    }

    /* Beállítja a szoba jelenleg aktív hatással bíró tárgyait,
     * meghívódik például, ha egy karakter aktivál egy új tárgyat
     * @param targy az aktív tárgyak listája
     */
    public void setAktiv(Targyinventory targy){
		System.out.println("Aktiv targyak beallitasa\n");
    }

    /* Beállítja a szoba befogadóképességét,
     * @param d a beállítandó érték
     */
    public void setBefogadokepesseg(int d){
		System.out.println("Befogadokepesseg beallitasa\n");
    }

    /* Visszatér a szoba befogadóképességével
     * @return int
     */
    public int getBefogadokepesseg(){
        return 5;
    }

    /* Beállítja egy szoba összes attribútumát,
     * felhasználás például új szoba létrehozásánál,
     * merge / split esetnél
     * @param u az új szoba attribútumaival rendelkező Szoba
     */
    public void setUj(Szoba u){
		System.out.println("Szoba attibutumainak beallitasa\n");
    }

    /* Kivesz egy aktív tárgyak a szoba aktív tárgyai közül,
     * felhasználás aktív tárgy hatásidejének lejárásánál
     * @param r a lejárt aktív tárgy
     */
    public void removeAktiv(Romlandok r){
        Targyinventory ti = getAktiv();
	    ti.RemoveTargy(r);
	    setAktiv(ti);
        System.out.println("Aktiv targy lejart");
    }

    /* Szoba egyesülés, az adott szoba attribútumai megváltoznak
     * @param sz Szoba, amellyel egyesülnie kell az adott szobának,
     * property beállítás után megsemmisül
     */
    public void merge(Szoba sz){
        System.out.println("Szoba egyesules");
        Szoba temp = sz.getSzobaProperties();
        setUj(temp);
        System.out.println("Szoba 2 megsemmisul");
    }

    /* Szoba szétesés, szoba meg fog semmisülni, két újat létrehoz,
     * amelyek meg fognak osztozni az eredeti attribútumain
     * @return újonnan létrejött 2 szoba listája
    */
    public List<Szoba> split(Szoba sz){
        System.out.println("Szoba szetesik");
        Szoba uj1 = new Szoba();
        Szoba uj2 = new Szoba();
        uj1.setUj(this);
        uj2.setUj(this);
        System.out.println("Szeteses kesz, szoba megsemmisul");
        return null;
    }

    /* Visszatér egy szobával,
     * amely tartalmazza a szoba összes attribútumát
     * @param Szoba
     */
    public Szoba getSzobaProperties(){
        return null;
    }
}
