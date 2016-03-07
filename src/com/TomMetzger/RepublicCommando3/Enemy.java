package com.TomMetzger.RepublicCommando3;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.TomMetzger.RepublicCommando3.*;



public class Enemy extends GameObject implements EntityB
{
	private double velX;
	private double velY;
	
	private Textures SPRITE_SHEET;
	
	Random r = new Random();
	
	private int speed = 2;
	
	Animation anim;
	
	private Game game;
	
	private Controller c;
	
	
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.SPRITE_SHEET = tex;
		this.c = c;
		this.game = game;
		
		anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[0]);
	}

	
	public void tick()
	{
		y += speed;
		
		if (y > Game.HEIGHT * Game.SCALE - 32)
		{
			c.removeEntity(this);
		}
		
		if(Physics.Collision(this, game.ea))
		{
			c.removeEntity(this);
			game.setEnemy_killed(game.getEnemy_killed() + 1);
			game.score += 10;
		}
		
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
		return new Rectangle((int)x, (int)y, 32, 32); //Double Check Sizes
	}

}
