package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.Ranking;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel = new ImagePanel("src/gui/res/MainMenu.jpg");

	private final JButton btnPlayGame = new JButton("Play Game");
	private final JButton btnOptions = new JButton("Options");
	private final JButton btnRules = new JButton("Rules");
	private final JButton btnRanking = new JButton("Ranking");
	private final JButton btnCredits = new JButton("Credits");

	GamePanel game = new GamePanel(mainPanel, this);
	OptionsMenu optionsMenu = new OptionsMenu(game);

	List<Ranking> ranking = new ArrayList<Ranking>();


	public MainMenu() {

		setResizable(false);
		setTitle("LPOO - PYS 1 Snooker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Utilities.dimScreen.width - 10, Utilities.dimScreen.height - 100);
		setLocation((Utilities.dimScreen.width - getWidth()) / 2, (Utilities.dimScreen.height - getHeight()) / 2);

		mainPanel.setSize(getSize());
		mainPanel.setLayout(null);

		mainPanel.setBackground(Color.GRAY);

		SetMainMenuButtons();

		getContentPane().add(mainPanel, BorderLayout.CENTER);

		ManageButtonListener();
	}

	public void SetMainMenuButtons() {
		btnPlayGame.setBackground(new Color(0, 139, 139));
		btnPlayGame.setContentAreaFilled(false);
		btnPlayGame.setOpaque(true);
		btnPlayGame.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 12));
		btnPlayGame.setForeground(new Color(255, 255, 255));
		btnPlayGame.setBounds(102, 63, 100, 40);
		mainPanel.add(btnPlayGame);

		btnOptions.setBackground(new Color(128, 0, 128));
		btnOptions.setContentAreaFilled(false);
		btnOptions.setOpaque(true);
		btnOptions.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 12));
		btnOptions.setForeground(new Color(255, 255, 255));
		btnOptions.setBounds(304, 166, 100, 40);
		mainPanel.add(btnOptions);

		btnRules.setBackground(Color.GREEN);
		btnRules.setContentAreaFilled(false);
		btnRules.setOpaque(true);
		btnRules.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 12));
		btnRules.setForeground(new Color(255, 255, 255));
		btnRules.setBounds(506, 284, 100, 40);
		mainPanel.add(btnRules);

		btnRanking.setBackground(Color.BLACK);
		btnRanking.setContentAreaFilled(false);
		btnRanking.setOpaque(true);
		btnRanking.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 12));
		btnRanking.setForeground(new Color(255, 255, 255));
		btnRanking.setBounds(708, 387, 100, 40);
		mainPanel.add(btnRanking);

		btnCredits.setBackground(Color.RED);
		btnCredits.setContentAreaFilled(false);
		btnCredits.setOpaque(true);
		btnCredits.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 12));
		btnCredits.setForeground(new Color(255, 255, 255));
		btnCredits.setBounds(910, 502, 100, 40);
		mainPanel.add(btnCredits);
	}

	public void ManageButtonListener() {
		btnPlayGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean checkName1 = false;
				boolean checkName2 = false;
				String toCheckedP1 = new String();
				String toCheckedP2 = new String();

				while(!checkName1) {
					toCheckedP1 = JOptionPane.showInputDialog(mainPanel, "Insert Name: Player 1");

					if(toCheckedP1 != null) {
						if(toCheckedP1.length() > 10 || toCheckedP1.equals("")) {
							JOptionPane.showMessageDialog(mainPanel, "Insert a name with maximum of 10 letters.");
							checkName1 = false;
						}
						else
							checkName1 = true;
					}
					else
						break;
				}

				if(checkName1)
					while(!checkName2) {
						toCheckedP2 = JOptionPane.showInputDialog(mainPanel, "Insert Name: Player 2");

						if(toCheckedP2 != null) {
							if(toCheckedP2.length() > 10 || toCheckedP2.equals("")) {
								JOptionPane.showMessageDialog(mainPanel, "Insert a name with maximum of 10 letters.");
								checkName2 = false;
							}
							else
								checkName2 = true;
						}
						else
							break;
					}

				if(checkName1 && checkName2) {
					game.getGame().getP1().setName(toCheckedP1);
					game.getGame().getP2().setName(toCheckedP2);
					mainPanel.setVisible(false);
					getContentPane().add(game);
					try {
						game.Init();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		btnOptions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				optionsMenu.setVisible(true);
			}
		});

		btnRules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(mainPanel, Utilities.rulesStr, "Rules", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnRanking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (game.getGame().getRanking() != null)
					JOptionPane.showMessageDialog(mainPanel, game.getGame().getRanking().toString(), "Ranking", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(mainPanel,"No information available!","Ranking",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnCredits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(mainPanel, "            João Pereira\n               Luís Pina", "Credits", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
