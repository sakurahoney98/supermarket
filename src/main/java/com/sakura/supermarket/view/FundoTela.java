/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sakura.supermarket.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author Suporte TI
 */
public class FundoTela extends JDesktopPane{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagem;

	    public FundoTela(String imagem) {
	        this.imagem = new ImageIcon (imagem).getImage();
	    }
	    
	   
	    @Override
	    public void paintComponent(Graphics g){
	        g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
	    }
	    

}
