package com.TomMetzger.RepublicCommando3;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;

import com.TomMetzger.RepublicCommando3.*;


public class Controller
{
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	private LinkedList<EntityC> ec = new LinkedList<EntityC>();
	
	private Game game;
	
	Random r = new Random();
	
	Textures SPRITE_SHEET;
	
	EntityA enta;
	EntityB entb;
	EntityC entc;
	
	private Timer timer;
	private Timer timer2;
	private Timer timer3;
	private Timer timer4;
	private Timer timer5;
	private Timer rockTimer;
	private Timer rockTimer2;
	private Timer rockTimer3;
	private Timer rockTimer4;
	private Timer rockTimer5;
	
	public int count = 0;
	public int rockCount = 0;
	
	public Controller(Textures tex, Game game)
	{
		this.SPRITE_SHEET = tex;
		this.game = game;
		
		rockTimer = new Timer(1750, new RockTimerListener());
		rockTimer = new Timer(1500, new TimerListener());
		rockTimer = new Timer (1500, new TimerListener());
		rockTimer2 = new Timer (1000, new TimerListener());
		rockTimer3 = new Timer (2000, new TimerListener());
		rockTimer4 = new Timer (5000, new TimerListener());
		rockTimer5 = new Timer (1750, new TimerListener());
		
		
		timer = new Timer(1500, new TimerListener());
		timer = new Timer (1500, new TimerListener());
		timer2 = new Timer (1000, new TimerListener());
		timer3 = new Timer (2000, new TimerListener());
		timer4 = new Timer (5000, new TimerListener());
		timer5 = new Timer (1750, new TimerListener());
		
		
		timer.start();
		rockTimer.start();
	}
	
	
	
	public void tick()
	{
		//A Class
		for (int i = 0; i < ea.size(); i++)
		{
			enta = ea.get(i);
			
			enta.tick();
		}
		
		//B Class
		for(int i = 0; i < eb.size(); i++)
		{
			entb = eb.get(i);
			
			entb.tick();
		}
		
		//C Class
		for (int i = 0; i < ec.size(); i++)
		{
			entc = ec.get(i);
			
			entc.tick();
		}
	}
	
	
	
	public void render(Graphics g)
	{
		//A Class
		for (int i = 0; i < ea.size(); i++)
		{
			enta = ea.get(i);
			
			enta.render(g);
		}
		
		//B Class
		for (int i = 0; i < eb.size(); i++)
		{
			entb = eb.get(i);
			
			entb.render(g);
		}
		
		//C Class
		for (int i = 0; i < ec.size(); i++)
		{
			entc = ec.get(i);
			
			entc.render(g);
		}
	}
	
	
	
	public void addEntity(EntityA block)
	{
		ea.add(block);
	}
	
	public void removeEntity(EntityA block)
	{
		ea.remove(block);
	}
	
	
	
	public void addEntity(EntityB block)
	{
		eb.add(block);
	}
	
	public void removeEntity(EntityB block)
	{
		eb.remove(block);
	}
	
	
	
	public void addEntity(EntityC block)
	{
		ec.add(block);
	}
	
	public void removeEntity(EntityC block)
	{
		ec.remove(block);
	}
	
	
	public void createEnemy()
	{
			addEntity(new Enemy(r.nextInt(640), -16, SPRITE_SHEET, this, game));
	}
	
	
	public void createRock()
	{
			addEntity(new Rock(r.nextInt(640), -20, SPRITE_SHEET, this, game));
	}
	
	
	
	public LinkedList<EntityA> getEntityA()
	{
		return ea;
	}
	
	
	
	public LinkedList<EntityB> getEntityB()
	{
		return eb;
	}
	
	
	
	public LinkedList<EntityC> getEntityC()
	{
		return ec;
	}
	
	
	
	
	private class TimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			createEnemy();
			count++;
			if(count >= 30)
			{
				timer2.start();
			}
			if(count >= 60)
			{
				timer3.start();
			}
			if(count >= 120)
			{
				timer4.start();
			}
			if(count >= 240)
			{
				timer5.start();
			}
		}
		
	}
	
	
	
	private class RockTimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			createRock();
			rockCount++;
			if(count >= 30)
			{
				rockTimer2.start();
			}
			if(count >= 60)
			{
				rockTimer3.start();
			}
			if(count >= 120)
			{
				rockTimer4.start();
			}
			if(count >= 240)
			{
				rockTimer5.start();
			}
		}
		
	}
}
