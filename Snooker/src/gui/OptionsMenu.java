package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import logic.Ball;

public class OptionsMenu extends JDialog {
	private static final long serialVersionUID = 1L;
	
	GamePanel game;
	
	JLabel frictionLabel;
	JLabel ballWeightLabel;
	
	JRadioButton lowFriction;
	JRadioButton normalFriction;
	JRadioButton highFriction;
	
	JRadioButton lightWeight;
	JRadioButton normalWeight;
	JRadioButton heavyWeight;
	
	JButton btnSaveOptions;
	JButton btnCancelOptions;
	
	boolean oldLowFriction;
	boolean oldNormalFriction;
	boolean oldHighFriction;
	
	boolean oldLightValue;
	boolean oldNormalValue;
	boolean oldHeavyValue;
	
	public OptionsMenu(GamePanel game) {
		this.game = game;
		
		getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		getContentPane().setForeground(Color.GREEN);
		setTitle("LPOO - PYS 1 - Snooker Options");
		setSize(Utilities.optionsMenuSize);
		setLocation((Utilities.dimScreen.width - getSize().width) / 2, (Utilities.dimScreen.height - getSize().height) / 2);
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
		
		SetUpFrictionButtons();
		SetUpWeightButtons();
		SetUpSaveCancelButtons();
		
		oldLowFriction = lowFriction.isSelected();
		oldNormalFriction = normalFriction.isSelected();
		oldHighFriction = highFriction.isSelected();
		
		oldLightValue = lightWeight.isSelected();
		oldNormalValue = normalWeight.isSelected();
		oldHeavyValue = heavyWeight.isSelected();
		
		ManageListeners();
	}
	
	public void SetUpFrictionButtons() {
		ButtonGroup frictionButtons = new ButtonGroup();
		
		frictionLabel = new JLabel("Friction");
		frictionLabel.setOpaque(true);
		frictionLabel.setBackground(new Color(0, 255, 0));
		frictionLabel.setForeground(new Color(0, 0, 0));
		frictionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frictionLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		frictionLabel.setBounds(171, 91, 98, 59);
		getContentPane().add(frictionLabel);
		
		lowFriction = new JRadioButton("Low");
		lowFriction.setBounds(275, 82, 109, 23);
		getContentPane().add(lowFriction);
		
		normalFriction = new JRadioButton("Normal");
		normalFriction.setBounds(275, 108, 109, 23);
		normalFriction.setSelected(true);
		getContentPane().add(normalFriction);
		
		highFriction = new JRadioButton("High");
		highFriction.setBounds(275, 134, 109, 23);
		getContentPane().add(highFriction);
		
		frictionButtons.add(lowFriction);
		frictionButtons.add(normalFriction);
		frictionButtons.add(highFriction);
	}

	public void SetUpWeightButtons() {
		
		ButtonGroup ballWeightButtons = new ButtonGroup();
		
		ballWeightLabel = new JLabel("Ball Weight");
		ballWeightLabel.setOpaque(true);
		ballWeightLabel.setBackground(new Color(255, 255, 0));
		ballWeightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ballWeightLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		ballWeightLabel.setBounds(171, 251, 98, 59);
		getContentPane().add(ballWeightLabel);
		
		lightWeight = new JRadioButton("Light");
		lightWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lightWeight.setBounds(287, 246, 89, 23);
		getContentPane().add(lightWeight);
		
		normalWeight = new JRadioButton("Normal");
		normalWeight.setHorizontalAlignment(SwingConstants.CENTER);
		normalWeight.setBounds(287, 272, 89, 23);
		normalWeight.setSelected(true);
		getContentPane().add(normalWeight);
		
		heavyWeight = new JRadioButton("Heavy");
		heavyWeight.setHorizontalAlignment(SwingConstants.CENTER);
		heavyWeight.setBounds(287, 298, 89, 23);
		getContentPane().add(heavyWeight);
		
		ballWeightButtons.add(lightWeight);
		ballWeightButtons.add(normalWeight);
		ballWeightButtons.add(heavyWeight);
	}

	public void SetUpSaveCancelButtons() {
		btnSaveOptions = new JButton("Save");
		btnSaveOptions.setBounds(161, 438, 108, 38);
		getContentPane().add(btnSaveOptions);
		
		btnCancelOptions = new JButton("Cancel");
		btnCancelOptions.setBounds(308, 438, 108, 38);
		getContentPane().add(btnCancelOptions);
	}

	public void ManageListeners() {
		btnSaveOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateGameValues();
				setVisible(false);
			}
		});
		
		btnCancelOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ar0) {
				lowFriction.setSelected(oldLowFriction);
				normalFriction.setSelected(oldNormalFriction);
				highFriction.setSelected(oldHighFriction);
				
				lightWeight.setSelected(oldLightValue);
				normalWeight.setSelected(oldNormalValue);
				heavyWeight.setSelected(oldHeavyValue);
				setVisible(false);
			}
		});
	}

	public void updateGameValues() {
		
		if(lowFriction.isSelected())
			game.getGame().getTable().getCloth().setFriction(0.993);
		else if(normalFriction.isSelected())
			game.getGame().getTable().getCloth().setFriction(0.99);
		else
			game.getGame().getTable().getCloth().setFriction(0.95);
		
		if(lightWeight.isSelected())
			Ball.setMass(0.08);
		else if(normalWeight.isSelected())
			Ball.setMass(0.17);
		else
			Ball.setMass(0.35);
	}
}
