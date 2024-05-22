package logarlec;

import java.util.ArrayList;
import java.util.List;

/*
 * Szoba osztály, a játék teljes egészében a szobák
 * valamelyikében játszódik. A karakterek szobák között
 * mozoghatnak, hacsak nem vesztette el a lelkét egy
 * hallgató, minden karakter egyszerre mindig egy szobában
 * tartozkodik. A szobában tárgyak is lehetnek, a szobák
 * különböző tulajdonságokkal rendelkezhetnek
 */
public class Szoba {

    // Szoba privat attributumai
    private int befogadokepesseg;
    private List<Szoba> szomszedok;
    private List<Karakter> bentlevok;
    private Targyinventory bentiTargyak;
    private Targyinventory aktivTargyak;
    //-1 ha még nem volt takarítva
    private int legutobbTakaritva = -1;

    /* Szoba default konstruktora
    */
    public Szoba() {
        System.out.println("Szoba_letrehozva");
        befogadokepesseg = 0;
        szomszedok = null;
        bentiTargyak = null;
        bentlevok = null;
        aktivTargyak = null;
        legutobbTakaritva = -1;
    }

    public Szoba(int bef, Targyinventory targyinventory){
        System.out.println("Szoba_letrehozva");
        befogadokepesseg = bef;
        bentiTargyak = targyinventory;
        szomszedok = new ArrayList<Szoba>();
        bentlevok = new ArrayList<Karakter>();
        aktivTargyak = new Targyinventory();
        legutobbTakaritva = -1;

    }
    /* Szoba osztály konstruktora
     * szoba inventoryját feltölti, szobába karaktereket beteszi
    */
    public Szoba(int bef, List<Szoba> szomsz, List<Karakter> benti, Targyinventory inventory, Targyinventory targyi, int tak) {

        System.out.println("Szoba_letrehozva");
	    System.out.println("Szoba_inventoryja_feltoltve");
	    System.out.println("Szobaba_karakterek_beteve");
       
        befogadokepesseg = bef;
        szomszedok = szomsz;
        bentlevok = benti;
        bentiTargyak = inventory;
        aktivTargyak = targyi;
        legutobbTakaritva = tak;
    }
    /* A szoba befogadóképessége alapján
     * eldönti, hogy egy új karakter be tud-e lépni
     * @return: true, ha beléphet, false, ha tele a szoba
     */
    public boolean beenged() {
        if (getBentlevok().size() >= befogadokepesseg){
            System.out.println("Nincs_hely");
            return false;
        }
        System.out.println("Van_hely");
        return true;
    }
    
    /* Visszatér az adott szobában tartózkodó karakterekkel
     * @return karakterek listája
     */
    public List<Karakter> getBentlevok(){
        return bentlevok;
    }

    /* Beállítja a szobában tartózkodó karaktereket,
     * szobák közti mozgás/elhalálozás esetében
     * @param k a bent lévő karakterek listája, beállítandó érték
     */
    public void setBentlevok(List<Karakter> k){
		System.out.println("Bentlevo_karakterek_beallitasa");
        bentlevok = k;
    }

    /* Meghívódik, ha egy karakter elhagyja a szobát,
     * beállítja a frissített bentlevok listáját
     * @param k: a kilépő karakter
     */
    public void kilep(Karakter k){
        
        bentlevok.remove(k);
        System.out.println("Kilepett_a_szobabol");
    }

    /* Visszatér a szobában található tárgyak listájával
     * @return Targyinventory
     */
    public Targyinventory getBentiTargyak(){
        return bentiTargyak;
    }

    /* Beállítja a szobában található tárgyakat
     * @param t a tárgyak listája, a beállítandó érték
     */
    public void setBentiTargyak(Targyinventory t){
		System.out.println("Szoba_targyainak_beallitasa");
        bentiTargyak = t;
    }

    /* Visszaadja a szoba szomszédait
     * @return a szobák listája, amelyekbe át lehet jutni
     */
    public List<Szoba> getSzomszedok(){
        return szomszedok;
    }

    /* Beállítja a szoba szomszédait, akikhez
     * van egyenes út az adott szobából
     * @param s a szomszédok listája
     */
    public void setSzomszedok(List<Szoba> s){
		System.out.println("Szoba_szomszedainak_beallitasa");
        this.szomszedok = s;
    }

    /* Visszaadja a szoba jelenleg aktív tárgyait,
     * amelyek hatása jelenleg befolyásolja a szoba működését
     * @return Targyinventory, az aktív tárgyak listája
     */
    public Targyinventory getAktiv(){
        return aktivTargyak;
    }

    /* Beállítja a szoba jelenleg aktív hatással bíró tárgyait,
     * meghívódik például, ha egy karakter aktivál egy új tárgyat
     * @param targy az aktív tárgyak listája
     */
    public void setAktiv(Targyinventory targy){
		System.out.println("Aktiv_targyak_beallitasa");
        aktivTargyak = targy;
    }

    /* Beállítja a szoba befogadóképességét,
     * @param d a beállítandó érték
     */
    public void setBefogadokepesseg(int d){
		System.out.println("Befogadokepesseg_beallitasa");
        befogadokepesseg = d;
    }

    /* Visszatér a szoba befogadóképességével
     * @return int
     */
    public int getBefogadokepesseg(){
        return befogadokepesseg;
    }

    /* Beállítja egy szoba összes attribútumát,
     * felhasználás például új szoba létrehozásánál,
     * merge / split esetnél
     * @param u az új szoba attribútumaival rendelkező Szoba
     */
    public void setUj(Szoba u){
		System.out.println("Szoba_attibutumainak_beallitasa");
        this.aktivTargyak = u.aktivTargyak;
        this.befogadokepesseg = u.befogadokepesseg;
        this.bentiTargyak = u.bentiTargyak;
        this.bentlevok = u.bentlevok;
        this.legutobbTakaritva = u.legutobbTakaritva;
        this.szomszedok = u.szomszedok;
    }

    public void setLegutobbTakaritva(int i){
        legutobbTakaritva = i;
    }

    /* Kivesz egy aktív tárgyak a szoba aktív tárgyai közül,
     * felhasználás aktív tárgy hatásidejének lejárásánál
     * @param r a lejárt aktív tárgy
     */
    public void removeAktiv(Romlandok r){
        Targyinventory ti = getAktiv();
	    ti.RemoveTargy(r);
	    setAktiv(ti);
        System.out.println("Aktiv_targy_lejart");
    }

    /* Szoba egyesülés, az adott szoba attribútumai megváltoznak
     * @param sz Szoba, amellyel egyesülnie kell az adott szobának,
     * property beállítás után megsemmisül
     */
    public void merge(Szoba sz){
        System.out.println("Szoba_egyesules");
        Szoba temp = sz.getSzobaProperties();
        setUj(temp);
        System.out.println("Szoba_2_megsemmisul");
    }

    /* Szoba szétesés, szoba meg fog semmisülni, két újat létrehoz,
     * amelyek meg fognak osztozni az eredeti attribútumain
     * @return újonnan létrejött 2 szoba listája
    */
    public List<Szoba> split(){
        System.out.println("Szoba_szetesik");
        Szoba uj1 = new Szoba();
        Szoba uj2 = new Szoba();
        uj1.setUj(this);
        uj2.setUj(this);
        System.out.println("Szeteses_kesz,_szoba_megsemmisul");
        return null;
    }

    /* Visszatér egy szobával,
     * amely tartalmazza a szoba összes attribútumát
     * @param Szoba
     */
    public Szoba getSzobaProperties(){
        return this;
    }

    public int getLegutobbTakaritva(){
        return legutobbTakaritva;
    }

    public boolean getRagacsos() {
        if(legutobbTakaritva <0) return false;
		int rand = (int) (Proto.getRandVal() * 10);
		return legutobbTakaritva <= rand;
	}
}
