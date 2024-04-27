package logarlec;

import java.util.Scanner;

//megvalósítja az ITargy interfészt
public class Tranzisztor implements ITargy{

    private Tranzisztor par;
    private Szoba szoba;

    /*
     * Tranzisztor konstruktor
     */
    public Tranzisztor(Tranzisztor t, Szoba s) {
        System.out.println("Tranzisztor létrehozva\n");
        par = t;
        szoba = s;
    }

    /*
     * Visszaadja a tranzisztor párját
     * @return Tranzisztor - a tranzisztor párja
     */
    public Tranzisztor getPar(){
        System.out.println("Tranzisztor párja lekérdezve");
        return par;
    }

    /*
     * Beállítja a tranzisztor párját
     * @param t - a beállítandó tranzisztor
     */
    public void setPar(Tranzisztor t){
        par = t;
        System.out.println("Tranzisztor pár beállítva");
    }

    /*
     * Visszaadja hogy a tranzisztor melyik szobában van
     * @return int - a tranzisztor szobája
     */
    public Szoba getSzoba(){
        System.out.println("Szoba lekérdezve");
        return szoba;
    }

    /*
     * Beállítja a tranzisztor szobáját
     * @param s - a beállítandó szoba
     */
    public void setSzoba(Szoba s){
        szoba = s;
        System.out.println("Szoba beállítva");
    }

    /*
    * Tranzisztor aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    public void aktival(Karakter k) {
        k.jelenlegi.getAktiv().AddTargy(this);
        System.out.println("Tranzisztor aktiválva");
    }

    /**
     * Tranzisztor ezeket nem tudja használni
     */
    public boolean hasznal(Karakter k){
        System.out.println("Tranzisztor használva");
        return false;
    }

    public boolean szur(Karakter k){
        System.out.println("Tranzisztor szűrve");
        return false;
    }



    /*
     * Tranzisztor párosíthatóságának ellenőrzése
     * @return boolean - párosítható-e a tranzisztor
     */
    public boolean canPair(){
        System.out.println("A tranzisztorok össze vannak kapcsolva? (I/N)");
        Scanner scanner = new Scanner(System.in);
        String valasz = scanner.nextLine();
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
