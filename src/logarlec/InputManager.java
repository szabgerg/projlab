package logarlec;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private String[] layout;

    public String[] getLayout() {
        return layout;
    }

    private void numberInput(int n){}
    public void keyPressed(KeyEvent k){}
    public void keyTyped(KeyEvent k){}
    public void keyReleased(KeyEvent k){}
    private void wasInput(char c){}

/*
● mozgás: ‘m’ megnyomása majd egy szám megadása ami az óra 12-es mutatójánál 1 és
    az óra járásával megyegyező írányba történik
● aktiválás: ‘a’ megnyomása majd egy szám ami az inventoryban lévő helyet jelzi
● felvétel: ‘f’ majd a szoba inventoryjának a megfelelő helyén lévő item száma
● letevés: ‘l’ majd ugyanaz mint feljebb
● 2 tranzisztor összekapcsolása: ‘o’ gomb majd kettő szám szóközzel elválasztva, ezen
    két helyen van a két tranzisztor */
}
