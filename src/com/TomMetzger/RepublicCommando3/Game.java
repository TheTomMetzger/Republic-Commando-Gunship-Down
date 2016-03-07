package com.TomMetzger.RepublicCommando3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;

import com.TomMetzger.RepublicCommando3.*;




public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9; //equate to 240
	public final static int SCALE = 2;
	public final String TITLE = "Republic Commando 3";
	private static JFrame frame;
	public static Font rc3Font;
	public static Font rc3Font_50;
	public static Font rc3Font_30;
	public static Font rc3Font_30_BOLD;
	
	private boolean running = false;
	private boolean paused = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	Random random = new Random();
	private double spawnX = random.nextInt((240 - 0) + 1) + 0;

	
	private Player p;
	private Background1 bg1;
	private Background2 bg2;
	private Background3 bg3;
	private Controller c;
	private Enemy t;
	private Textures SPRITE_SHEET;
	
	private int enemy_count = 1;
	private int enemy_killed = 0;
	private int rock_count = 1;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	//public LinkedList<EntityC> ec;
	
	private MainMenu menu;
	
	public static int score = 0;
	public static float distanceRan = 0;
	
	public enum STATE
	{
		MENU,
		GAME,
		OPTIONS,
		HELP,
		GAMEOVER
	};
	
	public STATE State = STATE.MENU;
	
	
	
	public void init()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try
		{
			spriteSheet = loader.loadImage("/RC3-SpriteSheet.png");
			//background = loader.loadImage("/starfield.png");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		SPRITE_SHEET = new Textures(this);
		p = new Player((double)300,(double)500, SPRITE_SHEET, this);
		c = new Controller(SPRITE_SHEET, this);
		bg1 = new Background1((double)0, (double)0, SPRITE_SHEET, c, this);
		bg2 = new Background2((double)0, (double)-685, SPRITE_SHEET, c, this);//(double)(-1*(Background2.height)), SPRITE_SHEET, c, this);
		bg3 = new Background3((double)0, (double)-1370, SPRITE_SHEET, c, this);
		
		menu = new MainMenu();
		
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		//ec = c.getEntityC();
		
		c.createEnemy();
		c.createRock();
	}
	
	
	
	private synchronized void start()
	{
		if(running)
		{
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	
	private synchronized void stop()
	{
		if(!running)
		{
			return;
		}
		
		running = false;
		
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	
	
	public void run()
	{
		requestFocus();
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
		
			if(delta >= 1)
			{
				tick();
				updates++;
				delta--;
			}			
			frames++;
			render();
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		
		stop();
		
	}
	
	
	
	private void tick()
	{
		if(State == STATE.MENU)
		{
			menu.tick();
		}
		
		else if(State == STATE.GAME)
		{
			bg1.tick();
			bg2.tick();
			bg3.tick();
			p.tick();
			c.tick();
		}
		
		else if(State == STATE.GAMEOVER)
		{
			//paused = true;
			//ComponentImageCapture.getScreenShot(component)
		}
	}
	
	
	
	private void render()
	{
		Color rc3Color = new Color(108, 249, 254);
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();getBounds();
		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		
		g.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		
		if(State == STATE.MENU)
		{
			menu.render(g);
		}
		else if(State == STATE.GAME)
		{
			bg1.render(g);
			bg2.render(g);
			bg3.render(g);
			p.render(g);
			c.render(g);
			g.setColor(rc3Color);
			Font fnt1 = new Font("Star Jedi", Font.PLAIN, 30);
			g.setFont(rc3Font_30);
			g.drawString(""+(int)distanceRan+"m", 15, 35);
		}
		else if(State == STATE.GAMEOVER)
		{
			g.setColor(rc3Color);
			Font fnt1 = new Font("Star Jedi", Font.PLAIN, 50);
			g.setFont(rc3Font_50);
			g.drawString("Score: " + ((int)(distanceRan *0.12) + score), WIDTH/2, 100);
			g.drawString("[Press Enter] ", WIDTH/2 - 50, 300);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(State == STATE.MENU)
		{
			if(key == KeyEvent.VK_UP)
			{
				menu.setSelected(menu.getSelected() - 1);
			}
			else if(key == KeyEvent.VK_DOWN)
			{
				menu.setSelected(menu.getSelected() + 1);
			}
			
			if(key == KeyEvent.VK_ENTER)
			{
				if(menu.getSelected() == 0)
				{
					State = STATE.GAME;
				}
				else if(menu.getSelected() == 1)
				{
					State = STATE.HELP;
				}
				else if(menu.getSelected() == 2)
				{
					System.exit(0);
				}
			}
		}
		else if(State == STATE.GAME)
		{
			if(key == KeyEvent.VK_RIGHT)
			{
				p.setVelX(5);
			}
			else if(key == KeyEvent.VK_LEFT)
			{
				p.setVelX(-5);
			}
		}
		else if(State == STATE.GAMEOVER)
		{
			//press enter to either go to main menu or go to highschore screen
			if (key == KeyEvent.VK_ENTER)
			{
				restart();
			}
		}
	}
	
	
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(State == STATE.GAME)
		{
			if(key == KeyEvent.VK_RIGHT)
			{
				p.setVelX(0);
			}
			else if(key == KeyEvent.VK_LEFT)
			{
				p.setVelX(0);
			}
			else if(key == KeyEvent.VK_SPACE)
			{
				c.addEntity(new Laser(p.getX(), p.getY()+48, SPRITE_SHEET, this));
			}
		}
	}
	
	
	
	public static void main(String args[])
	{
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		try
		{
			InputStream myStream = new BufferedInputStream(new FileInputStream("res/anklepants.ttf"));
			rc3Font = Font.createFont(Font.TRUETYPE_FONT, myStream);
			rc3Font_30 = rc3Font.deriveFont(30F);
			rc3Font_50 = rc3Font.deriveFont(50F);
			rc3Font_30_BOLD = rc3Font.deriveFont(Font.BOLD, 30F);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(frame.getContentPane().getSize());
		
		game.start();
	}
	
	
	
	public void restart()
	{
		score = 0;
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.setEnemy_killed(0);
		game.start();
	}
	
	
	
	public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
	}



	public int getEnemy_count() 
	{
		return enemy_count;
	}
	
	public int getRock_count()
	{
		return rock_count;
	}



	public void setEnemy_count(int enemy_count) 
	{
		this.enemy_count = enemy_count;
	}
	
	public void setRock_count(int rock_count)
	{
		this.rock_count = rock_count;
	}



	public int getEnemy_killed() 
	{
		
		return enemy_killed;
	}



	public void setEnemy_killed(int enemy_killed) 
	{
		this.enemy_killed = enemy_killed;
	}
	
}
