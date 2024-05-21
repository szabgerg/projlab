package logarlec;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private String[] layout;

    public String[] getLayout() {
        return layout;
    }

    // szoveg input allapotgephez
    private StringBuilder command = new StringBuilder();

    private void numberInput(int n) {
        if (command.length() > 0) {
            char action = command.charAt(0);
            switch (action) {
                case 'm':
                    /* mozgás */
                    break;
                case 'a':
                    /* aktiválás */
                    break;
                case 'f':
                    /* felvétel */
                    break;
                case 'l':
                    /* lerakás */
                    break;
                case 'o':
                    command.append(n);
                    if (command.toString().split(" ").length == 2) {
                        /* tranzisztor kapcsolás */
                        command.setLength(0); // reset
                    }
                    break;
            }
        }
    }

    /* beviteli karakterek tárolása */
    private void wasInput(char c) {
        command.setLength(0); // Reset command
        command.append(c);
    }

    @Override
    public void keyPressed(KeyEvent k) {
        char c = k.getKeyChar();
        if (Character.isDigit(c)) {
            numberInput(Character.getNumericValue(c));
        } else if (c == ' ') {
            command.append(' ');
        } else {
            wasInput(c);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent k) {
        /* tudtommal nem kell */
    }

    @Override
    public void keyReleased(KeyEvent k) {
        /* tudtommal nem kell */
    }

}
/*
● mozgás: ‘m’ megnyomása majd egy szám megadása ami az óra 12-es mutatójánál 1 és
    az óra járásával megyegyező írányba történik
● aktiválás: ‘a’ megnyomása majd egy szám ami az inventoryban lévő helyet jelzi
● felvétel: ‘f’ majd a szoba inventoryjának a megfelelő helyén lévő item száma
● letevés: ‘l’ majd ugyanaz mint feljebb
● 2 tranzisztor összekapcsolása: ‘o’ gomb majd kettő szám szóközzel elválasztva, ezen
    két helyen van a két tranzisztor */
