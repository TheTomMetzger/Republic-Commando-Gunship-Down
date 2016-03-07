package com.TomMetzger.RepublicCommando3;

import java.awt.image.BufferedImage;

import com.TomMetzger.RepublicCommando3.*;


public class SpriteSheetLoader
{
	private BufferedImage image;	
	
	public SpriteSheetLoader(BufferedImage image)
	{
		this.image = image;
	}
	
	
	
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
}
