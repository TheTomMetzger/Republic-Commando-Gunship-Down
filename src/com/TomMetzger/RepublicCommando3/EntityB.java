package com.TomMetzger.RepublicCommando3;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.TomMetzger.RepublicCommando3.*;



public interface EntityB
{
	public void tick();
	public void render(Graphics g);
	
	public double getX();
	public double getY();
	
	public Rectangle getBounds();
}
