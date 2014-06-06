package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	String imageFile = null;

	public ImagePanel(String image) {
		super();
		this.imageFile = image;
	}

	public void paintComponent(Graphics g) {
		ImageIcon imageicon = new ImageIcon(imageFile);
		Image image = imageicon.getImage();

		super.paintComponent(g);

		if (image != null)
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}
