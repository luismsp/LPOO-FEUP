package gui;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerListModel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OptionsMenu extends JDialog {
	private static final long serialVersionUID = 1L;
	String frictionValues[] = new String[20];
	
	JLabel frictionLabel;
	JLabel ballWeightLabel;
	
	ButtonGroup ballWeightButtons;
	
	JButton btnSaveOptions;
	JButton btnCancelOptions;
	
	JSpinner frictionSpinner;
	
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
		
		for(int i = 0; i < 20; i++) {
			frictionValues[i] = i+1 + "";
		}
		
		SpinnerListModel fricValues = new SpinnerListModel(frictionValues); 
		frictionSpinner = new JSpinner(fricValues);
		frictionSpinner.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		frictionSpinner.setAutoscrolls(true);
		frictionSpinner.setToolTipText("Atrito");
		frictionSpinner.setBounds(308, 104, 64, 32);
		getContentPane().add(frictionSpinner);
	}

	public void SetUpWeightButtons() {
		ballWeightLabel = new JLabel("Ball Weight");
		ballWeightLabel.setOpaque(true);
		ballWeightLabel.setBackground(new Color(255, 255, 0));
		ballWeightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ballWeightLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		ballWeightLabel.setBounds(197, 216, 98, 59);
		getContentPane().add(ballWeightLabel);
		
		ballWeightButtons = new ButtonGroup();
		JRadioButton heavyWeight = new JRadioButton("Heavy");
		heavyWeight.setHorizontalAlignment(SwingConstants.CENTER);
		heavyWeight.setBounds(309, 205, 89, 23);
		getContentPane().add(heavyWeight);
		
		JRadioButton normalWeight = new JRadioButton("Normal");
		normalWeight.setHorizontalAlignment(SwingConstants.CENTER);
		normalWeight.setBounds(309, 231, 89, 23);
		normalWeight.setSelected(true);
		getContentPane().add(normalWeight);
		
		JRadioButton highWeight = new JRadioButton("High");
		highWeight.setHorizontalAlignment(SwingConstants.CENTER);
		highWeight.setBounds(309, 257, 89, 23);
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
	}

	public void ManageListeners() {
		btnSaveOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				
			}
		});
		
		btnCancelOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ar0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
}
