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
            Karakter jelenlegiKar = Graf.getAktKarakter().getModel();

            switch (action) {
                case 'm':
                    /* mozgás */
                    List<Szoba> szobaList = jelenlegiKar.getSzoba().getSzomszedok();
                    n -= 1;
                    if (n >= 0 && n < szobaList.size()) {
                        jelenlegiKar.mozog(szobaList.get(n));
                    } else {
                        System.out.println("Nincs ilyen számú szoba");
                    }
                    break;

                case 'a':
                    /* aktiválás */
                    Targyinventory eszkozkeszlet = jelenlegiKar.getEszkozkeszlet();
                    List<ITargy> targyak = eszkozkeszlet.getTargyak();
                    // ITargy aktivalando =
                    // Graf.getAktKarakter().getModel().eszkozkeszlet.getTargyak().get(n-1);
                    if (n >= 0 && n < targyak.size() && targyak.get(n) != null) {
                        ITargy aktivalando = targyak.get(n);
                        aktivalando.aktival(jelenlegiKar);
                    }
                    break;

                case 'f':
                    /* felvétel */
                    Targyinventory bentitargyak = jelenlegiKar.getSzoba().getBentiTargyak();
                    if (n >= 0 && n < bentitargyak.getTargyak().size()) {
                        jelenlegiKar.felvesz(n - 1);
                    }
                    break;
                case 'l':
                    /* lerakás */
                    if (n >= 0 && n < jelenlegiKar.getEszkozkeszlet().getTargyak().size()) {
                        jelenlegiKar.letesz(n - 1);
                    }
                    break;

                case 'o':
                    command.append(n);
                    if (command.toString().split(" ").length == 2) {
                        /* tranzisztor összekapcsolás */
                        try {
                            String[] numbers = command.toString().split(" ");

                            int number1 = Integer.parseInt(numbers[0]);
                            int number2 = Integer.parseInt(numbers[1]);

                            List<ITargy> targyakk = Graf.getAktKarakter().getModel().eszkozkeszlet.getTargyak();

                            if (number1 - 1 >= 0 && number1 - 1 < targyakk.size() && number2 - 1 >= 0
                                    && number2 - 1 < targyakk.size()) {
                                Tranzisztor tr = (Tranzisztor) targyakk.get(number1 - 1);
                                Tranzisztor tr2 = (Tranzisztor) targyakk.get(number2 - 1);

                                if (tr != null && tr2 != null) {
                                    tr.setPar(tr2);
                                } else {
                                    System.out.println("Tranzisztor null. :(");
                                }

                            } else {
                                System.out.println("Hibás tranzisztor indexek.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Index out of bounds.");
                        }
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