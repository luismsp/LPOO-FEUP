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
import javax.swing.JSlider;

public class OptionsMenu extends JDialog {
	private static final long serialVersionUID = 1L;
	
	JLabel frictionLabel;
	JLabel ballWeightLabel;
	
	ButtonGroup ballWeightButtons;
	
	JButton btnSaveOptions;
	JButton btnCancelOptions;
	
	double oldValueFriction;
	
	public OptionsMenu() {
		setTitle("LPOO - PYS 1 - Snooker Options");
		setSize(Utilities.optionsMenuSize);
		setLocation((Utilities.dimScreen.width - getSize().width) / 2, (Utilities.dimScreen.height - getSize().height) / 2);
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
		
		SetUpFrictionSpinner();
		SetUpWeightButtons();
		SetUpSaveCancelButtons();
		
		ManageListeners();
	}
	
	public void SetUpFrictionSpinner() {
		frictionLabel = new JLabel("Friction");
		frictionLabel.setOpaque(true);
		frictionLabel.setBackground(new Color(0, 255, 0));
		frictionLabel.setForeground(new Color(0, 0, 0));
		frictionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frictionLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		frictionLabel.setBounds(197, 89, 98, 59);
		getContentPane().add(frictionLabel);
		
		
	}

	public void SetUpWeightButtons() {
		ballWeightLabel = new JLabel("Ball Weight");
		ballWeightLabel.setOpaque(true);
		ballWeightLabel.setBackground(new Color(255, 255, 0));
		ballWeightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ballWeightLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		ballWeightLabel.setBounds(148, 246, 98, 59);
		getContentPane().add(ballWeightLabel);
		
		ballWeightButtons = new ButtonGroup();
		JRadioButton heavyWeight = new JRadioButton("Heavy");
		heavyWeight.setHorizontalAlignment(SwingConstants.CENTER);
		heavyWeight.setBounds(258, 235, 89, 23);
		getContentPane().add(heavyWeight);
		
		JRadioButton normalWeight = new JRadioButton("Normal");
		normalWeight.setHorizontalAlignment(SwingConstants.CENTER);
		normalWeight.setBounds(258, 261, 89, 23);
		normalWeight.setSelected(true);
		getContentPane().add(normalWeight);
		
		JRadioButton highWeight = new JRadioButton("High");
		highWeight.setHorizontalAlignment(SwingConstants.CENTER);
		highWeight.setBounds(258, 287, 89, 23);
		getContentPane().add(highWeight);
		
		ballWeightButtons.add(heavyWeight);
		ballWeightButtons.add(normalWeight);
		ballWeightButtons.add(highWeight);
	}

	public void SetUpSaveCancelButtons() {
		btnSaveOptions = new JButton("Save");
		btnSaveOptions.setBounds(161, 438, 108, 38);
		getContentPane().add(btnSaveOptions);
		
		btnCancelOptions = new JButton("Cancel");
		btnCancelOptions.setBounds(308, 438, 108, 38);
		getContentPane().add(btnCancelOptions);
		
		JSlider slider = new JSlider();
		slider.setMaximum(110);
		slider.setMinimum(70);
		slider.setBounds(147, 159, 200, 26);
		getContentPane().add(slider);
	}

	public void ManageListeners() {
		btnSaveOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				
			}
		});
		
		btnCancelOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ar0) {
				
				setVisible(false);
			}
		});
	}
}
