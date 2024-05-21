package logarlec;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

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
                    Karakter jelenlegiKar = Graf.getAktKarakter().getModel();
                    List<Szoba> szobaList = jelenlegiKar.getSzoba().getSzomszedok();
                    n -= 1;
                    jelenlegiKar.mozog(szobaList.get(n));
                    break;
                case 'a':
                    /* aktiválás */
                    ITargy aktivalando = Graf.getAktKarakter().getModel().eszkozkeszlet.getTargyak().get(n-1);
                    aktivalando.aktival(Graf.getAktKarakter().getModel());
                    break;
                case 'f':
                    /* felvétel */
                    Graf.getAktKarakter().getModel().felvesz(n-1);
                    break;
                case 'l':
                    /* lerakás */
                    Graf.getAktKarakter().getModel().letesz(n-1);
                    break;
                case 'o':
                    command.append(n);
                    if (command.toString().split(" ").length == 2) {
                        /* tranzisztor összekapcsolás */
                        String[] numbers = command.toString().split(" ");
                        int number1 = Integer.parseInt(numbers[0]);
                        int number2 = Integer.parseInt(numbers[1]);
                        Tranzisztor tr = (Tranzisztor)Graf.getAktKarakter().getModel().eszkozkeszlet.getTargyak().get(number1-1);
                        Tranzisztor tr2 = (Tranzisztor)Graf.getAktKarakter().getModel().eszkozkeszlet.getTargyak().get(number2-1);
                        tr.setPar(tr2);
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
        /* nem kell */
    }

    @Override
    public void keyReleased(KeyEvent k) {
        /* nem kell */
    }

}