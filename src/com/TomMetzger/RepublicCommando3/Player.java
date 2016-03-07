package com.TomMetzger.RepublicCommando3;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.TomMetzger.RepublicCommando3.*;


public class Player extends GameObject implements EntityA
{
	private double velX = 0;
	private double velY = 0;
	
	private Textures SPRITE_SHEET;
	
	Animation anim;
	
	private Game game;
	
	

	public Player(double x, double y, Textures tex, Game game)
	{
		super(x, y);
		this.SPRITE_SHEET = tex;
		this.game = game;
		
		anim = new Animation(1, tex.player[0], tex.player[1], tex.player[2], tex.player[3]);
	}

	public void tick()
	{
		x+=velX;
		y+=velY;
		
		Game.distanceRan += 0.05;
		
		if(x < 0 - 32)
		{
			x =  640;
		}
		
		if(x > 640 + 32)
		{
			x = 0;
		}
		
		if(y <= 0)
		{
			y = 0;
		}
		
		if(y >= 480 - 48)
		{
			y = 480 - 48;
		}
		
		if(Physics.Collision(this, game.eb))
		{
			game.State = Game.STATE.GAMEOVER;
			//System.out.println("Game Over");
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
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	
	
	public void setVelX(double velX)
	{
		this.velX = velX;
	}
	
	
	
	public void setVelY(double velY)
	{
		this.velY = velY;
	}

	public Rectangle getBounds()
	{
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
