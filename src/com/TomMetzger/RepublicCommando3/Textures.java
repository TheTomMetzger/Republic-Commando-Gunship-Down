package com.TomMetzger.RepublicCommando3;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.TomMetzger.RepublicCommando3.*;

public class Textures
{
	public BufferedImage[] player = new BufferedImage[4];
	public BufferedImage[] enemy = new BufferedImage[3];
	public BufferedImage[] laser = new BufferedImage[3];
	public BufferedImage[] rock = new BufferedImage[3];
	
	public BufferedImage[] background1 = new BufferedImage[1];
	public BufferedImage[] background2 = new BufferedImage[1];
	public BufferedImage[] background3 = new BufferedImage[1];
	
	private SpriteSheetLoader SPRITE_SHEET;
	private BufferedImageLoader loader = new BufferedImageLoader();

	
	
	
	public Textures(Game game)
	{
		SPRITE_SHEET = new SpriteSheetLoader(game.getSpriteSheet());
		
		getTextures();
	}
	
	
	
	private void getTextures()
	{
		player[0] = SPRITE_SHEET.grabImage(1, 1, 32, 32);
		player[1] = SPRITE_SHEET.grabImage(1, 2, 32, 32);
		player[2] = SPRITE_SHEET.grabImage(1, 3, 32, 32);
		player[3] = SPRITE_SHEET.grabImage(1, 4, 32, 32);
		
		enemy[0] = SPRITE_SHEET.grabImage(2, 1, 32, 32);
		enemy[1] = SPRITE_SHEET.grabImage(2, 1, 32, 32);
		enemy[1] = SPRITE_SHEET.grabImage(2, 1, 32, 32);
		
		laser[0] = SPRITE_SHEET.grabImage(4, 1, 32, 32); //Double Check These Values
		laser[1] = SPRITE_SHEET.grabImage(4, 1, 32, 32);
		laser[2] = SPRITE_SHEET.grabImage(4, 1, 32, 32);
		
		rock[0] = SPRITE_SHEET.grabImage(3, 1, 32, 32);
		rock[1] = SPRITE_SHEET.grabImage(3, 1, 32, 32);
		rock[2] = SPRITE_SHEET.grabImage(3, 1, 32, 32);
		
		try
		{
			background1[0] = loader.loadImage("/rc3-2.jpg");
			background2[0] = loader.loadImage("/rc3-2.jpg");
			background3[0] = loader.loadImage("/rc3-2.jpg");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
