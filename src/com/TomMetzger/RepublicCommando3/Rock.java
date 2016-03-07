package com.TomMetzger.RepublicCommando3;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Rock extends GameObject implements EntityB
{
	private double velX;
	private double velY;
	
	private Textures SPRITE_SHEET;
	
	Random r = new Random();
	
	private int speed = 2;
	
	Animation anim;
	
	private Game game;
	
	private Controller c;

	
	
	public Rock(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.SPRITE_SHEET = tex;
		this.c = c;
		this.game = game;
		
		anim = new Animation(5, tex.rock[0], tex.rock[1], tex.rock[0]);
	}



	@Override
	public void tick()
	{
		y += speed;
		
		if (y > Game.HEIGHT * Game.SCALE - 32)
		{
			c.removeEntity(this);
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
		return x;
	}



	@Override
	public double getY()
	{
		return y;
	}



	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
