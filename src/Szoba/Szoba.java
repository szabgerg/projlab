package Szoba;

import java.util.List;
import java.util.Scanner;

import Karakterek.Karakter;
import Targyak.*;
import Targyak.Romlandok.Romlandok;

public class Szoba {
    private int befogadokepesseg;
    private List<Szoba> szomszedok;
    private List<Karakter> bentlevok;
    private Targyinventory bentiTargyak;
    private Targyinventory aktivTargyak;

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
    public List<Karakter> getBentlevok(){
        return null;
    }
    public void setBentlevok(Karakter K){
		System.out.println("Bent levo karakterek beallitasa\n");
    }
    public void kilep(Karakter k){
        System.out.println("Kilepes a szobabol");
    }
    public Targyinventory getBentiTargyak(){
		System.out.println("A szobában található tárgyak:\n" +
        "1. Tranzisztor\n" +
        "2. Rongy\n" +
        "3. Camambert\n" +
        "4. Logarlec\n");
        return null;
    }
    public void setBentiTargyak(Targyinventory t){
		System.out.println("Szoba targyainak beallitasa\n");
    }
    public List<Szoba> getSzomszedok(){
        return null;
    }
    public void setSzomszedok(List<Szoba> s){
		System.out.println("Szoba szomszedainak beallitasa\n");
    }
    public Targyinventory getAktiv(){
        return null;
    }
    public void setAktiv(Targyinventory targy){
		System.out.println("Aktiv targyak beallitasa\n");
    }
    public void setBefogadokepesseg(int d){
		System.out.println("Befogadokepesseg beallitasa\n");
    }
    public int getBefogadokepesseg(){
        return 5;
    }
    public void setUj(Szoba u){
		System.out.println("Szoba attibutumainak beallitasa\n");
    }
    public void removeAktiv(Romlandok r){
        Targyinventory ti = getAktiv();
        ti.targyak.remove(r);
        System.out.println("Aktiv targy lejart");
    }
    public List<Szoba> merge(Szoba sz){
        System.out.println("Szoba egyesules");
        Szoba temp = sz.getSzobaProperties();
        setUj(temp);
        System.out.println("Szoba 2 megsemmisul");
        return null;
    }
    public List<Szoba> split(Szoba sz){
        System.out.println("Szoba szetesik");
        Szoba uj1 = new Szoba();
        Szoba uj2 = new Szoba();
        uj1.setUj(this);
        uj2.setUj(this);
        System.out.println("Szeteses kesz, szoba megsemmisul");
        return null;
    }
    public Szoba getSzobaProperties(){
        return null;
    }
}