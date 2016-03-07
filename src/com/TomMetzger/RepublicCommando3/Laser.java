package com.TomMetzger.RepublicCommando3;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.TomMetzger.RepublicCommando3.*;

public class Laser extends GameObject implements EntityA
{
	private Textures SPRITE_SHEET;
	
	private Game game;
	
	Animation anim;
	
	private Controller c;

	public Laser(double x, double y, Textures tex, Game game)
	{
		super(x, y);
		this.SPRITE_SHEET = tex;
		this.game = game;
		anim = new Animation(5, tex.laser[0], tex.laser[0]);
	}

	
	public void tick()
	{
		y -= 10;
		
//		if(Physics.Collision(this, game.eb))
//		{
//			c.removeEntity(this);
//		}
		anim.runAnimation();
	}

	
	public void render(Graphics g)
	{
		anim.drawAnimation(g, x, y, 0);
	}

	
	public double getX()
	{
		// TODO Auto-generated method stub
		return x;
	}

	
	public double getY()
	{
		// TODO Auto-generated method stub
		return y;
	}

	
	public Rectangle getBounds()
	{
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 32, 32); //Double Check Measurements 
	}
	
}
