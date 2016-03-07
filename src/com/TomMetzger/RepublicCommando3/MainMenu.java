package com.TomMetzger.RepublicCommando3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.TomMetzger.RepublicCommando3.*;




public class MainMenu
{
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2 + 120, 200, 100, 50);
	public Rectangle optionsButton = new Rectangle(Game.WIDTH/2 + 120, 275, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 120, 350, 100, 40);
	public int selected = 0;
	public static final String[] OPTIONS = {"Play", "Options", "Quit"};
	
	Game game;
	
	public int initSelX = 251;
	public int initSelY = playButton.y;
	public String selector;
	
	
	
	public void render(Graphics g)
	{
		//selector = ">        <";
		if (getSelected() == 0)
		{
			selector = ">        <";
			initSelX = 251;
			initSelY = ((playButton.y+(g.getFontMetrics(Game.rc3Font_30).getHeight()/2)) + (g.getFontMetrics(Game.rc3Font_30_BOLD).getHeight()/2));
		}
		else if (getSelected() == 1)
		{
			selector = ">             <";
			initSelX = 221;
			initSelY = ((optionsButton.y+(g.getFontMetrics(Game.rc3Font_30).getHeight()/2)) + (g.getFontMetrics(Game.rc3Font_30_BOLD).getHeight()/2));
		}
		else if (getSelected() == 2)
		{
			selector = ">       <";
			initSelX = 257;
			initSelY = ((quitButton.y+(g.getFontMetrics(Game.rc3Font_30).getHeight()/2)) + (g.getFontMetrics(Game.rc3Font_30_BOLD).getHeight()/2));
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		Font fnt0 = new Font("Star Jedi", Font.PLAIN, 50);
		Color rc3Color = new Color(108, 249, 254);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH * 2, Game.HEIGHT * 2);
		
		g.setFont(Game.rc3Font_50);
		g.setColor(rc3Color);
		g.drawString("Republic Commando", (((Game.WIDTH * Game.SCALE) / 2) - (g.getFontMetrics().stringWidth("Republic Commando"))/2), 70);
		g.drawString("-Gunship Down-", (((Game.WIDTH * Game.SCALE) / 2) - (g.getFontMetrics().stringWidth("-Gunship Down-"))/2), 120);
		
//		System.out.println(g.getFontMetrics().stringWidth("Republic Commando"));
//		System.out.println("Scaled Midpoint Width Of Window: " + (Game.WIDTH * Game.SCALE) / 2);
//		System.out.println("Republic Commando Width Midpoint" + g.getFontMetrics().stringWidth("Republic Commando")/2);
//		
		Font fnt1 = new Font("Star Jedi", Font.PLAIN, 30);
		g.setFont(Game.rc3Font_30);
		
		playButton.x = ((Game.WIDTH * Game.SCALE) / 2) - ((g.getFontMetrics(Game.rc3Font_30).stringWidth("Play"))/2);
		System.out.println("Play X " + playButton.x);
		System.out.println("Play Y " +playButton.y);
		System.out.println("Should Be Play Middle: " + ((g.getFontMetrics(Game.rc3Font_30).stringWidth("Play"))/2));
		optionsButton.x = ((Game.WIDTH * Game.SCALE) / 2) - ((g.getFontMetrics(Game.rc3Font_30).stringWidth("Options"))/2);
		System.out.println("Options X " + optionsButton.x);
		quitButton.x = ((Game.WIDTH * Game.SCALE) / 2) - ((g.getFontMetrics(Game.rc3Font_30).stringWidth("Quit"))/2);
		System.out.println("Quit X " + quitButton.x);
		
		//initSelX = ((Game.WIDTH * Game.SCALE) / 2) - ((g.getFontMetrics().stringWidth("Play"))/2);
		///initSelY = ((Game.WIDTH * Game.SCALE) / 2) - ((g.getFontMetrics().stringWidth("Play"))/2);
		
		g.drawString("Play", playButton.x, playButton.y + 37);
		g.drawString("Options", optionsButton.x, optionsButton.y + 37);
		g.drawString("Quit", quitButton.x, quitButton.y + 37);
		
		Font fnt2 = new Font("Stencil", Font.BOLD, 30);
		g.setFont(Game.rc3Font_30_BOLD);
		System.out.println(((Game.WIDTH * Game.SCALE) / 2) - g.getFontMetrics().stringWidth(">        <")/2);
		g.drawString(selector, initSelX , initSelY);
		
//		g2d.draw(playButton);
//		g2d.draw(optionsButton);
//		g2d.draw(quitButton);
	}
	
	
	
	public void tick()
	{
		if(getSelected() == 0)
		{
			initSelX = 251;
			initSelY = playButton.y;// + 35;
		}
		
		if(getSelected() == 1)
		{
			initSelX = 233;
			initSelY = optionsButton.y;// + 35;
		}
		
		if(getSelected() == 2)
		{
			initSelX = 257;
			initSelY = quitButton.y;// + 35;
		}
		
		int len = OPTIONS.length;
		if (getSelected() < 0) setSelected(getSelected() + len);
		if (getSelected() >= len) setSelected(getSelected() - len);
		
	}
	
	
	
	public int getSelected()
	{
		return selected;
	}
	
	
	
	public void setSelected(int selected)
	{
		this.selected = selected;
	}
}
