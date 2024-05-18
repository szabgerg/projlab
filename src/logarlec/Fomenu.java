package logarlec;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Fomenu {
	public static JFrame window;
	public static boolean visible = true;
	private static JButton startButton, addPlayerButton, exitButton;
	private static JLabel playernum;
	private static int playerCount = 0;
	static JPanel jp;

	public Fomenu() throws IOException {
		window = new JFrame();
		jp = new JPanel();
		jp.setBackground(Color.LIGHT_GRAY);
		jp.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel title = new JLabel("Budapesti Műszaki és Gazdaságtudományi Egyetem");
		title.setFont(new Font("Consolas", Font.PLAIN, 20));
		title.setForeground(Color.black);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(20, 0, 20, 0); // Margó a cím körül
		gbc.anchor = GridBagConstraints.CENTER;
		jp.add(title, gbc);

		// Kép betöltése és középre helyezése
		JLabel imageLabel = new JLabel(new ImageIcon(ImageIO.read(new File("src/bme_logo.png"))));
		gbc.gridy++;
		gbc.insets = new Insets(20, 0, 20, 0); // Margó a kép körül
		jp.add(imageLabel, gbc);

		// Gombok tartópanelje
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 10)); // 10 pixel vertikális rés a gombok között
		buttonPanel.setBackground(Color.LIGHT_GRAY);

		startButton = createImageButton("Start", "kepek/start&exit.png");
		startButton.addActionListener(new StartButtonListener());

		addPlayerButton = createImageButton("Add player", "kepek/start&exit.png");
		addPlayerButton.addActionListener(new AddplayerButtonListener());

		exitButton = createImageButton("Exit", "kepek/start&exit.png");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit button pressed");
				window.setVisible(false);
				window.dispose();
			}
		});

		// Gombok hozzáadása a panelhez
		buttonPanel.add(startButton);
		buttonPanel.add(addPlayerButton);
		buttonPanel.add(exitButton);

		gbc.gridy++;
		gbc.insets = new Insets(10, 0, 10, 0); // Gombok közötti margó
		jp.add(buttonPanel, gbc);

		playernum = new JLabel("Number of players: " + playerCount);
		gbc.gridy++;
		jp.add(playernum, gbc);

		window.add(jp);
		window.setTitle("Labirintus");
		window.setSize(1280, 720);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	private static void setVisible(boolean b) {
		visible = b;
		window.setVisible(b);
	}

	public boolean getVisible() {
		return visible;
	}

	private JButton createImageButton(String text, String imagePath) throws IOException {
		JButton button = new JButton(text);
		ImageIcon icon = new ImageIcon(ImageIO.read(new File(imagePath)));
		button.setIcon(icon);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setOpaque(false);
		return button;
	}

	private static class StartButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Graf.startGameUi();
		}
	}

	private static class AddplayerButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			playerCount++;
			Proto.newHallgato(Proto.getRandSzoba(), new Targyinventory());
			playernum.setText("Number of players: " + playerCount);
		}
	}
}
