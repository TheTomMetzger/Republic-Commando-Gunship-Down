package com.TomMetzger.RepublicCommando3;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.TomMetzger.RepublicCommando3.*;



public class BufferedImageLoader
{
	private BufferedImage image;
	
	
	
	public BufferedImage loadImage(String path) throws IOException
	{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}

