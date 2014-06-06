package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel = new ImagePanel("src/gui/res/MainMenu.jpg");
	
	private final JButton btnPlayGame = new JButton("Play Game");
	private final JButton btnOptions = new JButton("Options");
	private final JButton btnRules = new JButton("Rules");
	private final JButton btnRanking = new JButton("Ranking");
	private final JButton btnCredits = new JButton("Credits");
	
	GamePanel game = new GamePanel();
	OptionsMenu optionsMenu = new OptionsMenu();
	
	
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
				// TODO Auto-generated method stub
				mainPanel.setVisible(false);
				getContentPane().add(game);
				try {
					game.Init();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				optionsMenu.setVisible(true);
			}
		});
		
		btnRules.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnRanking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnCredits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
