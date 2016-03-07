package com.TomMetzger.RepublicCommando3;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Background2 extends GameObject implements EntityC
{
	private double velX;
	private double velY;
	
	private Textures SPRITE;
	
	Random r = new Random();
	
	private int speed = 2;
	
	public static int width;
	public static int height;
	
	Animation anim;
	
	private Game game;
		
	private Controller c;

	
	
	public Background2(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.SPRITE = tex;
		this.c = c;
		this.game = game;
		
		width = tex.background2[0].getWidth();
		height = tex.background2[0].getHeight();
		
		
		anim = new Animation(5, tex.background2[0], tex.background2[0], tex.background2[0]);
	}
	
	
	
	@Override
	public void tick()
	{
		y += speed;
		if(y > (Game.HEIGHT * Game.SCALE))
		{
			y = -1370;
		}
		
		anim.runAnimation();
	}



	@Override
	public void render(Graphics g)
	{
		anim.drawAnimation(g, x, y, 0);
	}



	@Override
	public double getX()
	{
		// TODO Auto-generated method stub
		return x;
	}



	@Override
	public double getY()
	{
		// TODO Auto-generated method stub
		return y;
	}



	@Override
	public Rectangle getBounds()
	{
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, width, height);
	}

}
