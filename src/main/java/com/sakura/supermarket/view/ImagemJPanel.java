/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sakura.supermarket.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
class ImagemJPanel extends JPanel {
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagemDeFundo;

    public ImagemJPanel(String url) {
        ImageIcon imagemIcon = new ImageIcon(url);
        imagemDeFundo = imagemIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemDeFundo != null) {
            // Estica a imagem para preencher o JPanel
            g.drawImage(imagemDeFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}



