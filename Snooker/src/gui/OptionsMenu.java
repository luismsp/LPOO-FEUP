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
	
	JRadioButton lightWeight;
	JRadioButton normalWeight;
	JRadioButton heavyWeight;
	
	JButton btnSaveOptions;
	JButton btnCancelOptions;
	
	JSlider frictionSlider;
	
	int oldValueFriction;
	boolean oldLightValue;
	boolean oldNormalValue;
	boolean oldHeavyValue;
	
	public OptionsMenu() {
		getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		getContentPane().setForeground(Color.GREEN);
		setTitle("LPOO - PYS 1 - Snooker Options");
		setSize(Utilities.optionsMenuSize);
		setLocation((Utilities.dimScreen.width - getSize().width) / 2, (Utilities.dimScreen.height - getSize().height) / 2);
		getContentPane().setBackground(new Color(0, 191, 255));
		getContentPane().setLayout(null);
		
		SetUpFrictionSpinner();
		SetUpWeightButtons();
		SetUpSaveCancelButtons();
		
		oldValueFriction = frictionSlider.getValue();
		oldLightValue = lightWeight.isSelected();
		oldNormalValue = normalWeight.isSelected();
		oldHeavyValue = heavyWeight.isSelected();
		
		ManageListeners();
	}
	
	public void SetUpFrictionSpinner() {
		frictionLabel = new JLabel("Friction");
		frictionLabel.setOpaque(true);
		frictionLabel.setBackground(new Color(0, 255, 0));
		frictionLabel.setForeground(new Color(0, 0, 0));
		frictionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frictionLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		frictionLabel.setBounds(243, 89, 98, 59);
		getContentPane().add(frictionLabel);
		
		frictionSlider = new JSlider();
		frictionSlider.setMajorTickSpacing(10);
		frictionSlider.setPaintLabels(true);
		frictionSlider.setPaintTicks(true);
		frictionSlider.setMinorTickSpacing(1);
		frictionSlider.setToolTipText("Decimals");
		frictionSlider.setMaximum(110);
		frictionSlider.setMinimum(70);
		frictionSlider.setBounds(192, 159, 200, 50);
		frictionSlider.setValue(99);
		frictionSlider.setBackground(new Color(0, 191, 255));
		frictionSlider.setForeground(Color.BLACK);
		getContentPane().add(frictionSlider);
	}

	public void SetUpWeightButtons() {
		
		ButtonGroup ballWeightButtons;
		
		ballWeightLabel = new JLabel("Ball Weight");
		ballWeightLabel.setOpaque(true);
		ballWeightLabel.setBackground(new Color(255, 255, 0));
		ballWeightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ballWeightLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		ballWeightLabel.setBounds(171, 251, 98, 59);
		getContentPane().add(ballWeightLabel);
		
		ballWeightButtons = new ButtonGroup();
		
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
				
				setVisible(false);
			}
		});
		
		btnCancelOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ar0) {
				frictionSlider.setValue(oldValueFriction);
				lightWeight.setSelected(oldLightValue);
				normalWeight.setSelected(oldNormalValue);
				heavyWeight.setSelected(oldHeavyValue);
				setVisible(false);
			}
		});
	}
}
