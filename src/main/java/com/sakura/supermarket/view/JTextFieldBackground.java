package com.sakura.supermarket.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JTextField;

public class JTextFieldBackground extends JTextField {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage backgroundImage;

    public JTextFieldBackground(String image) {
        super();
        try {
            backgroundImage = ImageIO.read(new File(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (backgroundImage != null) {
            size.width = Math.max(size.width, backgroundImage.getWidth());
            size.height = Math.max(size.height, backgroundImage.getHeight());
        }
        return size;
    }

    public void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
        repaint();
    }
}


