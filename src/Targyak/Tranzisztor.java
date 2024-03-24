package Targyak;

import java.util.Scanner;

import Szoba.Szoba;

//megvalósítja az ITargy interfészt
public class Tranzisztor implements ITargy{

    /*
     * Tranzisztor konstruktor
     */
    public Tranzisztor() {
        System.out.println("Tranzisztor létrehozva\n");
    }

    /*
     * Visszaadja a tranzisztor párját
     * @return Tranzisztor - a tranzisztor párja
     */
    public Tranzisztor getPar(){
        System.out.println("Tranzisztor párja lekérdezve");
        return new Tranzisztor();
    }

    /*
     * Beállítja a tranzisztor párját
     * @param t - a beállítandó tranzisztor
     */
    public void setPar(Tranzisztor t){
        System.out.println("Tranzisztor pár beállítva");
    }

    /*
     * Visszaadja hogy a tranzisztor melyik szobában van
     * @return int - a tranzisztor szobája
     */
    public Szoba getSzoba(){
        System.out.println("Szoba lekérdezve");
        return new Szoba();
    }

    /*
     * Beállítja a tranzisztor szobáját
     * @param s - a beállítandó szoba
     */
    public void setSzoba(Szoba s){
        System.out.println("Szoba beállítva");
    }

    /*
    * Tranzisztor aktiválása, aktiv jelzés beállítása
    */
    public void aktival(Szoba s) {
        //boolean aktiv->true;
        System.out.println("Tranzisztor aktiválva");
        s.getAktiv().AddTargy(this);
    }

    /*
     * Tranzisztor párosíthatóságának ellenőrzése
     * @return boolean - párosítható-e a tranzisztor
     */
    public boolean canPair(){
        System.out.println("A tranzisztorok össze vannak kapcsolva? (I/N)");
        Scanner scanner = new Scanner(System.in);
        String valasz = scanner.nextLine();
        scanner.close();
        if(valasz.equals("N")){
            System.out.println("Tranzisztor párosítható");
            return true;
        }
        else if(valasz.equals("I")){
            System.out.println("Tranzisztor nem párosítható");
            return false;
        }else{
            System.out.println("Nem értelmezett válasz");
            return false;
        }
    }

    /*
     * Tranzisztor kikapcsolása, aktiv jelzés beállítása
     */
    public void kikapcsol(){
        //boolean aktiv->false;
        System.out.println("Tranzisztor kikapcsolva");
    }
}
